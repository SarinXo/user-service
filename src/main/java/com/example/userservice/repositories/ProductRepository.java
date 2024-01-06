package com.example.userservice.repositories;

import com.example.userservice.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findAllByOrderByType(Pageable pageable);
    Page<Product> findAllByOrderByCost(Pageable pageable);

    Product getProductById(Integer id);
}
