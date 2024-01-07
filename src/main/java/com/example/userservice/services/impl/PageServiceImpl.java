package com.example.userservice.services.impl;

import com.example.userservice.entities.Farm;
import com.example.userservice.entities.Farmer;
import com.example.userservice.entities.FatteningDay;
import com.example.userservice.entities.Feedback;
import com.example.userservice.entities.Pig;
import com.example.userservice.entities.PigForSale;
import com.example.userservice.entities.Stern;
import com.example.userservice.entities.SternForSale;
import com.example.userservice.entities.User;
import com.example.userservice.services.FarmService;
import com.example.userservice.services.FarmerService;
import com.example.userservice.services.FatteningDayService;
import com.example.userservice.services.FeedbacksService;
import com.example.userservice.services.PageService;
import com.example.userservice.services.PigForSaleService;
import com.example.userservice.services.PigService;
import com.example.userservice.services.SternForSaleService;
import com.example.userservice.services.SternService;
import com.example.userservice.services.UserService;
import com.example.userservice.services.WeightService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    private final SternForSaleService sternForSaleServiceImpl;
    private final PigForSaleService pigForSaleService;

    private static final Integer STERN = 1;
    private static final Integer PIG = 2;
    private static final Double FATTENING_DAY = 0.04;
    private static final Double USUAL_DAY = 0.025;

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
        List<SternForSale> sternsOnMarket = sternForSaleServiceImpl.getByFarmerId(farmer.getId());
        List<PigForSale> pigForSales = pigForSaleService.getByFarmerId(farmer.getId());
        List<Pig> dtosForSellingPigs = pigForSales.stream()
                .map((p) -> pigServiceImpl.findPigById(p.getPigId()))
                .toList();
        List<String> nameSterns = sternsOnMarket.stream()
                .map((st) -> sternServiceImpl.findSternById(st.getSternId()).getType())
                .toList();
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
        model.addAttribute("sternsOnMarket", sternsOnMarket);
        model.addAttribute("nameSterns", nameSterns);
        model.addAttribute("pigForSales", pigForSales);
        model.addAttribute("dtosForSellingPigs", dtosForSellingPigs);
        return model;
    }

    private Double getLastPigWeight(Pig pig){
        return weightServiceImpl.findCurrentWeightByPigId(pig.getId()).getWeight();
    }

   /* @Override
    public Model setProperties4Market(Model model, int page, int size,
                                      String sortByName, String sortByPrice) {

        boolean costil1 = sortByName.equals("on");
        boolean costil2 = sortByPrice.equals("on");

        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Product> pageTuts;
        if (costil1) {
            pageTuts = productServiceImpl.findAllByOrderByType(pageable);
        } else if(costil2) {
            pageTuts = productServiceImpl.findAllByOrderByCost(pageable);
        } else {
            pageTuts = productServiceImpl.findAll(pageable);
        }
        List<Product> products =  pageTuts.getContent();
        // лист инфы которая должна быть в таблице
        List<ProductDTO> productDtos = new ArrayList<>(products.size());
        for(var product : products){
            Farmer owner = farmerServiceImpl.findFarmerById(product.getFarmerId());
            //userId = farmerId condition :-)
            User ownerUser = userServiceImpl.getUserById(owner.getId());
            if(Objects.equals(product.getType(), PIG)){
                Pig pig = pigServiceImpl.findPigById(product.getProductId());
                Double weight = getLastPigWeight(pig);
                ProductDTO productDTO =
                        new ProductDTO(pig.getBreed(), weight, "Свинья" , getFio(owner), ownerUser.getLogin(), product.getCost());
                productDtos.add(productDTO);
            } else if (Objects.equals(product.getType(), STERN)) {
                Stern stern = sternServiceImpl.findSternById(product.getProductId());
                ProductDTO productDTO =
                        new ProductDTO(stern.getType(), stern.getWeight(), "Зерно", getFio(owner), ownerUser.getLogin(), product.getCost());
                productDtos.add(productDTO);
            }
        }
        model.addAttribute("productPage", pageTuts);
        model.addAttribute("currentPage", pageable.getPageNumber() + 1);
        model.addAttribute("productDtos", productDtos);
        model.addAttribute("sortByName", costil1);
        model.addAttribute("sortByPrice", costil2);

        return model;
    }*/

    private String getFio(Farmer farmer){
        return farmer.getName() + " " +
                farmer.getSurname().substring(0, 1) + " " +
                farmer.getPatronymic().substring(0, 1);
    }

 /*   @Override
    public Model setProperties4Predict(Model model) {

        String login = getUserLogin();
        User user = userServiceImpl.getUserByLogin(login);
        Farmer farmer = farmerServiceImpl.findFarmerById(user.getFarmerId());
        Farm farm = farmServiceImpl.findFarmById(farmer.getFarmId());
        List<FatteningDay> fatteningDays = fatteningDayServiceImpl.findDaysByFarmCode(farm.getFarmCode());
        List<Stern> sterns = sternServiceImpl.findSternsByFarmerId(farmer.getId());
        List<Pig> pigs = pigServiceImpl.findPigsByFarmerId(farmer.getId());
        double sternWeight = sterns.stream()
                .mapToDouble(Stern::getWeight)
                .sum();// вес корма всего
        double meatWeight = pigs.stream()
                .mapToDouble(this::getLastPigWeight)
                .sum();// вес мяса всего
        LocalDate endDay = dayToEndCorm(sternWeight, meatWeight, fatteningDays);// на сколько дней хватит корма с учетом откормочных дней
        boolean cormAlarm = ChronoUnit.DAYS.between(LocalDate.now(), endDay) < 5;
        double plusMeat = sternWeight / (USUAL_DAY * 100.);// прибавка в весе свиней (в кг мяса)
        List<Product> products = productServiceImpl.findAll();

        double medianMeatPrice = medianMeatPrice(products); // средняя цена на мясо на рынке за кг
        double medianSternPrice = medianSternPrice(products);// средняя цена на корм на рынке за кг
        double profit = (plusMeat * medianMeatPrice) - (sternWeight * medianSternPrice); // расчетная прибыль
        if(profit < 10_000)
            profit = -1. * profit;
        boolean isProfitBad  = profit <= 100; // расчетная прибыль

        model.addAttribute("sternWeight", sternWeight);
        model.addAttribute("cormAlarm", cormAlarm);
        model.addAttribute("endDay", endDay);
        model.addAttribute("medianSternPrice", medianSternPrice);
        model.addAttribute("plusMeat", plusMeat);
        model.addAttribute("medianMeatPrice", medianMeatPrice);
        model.addAttribute("meatWeight", meatWeight);
        model.addAttribute("profit", profit);
        model.addAttribute("isProfitBad", isProfitBad);

        return null;
    }

    private double medianMeatPrice(List<Product> products) {
        return products.stream()
                .filter(product -> PIG.equals(product.getType()))
                .mapToDouble(product -> {
                    Pig pig = pigServiceImpl.findPigById(product.getProductId());
                    return getLastPigWeight(pig);
                })
                .sum();
    }

    private double medianSternPrice(List<Product> products) {
        return products.stream()
                .filter(product -> STERN.equals(product.getType()))
                .mapToDouble(product -> {
                    Stern stern = sternServiceImpl.findSternById(product.getProductId());
                    return stern.getWeight();
                })
                .sum();
    }*/



    private LocalDate dayToEndCorm(Double allCorm, Double pigsWeight, List<FatteningDay> fatteningDays){
        LocalDate start = LocalDate.now();
        Double minFeedConsumptionPerDay = pigsWeight * USUAL_DAY;

        long predictDays = (long)(allCorm / minFeedConsumptionPerDay);
        LocalDate end = start.plusDays(predictDays);
        long days = 0;
        for(var fatteningDay : fatteningDays){
            days+=intersect(start, end, fatteningDay);
        }
        predictDays -= Math.ceil((pigsWeight * (FATTENING_DAY - USUAL_DAY)) / minFeedConsumptionPerDay);
        return start.plusDays(predictDays);
    }

    private long intersect(LocalDate start, LocalDate end, FatteningDay fatteningDay){
        return ChronoUnit.DAYS.between(
                (start.isAfter(fatteningDay.getDateStart())
                    ? start
                    : fatteningDay.getDateStart()),
                (end.isBefore(fatteningDay.getDateEnd())
                    ? end
                    : fatteningDay.getDateEnd())
        );
    }

}
