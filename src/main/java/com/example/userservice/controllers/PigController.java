package com.example.userservice.controllers;

import com.example.userservice.entities.Pig;
import com.example.userservice.services.PigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class PigController {
    private final PigService pigServiceImpl;

    @PostMapping("/pigs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPig(@RequestBody Pig pig){
        pigServiceImpl.addPig(pig);
    }
}
