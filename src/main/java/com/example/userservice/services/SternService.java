package com.example.userservice.services;


import com.example.userservice.entities.Stern;

import java.util.List;

public interface SternService {

    List<Stern> findSternsByFarmerId(Integer farmerId);
}
