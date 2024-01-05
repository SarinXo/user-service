package com.example.userservice.repositories;

import com.example.userservice.entities.FatteningDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FatteningDayRepository extends JpaRepository<FatteningDay, Integer> {
    List<FatteningDay> findByFarmCode(Integer farmCode);
}
