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

    @Query("SELECT pfs " +
            "FROM PigForSale pfs " +
                "WHERE LOWER(CONCAT('pfs.', :keyWord)) LIKE LOWER(CONCAT('%', :sortWord, '%'))")
    Page<PigForSale> findAllByKeywordAndSortWord(@Param("keyWord") String keyWord,
                                                 Pageable pageable);

    @Query("SELECT pfs FROM PigForSale pfs " +
            "JOIN Pig p ON pfs.pigId = p.id " +
            "WHERE LOWER(p.someField) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "AND LOWER(pfs.someOtherField) LIKE LOWER(CONCAT('%', :sortWord, '%'))")
    Page<PigForSale> findMatchingPigForSale(@Param("keyword") String keyword,
                                            @Param("sortWord") String sortWord,
                                            Pageable pageable);
    Page<PigForSale> findAll(Pageable pageable);
}
