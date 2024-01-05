package com.example.userservice.repositories;

import com.example.userservice.entities.Pig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PigRepository extends JpaRepository<Pig, Integer> {
    List<Pig> findPigsByFarmerId(Integer farmerId);
}
