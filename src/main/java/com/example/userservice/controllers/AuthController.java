package com.example.userservice.controllers;

import com.example.userservice.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login-page";
    }

    @GetMapping("/users/test")
    public String test(){
        return "all-good";
    }
}
