package com.example.userservice.repositories;

import com.example.userservice.entities.Stern;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SternRepository extends JpaRepository<Stern, Integer> {

    List<Stern> findSternsByFarmerId(Integer farmerId);

    Stern findSternById(Integer id);

    Optional<Stern> findSternByTypeAndFarmerId(String type, Integer farmerId);

}
