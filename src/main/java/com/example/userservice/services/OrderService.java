package com.example.userservice.services;

import com.example.userservice.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<Order> findAll(Pageable pageable);
    Page<Order> findByIdContainingIgnoreCase(String keyword, Pageable pageable);
}
