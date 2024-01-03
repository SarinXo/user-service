package com.example.userservice.services;

import com.example.userservice.dto.FeedbackDto;
import com.example.userservice.entities.Feedback;

import java.util.List;

public interface FeedbacksService {
    List<Feedback> findFeedbacksByFarmerId(Integer id);
    Feedback addFeedBack(FeedbackDto feedback);
}
