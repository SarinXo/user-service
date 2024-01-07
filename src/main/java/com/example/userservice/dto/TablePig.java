package com.example.userservice.dto;

import com.example.userservice.entities.Pig;
import com.example.userservice.entities.PigForSale;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TablePig {
    private int id;//from PigsForSale
    private Integer farmerId;
    private String breed;
    private LocalDate dateOfBirthday;
    private String gender;
    private Integer pigId;
    private Double cost;

    public TablePig(Pig pig, PigForSale pigForSale){
        this.id = pigForSale.getId();
        this.farmerId = pigForSale.getFarmerId();
        this.breed = pig.getBreed();
        this.dateOfBirthday = pig.getDateOfBirthday();
        this.gender = pig.getGender();
        this.pigId = pig.getId();
        this.cost = pigForSale.getCost();
    }
}
