package com.example.userservice.services.impl;

import com.example.userservice.entities.Farm;
import com.example.userservice.entities.Farmer;
import com.example.userservice.entities.Pig;
import com.example.userservice.entities.PigForSale;
import com.example.userservice.entities.User;
import com.example.userservice.handlers.exceptions.TradingError;
import com.example.userservice.repositories.FarmRepository;
import com.example.userservice.repositories.FarmerRepository;
import com.example.userservice.repositories.PigForSaleRepository;
import com.example.userservice.repositories.PigRepository;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.services.PigForSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PigForSaleServiceImpl implements PigForSaleService {
    private final PigForSaleRepository pigForSaleRepository;
    private final UserRepository userRepository;
    private final FarmerRepository farmerRepository;
    private final FarmRepository farmRepository;
    private final PigRepository pigRepository;
    @Override
    public void sell(PigForSale pigForSale) {
        if(pigForSaleRepository.existsByPigId(pigForSale.getPigId()))
            throw new RuntimeException("this pig already on marketplace!");
        pigForSaleRepository.save(pigForSale);
    }

    @Override
    public Page<PigForSale> findAll(Pageable pageable) {
        return pigForSaleRepository.findAll(pageable);
    }

    @Override
    public List<PigForSale> getByFarmerId(Integer id) {
        return pigForSaleRepository.findPigByFarmerId(id);
    }

    @Override
    public void takeBack(Integer id) {
        if(!pigForSaleRepository.existsById(id))
             throw new RuntimeException("this id not found in db");
        pigForSaleRepository.deleteById(id);
    }

    @Override
    public Page<PigForSale> findAllByKeyword(String keyWord, Pageable pageable) {
        return pigForSaleRepository.findAllByKeyword(keyWord, pageable);
    }

    @Override
    @Transactional
    public void buy(Integer id) throws TradingError {
        UserDetails userDetails =(UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        User user = userRepository.getUserByLogin(userDetails.getUsername());
        Farmer customer = farmerRepository.findFarmerById(user.getFarmerId());
        Farm customerFarm = farmRepository.findFarmByFarmCode(user.getFarmerId());
        PigForSale pigForSale = pigForSaleRepository.findById(id).get();
        Farm sellerFarm = farmRepository.findFarmByFarmCode(pigForSale.getFarmerId());

        if(customerFarm.getMoney() - pigForSale.getCost() < 0.){
            throw new TradingError("Low balance");
        }
        customerFarm.setMoney(customerFarm.getMoney() - pigForSale.getCost());
        sellerFarm.setMoney(sellerFarm.getMoney() + pigForSale.getCost());
        farmRepository.save(customerFarm);
        farmRepository.save(sellerFarm);

        Pig pig = pigRepository.findPigById(pigForSale.getPigId());
        pig.setFarmerId(customer.getId());
        pigRepository.save(pig);


        pigForSaleRepository.delete(pigForSale);

    }

}
