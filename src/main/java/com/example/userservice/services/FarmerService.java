package com.example.userservice.services;

import com.example.userservice.entities.Farmer;

public interface FarmerService {
    Farmer findFarmerById(Integer id);
}
