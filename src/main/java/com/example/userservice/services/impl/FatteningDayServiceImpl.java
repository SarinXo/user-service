package com.example.userservice.services.impl;


import com.example.userservice.entities.FatteningDay;
import com.example.userservice.repositories.FatteningDayRepository;
import com.example.userservice.services.FatteningDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FatteningDayServiceImpl implements FatteningDayService {

    private final FatteningDayRepository fatteningDayRepository;


    @Override
    public List<FatteningDay> findDaysByFarmCode(Integer farmCode) {
        return fatteningDayRepository.findByFarmCode(farmCode);
    }
}
