package com.example.userservice.dto;

import lombok.Data;

@Data
public class FeedbackDto {
    private String  comment;
    private Integer rating;
    private Integer toFarmerWithId;
}
