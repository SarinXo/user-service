package com.example.userservice.repositories;

import com.example.userservice.entities.PigForSale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PigForSaleRepository extends JpaRepository<PigForSale, Integer> {
    Boolean existsByPigId(Integer pigId);
    List<PigForSale> findPigByFarmerId(Integer farmerId);

    @Query("SELECT pfs FROM PigForSale pfs " +
            "JOIN Pig p ON pfs.pigId = p.id " +
            "WHERE (CAST(p.id AS string )) LIKE CONCAT('%', :keyWord, '%') " +
            "OR p.breed LIKE CONCAT('%', :keyWord, '%') " +
            "OR p.gender LIKE CONCAT('%', :keyWord, '%')")
    Page<PigForSale> findAllByKeyword(@Param("keyWord") String keyWord, Pageable pageable);

    Page<PigForSale> findAll(Pageable pageable);
}
