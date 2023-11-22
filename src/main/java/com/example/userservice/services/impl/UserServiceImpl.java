package com.example.userservice.services.impl;


import com.example.userservice.entities.User;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> get(String login) {
        return Optional.of(userRepository.getUserByLogin(login));
    }

    @Override
    public Boolean isExist(String login) {
        return userRepository.existsUserByLogin(login);
    }
}
