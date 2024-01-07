package com.example.userservice.services.impl;

import com.example.userservice.entities.Farm;
import com.example.userservice.repositories.FarmRepository;
import com.example.userservice.services.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;

    @Override
    public Farm findFarmById(Integer farmId) {
        return farmRepository.findFarmByFarmCode(farmId);
    }

    @Override
    public void update(Farm farm) {
        farmRepository.save(farm);
    }
}
