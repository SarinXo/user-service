package com.example.userservice.repositories;

import com.example.userservice.entities.SternForSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SternForSaleRepository extends JpaRepository<SternForSale, Integer> {
    Optional<SternForSale> findByFarmerIdAndSternId(Integer farmerId, Integer sternId);
    List<SternForSale> findByFarmerId(Integer farmerId);

    SternForSale getSternForSaleById(Integer id);
}
