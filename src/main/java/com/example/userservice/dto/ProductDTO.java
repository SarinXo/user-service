package com.example.userservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    private String identificator;
    private Double weight;
    private String type;
    private String ownerFio;
    private String ownerLogin;
    private Double cost;
}
