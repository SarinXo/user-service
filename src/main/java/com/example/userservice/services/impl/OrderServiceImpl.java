package com.example.userservice.services.impl;

import com.example.userservice.entities.Order;
import com.example.userservice.repositories.OrderRepository;
import com.example.userservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> findByIdContainingIgnoreCase(String keyword, Pageable pageable) {
        return orderRepository.findByIdContainingIgnoreCase(keyword, pageable);
    }
}
