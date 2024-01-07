package com.example.userservice.controllers;

import com.example.userservice.dto.DeleteRequest;
import com.example.userservice.entities.PigForSale;
import com.example.userservice.entities.SternForSale;
import com.example.userservice.services.PigForSaleService;
import com.example.userservice.services.SternForSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/sell")
public class SellController {
    private final PigForSaleService pigForSaleServiceImpl;
    private final SternForSaleService sternForSaleServiceImpl;

    @PostMapping("/pig")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sellPig(@RequestBody PigForSale pigForSale){
        pigForSaleServiceImpl.sell(pigForSale);
    }

    @PostMapping("/stern")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sellStern(@RequestBody SternForSale sternForSale){
        sternForSaleServiceImpl.sell(sternForSale);
    }

    @PostMapping("/out-pig")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sellPig(@RequestBody DeleteRequest id){
        if(Objects.nonNull(id))
            pigForSaleServiceImpl.takeBack(id.getId());
        else
            throw new RuntimeException("No id /users/sell/out-stern");
    }

    @PostMapping("/out-stern")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sellStern(@RequestBody DeleteRequest id){
        if(Objects.nonNull(id))
            sternForSaleServiceImpl.takeBack(id.getId());
        else
            throw new RuntimeException("No id /users/sell/out-stern");
    }
}
