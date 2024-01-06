package com.example.userservice.services;


import com.example.userservice.entities.Pig;

import java.util.List;

public interface PigService {

    List<Pig> findPigsByFarmerId(Integer id);

    Pig findPigById(Integer id);
}
