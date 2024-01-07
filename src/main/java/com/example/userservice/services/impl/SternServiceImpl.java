package com.example.userservice.services.impl;

import com.example.userservice.entities.Stern;
import com.example.userservice.repositories.SternRepository;
import com.example.userservice.services.SternService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    @Override
    public void addStern(Stern stern) {
        Optional<Stern> existsInDbStern = sternRepository
                .findSternByTypeAndFarmerId(stern.getType(), stern.getFarmerId());
        if (existsInDbStern.isPresent()) {
            Stern existingStern = existsInDbStern.get();
            existingStern.setWeight(existingStern.getWeight() + stern.getWeight());
            sternRepository.save(existingStern);
        } else {
            sternRepository.save(stern);
        }
    }
}
