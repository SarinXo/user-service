package com.example.userservice.services;

import jakarta.annotation.Nullable;
import org.springframework.ui.Model;

public interface PageService {
    Model setProperties4UserPage(@Nullable String login, Model model);
    Model setProperties4Statistic(Model model);

    Model setProperties4MarketPigs(Model model, int page, int size,
                                   String sortByName, String sortByPrice, String keyWord);

/*    Model setProperties4Market(Model model, int page, int size, String sortByName, String sortByPrice);

    Model setProperties4Predict(Model model);*/
}
