package com.example.userservice.services;


import com.example.userservice.entities.FatteningDay;

import java.util.List;

public interface FatteningDayService {

    List<FatteningDay> findDaysByFarmCode(Integer farmCode);
}
