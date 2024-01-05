package com.example.userservice.services.impl;

import com.example.userservice.entities.Weight;
import com.example.userservice.handlers.exceptions.WeightError;
import com.example.userservice.repositories.WeightRepository;
import com.example.userservice.services.WeightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeightServiceImpl implements WeightService {

    private final WeightRepository weightRepository;


    @Override
    public Weight findCurrentWeightByPigId(Integer id) {
        Optional<Weight> weight = weightRepository.findWeightsByPigId(id).stream()
                .max(Comparator.comparing(Weight::getDateCollectionDay));
        return  weight.orElseThrow(() -> new WeightError("Pig weight data is not defined"));
    }
}
