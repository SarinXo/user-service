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
public class PagesController {

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

/*    @GetMapping("/market")
    public String marketplace(Model model,
                              @RequestParam(required = false, defaultValue = "1") int page,
                              @RequestParam(required = false, defaultValue = "5") int size,
                              @RequestParam(required = false, defaultValue = "off") String sortByName,
                              @RequestParam(required = false, defaultValue = "off") String sortByPrice){
        model = pageServiceImpl.setProperties4Market(model, page, size, sortByName, sortByPrice);
        return "market-page";
    }

    @GetMapping("/predict")
    public String predictStats(Model model){
        model = pageServiceImpl.setProperties4Predict(model);
        return "predict-page";
    }*/

    @GetMapping("/market/pigs")
    public String marketplace(Model model,
                              @RequestParam(required = false, defaultValue = "1") int page,
                              @RequestParam(required = false, defaultValue = "5") int size,
                              @RequestParam(required = false, defaultValue = "off") String sortByName,
                              @RequestParam(required = false, defaultValue = "off") String sortByPrice,
                              @RequestParam(required = false, defaultValue = "") String keyWord) {
        model = pageServiceImpl.setProperties4MarketPigs(model, page, size, sortByName, sortByPrice, keyWord);
        return "pig-market";
    }
}
