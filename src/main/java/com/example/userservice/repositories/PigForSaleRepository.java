package com.example.userservice.repositories;

import com.example.userservice.entities.PigForSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PigForSaleRepository extends JpaRepository<PigForSale, Integer> {
    Boolean existsByPigId(Integer pigId);
    List<PigForSale> findPigByFarmerId(Integer farmerId);
}
