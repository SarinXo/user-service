package com.example.userservice.services.impl;

import com.example.userservice.entities.Farmer;
import com.example.userservice.repositories.FarmerRepository;
import com.example.userservice.services.FarmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FarmerServiceImpl implements FarmerService {

    private final FarmerRepository farmerRepository;
    @Override
    public Farmer findFarmerById(Integer id) {
        return farmerRepository.findFarmerById(id);
    }
}
