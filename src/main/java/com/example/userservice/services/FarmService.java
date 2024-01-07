package com.example.userservice.services;

import com.example.userservice.entities.Farm;

public interface FarmService {
    Farm findFarmById(Integer farmId);

    void update(Farm farm);
}
