package com.example.userservice.services.impl;

import com.example.userservice.entities.Stern;
import com.example.userservice.repositories.SternRepository;
import com.example.userservice.services.SternService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SternServiceImpl implements SternService {

    private final SternRepository sternRepository;

    @Override
    public List<Stern> findSternsByFarmerId(Integer farmerId) {
        return sternRepository.findSternsByFarmerId(farmerId);
    }

    @Override
    public Stern findSternById(Integer id) {
        return sternRepository.findSternById(id);
    }
}
