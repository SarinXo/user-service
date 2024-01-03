package com.example.userservice.repositories;

import com.example.userservice.entities.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Integer> {
    Farmer findFarmerById(Integer id);
}
