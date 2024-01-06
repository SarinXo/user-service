package com.example.userservice.services;

import com.example.userservice.entities.Order;
import com.example.userservice.entities.Product;
import com.example.userservice.handlers.exceptions.TradingError;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);
    Page<Product> findAllByOrderByType(Pageable pageable);
    Page<Product> findAllByOrderByCost(Pageable pageable);

    void buyItemWithId(Integer id) throws TradingError;
}
