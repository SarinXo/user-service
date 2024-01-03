package com.example.userservice.services.impl;

import com.example.userservice.entities.Farmer;
import com.example.userservice.entities.Feedback;
import com.example.userservice.entities.User;
import com.example.userservice.services.FarmerService;
import com.example.userservice.services.FeedbacksService;
import com.example.userservice.services.UserPageService;
import com.example.userservice.services.UserService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserPageServiceImpl implements UserPageService {
    private final UserService userService;
    private final FeedbacksService feedbacksService;
    private final FarmerService farmerService;

    public Model setProperties4UserPage(@Nullable String login, Model model){
        boolean isUserHomePage = Objects.isNull(login);

        if(isUserHomePage){
            login = ((UserDetails) SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getPrincipal())
                    .getUsername();
        }
        User user = userService.getUserByLogin(login);
        Farmer farmer = farmerService.findFarmerById(user.getFarmerId());
        List<Feedback> feedbacks = feedbacksService.findFeedbacksByFarmerId(user.getFarmerId());
        List<String> names = new ArrayList<>(feedbacks.size());
        for (Feedback feedback : feedbacks){
            String name = farmerService.findFarmerById(feedback.getFarmerId()).getName();
            names.add(name);
        }

        model.addAttribute("farmer", farmer);
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("names", names);
        model.addAttribute("isUserHomePage", isUserHomePage);
        return model;
    }


}
