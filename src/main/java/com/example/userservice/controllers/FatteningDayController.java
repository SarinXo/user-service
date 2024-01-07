package com.example.userservice.controllers;

import com.example.userservice.entities.FatteningDay;
import com.example.userservice.services.FatteningDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class FatteningDayController {
    private final FatteningDayService fatteningDayServiceImpl;

    @PostMapping("/fat-day")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addFatteningDay(@RequestBody FatteningDay fatteningDay){
        fatteningDayServiceImpl.addDay(fatteningDay);
    }
}
