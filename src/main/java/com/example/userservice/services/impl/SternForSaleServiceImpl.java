package com.example.userservice.services.impl;

import com.example.userservice.entities.Stern;
import com.example.userservice.entities.SternForSale;
import com.example.userservice.repositories.SternForSaleRepository;
import com.example.userservice.repositories.SternRepository;
import com.example.userservice.services.SternForSaleService;
import com.example.userservice.services.SternService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SternForSaleServiceImpl implements SternForSaleService {
    private final SternForSaleRepository sternForSaleRepository;
    private final SternService sternService;
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
    public List<SternForSale> getByFarmerId(Integer farmerId) {
        return sternForSaleRepository.findByFarmerId(farmerId);
    }
}
