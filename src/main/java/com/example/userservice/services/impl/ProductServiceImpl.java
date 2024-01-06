package com.example.userservice.services.impl;

import com.example.userservice.entities.Farm;
import com.example.userservice.entities.Farmer;
import com.example.userservice.entities.Order;
import com.example.userservice.entities.Pig;
import com.example.userservice.entities.Product;
import com.example.userservice.entities.Stern;
import com.example.userservice.entities.User;
import com.example.userservice.handlers.exceptions.TradingError;
import com.example.userservice.repositories.FarmRepository;
import com.example.userservice.repositories.FarmerRepository;
import com.example.userservice.repositories.PigRepository;
import com.example.userservice.repositories.ProductRepository;
import com.example.userservice.repositories.SternRepository;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.services.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final FarmerRepository farmerRepository;

    private static final Integer STERN = 1;
    private static final Integer PIG = 2;
    private final FarmRepository farmRepository;
    private final SternRepository sternRepository;
    private final PigRepository pigRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findAllByOrderByType(Pageable pageable) {
        return productRepository.findAllByOrderByType(pageable);
    }

    @Override
    public Page<Product> findAllByOrderByCost(Pageable pageable) {
        return productRepository.findAllByOrderByCost(pageable);
    }

    @Override
    @Transactional
    public void buyItemWithId(Integer id) throws TradingError {
        UserDetails userDetails =(UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        User user = userRepository.getUserByLogin(userDetails.getUsername());
        Farmer customer = farmerRepository.findFarmerById(user.getFarmerId());
        Farm customerFarm = farmRepository.findFarmByFarmCode(user.getFarmerId());
        Product product = productRepository.getProductById(id);
        Farm sellerFarm = farmRepository.findFarmByFarmCode(product.getFarmerId());

        if(customerFarm.getMoney() - product.getCost() < 0.){
            throw new TradingError("Low balance");
        }
        customerFarm.setMoney(customerFarm.getMoney() - product.getCost());
        sellerFarm.setMoney(sellerFarm.getMoney() + product.getCost());
        farmRepository.save(customerFarm);
        farmRepository.save(sellerFarm);

        if(Objects.equals(product.getType(), STERN)){
            Stern stern = sternRepository.findSternById(product.getProductId());
            stern.setFarmerId(customer.getId());
            sternRepository.save(stern);
        } else if (Objects.equals(product.getType(), PIG)) {
            Pig pig = pigRepository.findPigById(product.getProductId());
            pig.setFarmerId(customer.getId());
            pigRepository.save(pig);

        }
        productRepository.delete(product);

    }


}
