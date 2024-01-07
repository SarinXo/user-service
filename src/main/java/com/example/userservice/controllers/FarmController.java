package com.example.userservice.controllers;

import com.example.userservice.entities.Farm;
import com.example.userservice.services.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class FarmController {
    private final FarmService farmServiceImpl;

    @PostMapping("/farms")
    public void updateFarm(@RequestBody Farm farm){
        farmServiceImpl.update(farm);
    }
}
