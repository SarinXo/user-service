package com.example.userservice.repositories;

import com.example.userservice.entities.Stern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SternRepository extends JpaRepository<Stern, Integer> {

    List<Stern> findSternsByFarmerId(Integer farmerId);
}
