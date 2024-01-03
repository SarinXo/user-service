package com.example.userservice.controllers;

import com.example.userservice.services.FeedbacksService;
import com.example.userservice.services.UserPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserPageController {

    private final UserPageService userPageServiceImpl;

    @GetMapping("/page")
    public String giveUserPage(
            @RequestParam(required = false) String login,
            Model model){
        model = userPageServiceImpl.setProperties4UserPage(login, model);
        return "user-page";
    }

}
