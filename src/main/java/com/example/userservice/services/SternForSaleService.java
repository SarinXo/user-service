package com.example.userservice.services;

import com.example.userservice.entities.SternForSale;

import java.util.List;

public interface SternForSaleService {
    void sell(SternForSale sternForSale);

    List<SternForSale> getByFarmerId(Integer farmerId);

    void takeBack(Integer integer);
}
