package com.example.userservice.services.impl;

import com.example.userservice.entities.Pig;
import com.example.userservice.repositories.PigRepository;
import com.example.userservice.services.PigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PigServiceImpl implements PigService {

    private final PigRepository pigRepository;

    @Override
    public List<Pig> findPigsByFarmerId(Integer id) {
        return pigRepository.findPigsByFarmerId(id);
    }

    @Override
    public Pig findPigById(Integer id) {
        return pigRepository.findPigById(id);
    }

    @Override
    public void addPig(Pig pig) {
        pigRepository.save(pig);
    }
}
