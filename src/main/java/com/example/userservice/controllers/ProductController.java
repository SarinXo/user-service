package com.example.userservice.controllers;

import com.example.userservice.dto.BuyRequest;
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
public class ProductController {

    @PostMapping("/buy")
    @ResponseStatus(HttpStatus.OK)
    public void buy(@RequestBody BuyRequest buyRequest){
    }
}
