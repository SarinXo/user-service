package com.example.userservice.services;

import com.example.userservice.entities.Weight;

import java.util.List;

public interface WeightService {
    Weight findCurrentWeightByPigId(Integer id);

    void save(Weight weight);
}
