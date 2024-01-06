package com.example.userservice.services;

import jakarta.annotation.Nullable;
import org.springframework.ui.Model;

public interface PageService {
    Model setProperties4UserPage(@Nullable String login, Model model);
    Model setProperties4Statistic(Model model);

    Model setProperties4Market(Model model, int page, int size, boolean sortByName, boolean sortByPrice);

    Model setProperties4Predict(Model model);
}
