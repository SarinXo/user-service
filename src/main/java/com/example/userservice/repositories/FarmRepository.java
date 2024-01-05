package com.example.userservice.repositories;

import com.example.userservice.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farm, Integer> {
    Farm findFarmByFarmCode(Integer farmId);

}
