package com.example.userservice.services;

import jakarta.annotation.Nullable;
import org.springframework.ui.Model;

public interface UserPageService {
    Model setProperties4UserPage(@Nullable String login, Model model);
}
