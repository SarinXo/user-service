package com.example.userservice.controllers;

import com.example.userservice.dto.BuyRequest;
import com.example.userservice.dto.BuySternRequest;
import com.example.userservice.services.PigForSaleService;
import com.example.userservice.services.SternForSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MarketplaceController {
    private final PigForSaleService pigForSaleServiceImpl;
    private final SternForSaleService sternForSaleServiceImpl;

    @PostMapping("/buy-pig")
    @ResponseStatus(HttpStatus.OK)
    public void buyPig(@RequestBody BuyRequest buyRequest){
        if(Objects.nonNull(buyRequest.getId())){
            pigForSaleServiceImpl.buy(buyRequest.getId());
        } else {
            throw new RuntimeException("empty request");
        }

    }

    @PostMapping("/buy-stern")
    @ResponseStatus(HttpStatus.OK)
    public void buyStern(@RequestBody BuySternRequest buyRequest){
        if(Objects.nonNull(buyRequest.getSternForSaleId())){
            sternForSaleServiceImpl.buy(buyRequest.getSternForSaleId(), buyRequest.getWeight());
        } else {
            throw new RuntimeException("empty request");
        }
    }
}
