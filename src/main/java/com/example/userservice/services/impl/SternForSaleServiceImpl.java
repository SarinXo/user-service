package com.example.userservice.services.impl;

import com.example.userservice.entities.Farm;
import com.example.userservice.entities.Farmer;
import com.example.userservice.entities.Stern;
import com.example.userservice.entities.SternForSale;
import com.example.userservice.entities.User;
import com.example.userservice.handlers.exceptions.TradingError;
import com.example.userservice.repositories.FarmRepository;
import com.example.userservice.repositories.FarmerRepository;
import com.example.userservice.repositories.PigRepository;
import com.example.userservice.repositories.SternForSaleRepository;
import com.example.userservice.repositories.SternRepository;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.services.SternForSaleService;
import com.example.userservice.services.SternService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SternForSaleServiceImpl implements SternForSaleService {
    private final SternForSaleRepository sternForSaleRepository;
    private final SternService sternService;
    private final UserRepository userRepository;
    private final FarmerRepository farmerRepository;
    private final FarmRepository farmRepository;
    private final SternRepository sternRepository;
    @Override
    public void sell(SternForSale sternForSale) {
        Stern stern = sternService.findSternById(sternForSale.getSternId());
        Optional<SternForSale> inDb = sternForSaleRepository
                .findByFarmerIdAndSternId(sternForSale.getFarmerId(), sternForSale.getSternId());

        if(inDb.isPresent()){
            SternForSale sternForSale1 = inDb.get();
            sternForSale1
                .setWeight(
                        inDb.get().getWeight() + sternForSale.getWeight()
                );
            if(sternForSale1.getWeight() > stern.getWeight())
                throw new RuntimeException("Too much stern!");
            sternForSaleRepository.save(inDb.get());
        } else {
            if(sternForSale.getWeight() > stern.getWeight())
                throw new RuntimeException("Too much stern!");

            sternForSaleRepository.save(sternForSale);
        }
    }

    @Override
    public void takeBack(Integer id) {
        if(!sternForSaleRepository.existsById(id))
            throw new RuntimeException("not found id in db");
        sternForSaleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void buy(Integer id, Double weight) throws TradingError {
        UserDetails userDetails =(UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        User user = userRepository.getUserByLogin(userDetails.getUsername());
        Farmer customer = farmerRepository.findFarmerById(user.getFarmerId());
        Farm customerFarm = farmRepository.findFarmByFarmCode(user.getFarmerId());
        SternForSale sternForSale = sternForSaleRepository.getSternForSaleById(id);
        Farm sellerFarm = farmRepository.findFarmByFarmCode(sternForSale.getFarmerId());

        if(customerFarm.getMoney() - sternForSale.getCostPerKilo()*weight < 0.){
            throw new TradingError("Low balance");
        }
        if(weight > sternForSale.getWeight()){
            throw new TradingError("Too many weight");
        }
        //перемещение денег
        customerFarm.setMoney(customerFarm.getMoney() - sternForSale.getCostPerKilo()*weight);
        sellerFarm.setMoney(sellerFarm.getMoney() + sternForSale.getCostPerKilo()*weight);
        farmRepository.save(customerFarm);
        farmRepository.save(sellerFarm);
        //вычет веса от одного фермера
        Stern stern = sternRepository.findSternById(sternForSale.getSternId());
        stern.setWeight(stern.getWeight() - sternForSale.getCostPerKilo()*weight);
        if(sternForSale.getWeight() <= weight + 0.00001){
            sternForSaleRepository.delete(sternForSale);
            sternRepository.delete(stern);
        }else{
            sternRepository.save(stern);
        }
        //добавление другому фермеру
        List<Stern> sterns = sternRepository.findSternsByFarmerId(customer.getId());
        for(var st : sterns){
            if(st.getType().equals(stern.getType())){
                st.setWeight(st.getWeight() + weight);
                sternRepository.save(st);
                return;
            }
        }
        sternRepository.save(new Stern(customer.getId(), stern.getType(), weight));

    }

    @Override
    public List<SternForSale> getByFarmerId(Integer farmerId) {
        return sternForSaleRepository.findByFarmerId(farmerId);
    }
}
