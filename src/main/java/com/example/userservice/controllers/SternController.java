package com.example.userservice.controllers;

import com.example.userservice.entities.Stern;
import com.example.userservice.services.SternService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class SternController {
    private final SternService sternServiceImpl;

    @PostMapping("/sterns")
    public void addStern(@RequestBody Stern stern){
        sternServiceImpl.addStern(stern);
    }
}
