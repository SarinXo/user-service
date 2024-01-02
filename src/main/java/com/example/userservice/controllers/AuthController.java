package com.example.userservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {


    @GetMapping("/all-good")
    public String test(){
        return "all-good";
    }

    @GetMapping("/login")
    public String login(){
        return "login-page";
    }

}
