package com.example.userservice.controllers;

import com.example.userservice.services.PageService;
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

    private final PageService pageServiceImpl;

    @GetMapping("/page")
    public String userPage(
            @RequestParam(required = false) String login,
            Model model){
        model = pageServiceImpl.setProperties4UserPage(login, model);
        return "user-page";
    }

    @GetMapping("/stat")
    public String userStatistic(Model model){
        model = pageServiceImpl.setProperties4Statistic(model);
        return "stat-page";
    }

    @GetMapping("/market")
    public String marketplace(Model model,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "5") int size,
                              @RequestParam(defaultValue = "false") boolean sortByName,
                              @RequestParam(defaultValue = "false") boolean sortByPrice){
        model = pageServiceImpl.setProperties4Market(model, page, size, sortByName, sortByPrice);
        return "market-page";
    }

    @GetMapping("/predict")
    public String predictStats(){
        return "predict-page";
    }

}
