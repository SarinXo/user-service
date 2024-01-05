package com.example.userservice.repositories;

import com.example.userservice.entities.Weight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeightRepository extends JpaRepository<Weight, Integer> {
    List<Weight> findWeightsByPigId(Integer id);
}
