package com.example.userservice.services;

import com.example.userservice.entities.PigForSale;

import java.util.List;

public interface PigForSaleService {
    void sell(PigForSale pigForSale);

    List<PigForSale> getByFarmerId(Integer id);

    void takeBack(Integer integer);
}
