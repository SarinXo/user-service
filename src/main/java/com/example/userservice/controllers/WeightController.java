package com.example.userservice.controllers;

import com.example.userservice.entities.Weight;
import com.example.userservice.services.WeightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class WeightController {
    private final WeightService weightService;

    @PostMapping("/weights")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addWeight(@RequestBody Weight weight){
        weight.setDateCollectionDay(LocalDate.now());
        weightService.save(weight);
    }
}
