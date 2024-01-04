package com.example.userservice.controllers;

import com.example.userservice.dto.FeedbackDto;
import com.example.userservice.services.FeedbacksService;
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
public class FeedbackController {

    private final FeedbacksService feedbacksServiceImpl;

    @PostMapping("/feedback")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addFeedback(@RequestBody FeedbackDto feedbackDto){
        feedbacksServiceImpl.addFeedBack(feedbackDto);
    }
}
