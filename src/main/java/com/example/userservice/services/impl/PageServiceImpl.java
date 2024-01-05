package com.example.userservice.services.impl;

import com.example.userservice.entities.Farm;
import com.example.userservice.entities.Farmer;
import com.example.userservice.entities.FatteningDay;
import com.example.userservice.entities.Feedback;
import com.example.userservice.entities.Order;
import com.example.userservice.entities.Pig;
import com.example.userservice.entities.Stern;
import com.example.userservice.entities.User;
import com.example.userservice.services.FarmService;
import com.example.userservice.services.FarmerService;
import com.example.userservice.services.FatteningDayService;
import com.example.userservice.services.FeedbacksService;
import com.example.userservice.services.OrderService;
import com.example.userservice.services.PageService;
import com.example.userservice.services.PigService;
import com.example.userservice.services.SternService;
import com.example.userservice.services.UserService;
import com.example.userservice.services.WeightService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {
    private final UserService userServiceImpl;
    private final FeedbacksService feedbacksServiceImpl;
    private final FarmerService farmerServiceImpl;
    private final FarmService farmServiceImpl;
    private final SternService sternServiceImpl;
    private final PigService pigServiceImpl;
    private final WeightService weightServiceImpl;
    private final FatteningDayService fatteningDayServiceImpl;
    private final OrderService orderServiceImpl;

    @Override
    public Model setProperties4UserPage(@Nullable String login, Model model){
        boolean isUserHomePage = Objects.isNull(login);

        if(isUserHomePage){
            login = getUserLogin();
        }
        User user = userServiceImpl.getUserByLogin(login);
        Farmer farmer = farmerServiceImpl.findFarmerById(user.getFarmerId());
        List<Feedback> feedbacks = feedbacksServiceImpl.findFeedbacksByFarmerId(user.getFarmerId());
        List<String> names = feedbacks.stream()
                .map(feedback -> farmerServiceImpl.findFarmerById(feedback.getFarmerId()).getName())
                .collect(Collectors.toList());
        model.addAttribute("user", user);
        model.addAttribute("farmer", farmer);
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("names", names);
        model.addAttribute("isUserHomePage", isUserHomePage);
        return model;
    }

    private String getUserLogin(){
        return ((UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
    }

    @Override
    public Model setProperties4Statistic(Model model){
        String login = getUserLogin();

        User user = userServiceImpl.getUserByLogin(login);
        Farmer farmer = farmerServiceImpl.findFarmerById(user.getFarmerId());
        Farm farm = farmServiceImpl.findFarmById(farmer.getFarmId());
        List<Stern> sterns = sternServiceImpl.findSternsByFarmerId(farmer.getId());
        List<Pig> pigs = pigServiceImpl.findPigsByFarmerId(farmer.getId());
        // It is better to take the list of weights in one query, but it needs thinking, and time is short
        List<Double> weights = pigs.stream()
                .map(this::getLastPigWeight)
                .toList();
        List<FatteningDay> fatteningDays = fatteningDayServiceImpl.findDaysByFarmCode(farm.getFarmCode());

        model.addAttribute("farm", farm);
        model.addAttribute("sterns", sterns);
        model.addAttribute("pigs", pigs);
        model.addAttribute("weights", weights);
        model.addAttribute("fatteningDays", fatteningDays);
        return model;
    }

    private Double getLastPigWeight(Pig pig){
        return weightServiceImpl.findCurrentWeightByPigId(pig.getId()).getWeight();
    }

    @Override
    public Model setProperties4Market(Model model,
         int page, int size, boolean sortByName, boolean sortByPrice) {

        String login = getUserLogin();
        User user = userServiceImpl.getUserByLogin(login);
        Farmer farmer = farmerServiceImpl.findFarmerById(user.getFarmerId());

        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Order> pageTuts;
        if (sortByName) {
            pageTuts = orderServiceImpl.findByIdContainingIgnoreCase("type", pageable);
        } else if(sortByPrice) {
            pageTuts = orderServiceImpl.findByIdContainingIgnoreCase("cost", pageable);
        } else{
            pageTuts = orderServiceImpl.findAll(pageable);
        }
        model.addAttribute("productPage", pageTuts);

        return model;
    }


}
