package com.example.userservice.services;


import com.example.userservice.dto.UserRequest;
import com.example.userservice.entities.User;

import java.util.Optional;

public interface UserService {

    User getUser(String login);

    User addUser(UserRequest userRequest);

    Boolean isExist(String login);

    Boolean isCorrectUser(UserRequest userRequest);
}
