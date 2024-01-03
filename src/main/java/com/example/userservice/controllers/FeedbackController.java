package com.example.userservice.controllers;

import com.example.userservice.dto.FeedbackDto;
import com.example.userservice.entities.Feedback;
import com.example.userservice.repositories.FeedbacksRepository;
import com.example.userservice.services.FeedbacksService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class FeedbackController {

    private final FeedbacksService feedbacksServiceImpl;

    @PostMapping("/submit-feedback")
    public String addFeedback(HttpServletRequest request, @RequestBody FeedbackDto feedbackDto){
        String referer = request.getHeader("Referer");

        feedbacksServiceImpl.addFeedBack(feedbackDto);
        return "redirect:" + referer;
    }
}
