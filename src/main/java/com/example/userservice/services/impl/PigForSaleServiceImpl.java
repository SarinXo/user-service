package com.example.userservice.services.impl;

import com.example.userservice.entities.PigForSale;
import com.example.userservice.repositories.PigForSaleRepository;
import com.example.userservice.services.PigForSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PigForSaleServiceImpl implements PigForSaleService {
    private final PigForSaleRepository pigForSaleRepository;
    @Override
    public void sell(PigForSale pigForSale) {
        if(pigForSaleRepository.existsByPigId(pigForSale.getPigId()))
            throw new RuntimeException("this pig already on marketplace!");
        pigForSaleRepository.save(pigForSale);
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

}
