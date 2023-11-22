package com.example.userservice.services;


import com.example.userservice.entities.User;

import java.util.Optional;

public interface UserService {

    Optional<User> get(String login);

    Boolean isExist(String login);
}
