package com.example.userservice.services.impl;


import com.example.userservice.dto.UserRequest;
import com.example.userservice.entities.User;
import com.example.userservice.handlers.exceptions.UserError;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String login) {
        User user = userRepository.getUserByLogin(login);
        if(Objects.isNull(user))
            throw new UserError("user doeesn't not exist in db");
        return user;
    }

    @Override
    public Boolean isExist(String login) {
        return userRepository.existsUserByLogin(login);
    }

    @Override
    public User addUser(UserRequest userRequest) {
        if(isExist(userRequest.getLogin())){
            throw new UserError("User already exist in database");
        }
        User user = User.builder()
                .login(userRequest.getLogin())
                .password(userRequest.getPassword())
                .role("USER")
                .build();
        return userRepository.save(user);
    }

    @Override
    public Boolean isCorrectUser(UserRequest userRequest) {
        User user = getUser(userRequest.getLogin());
        return user.getPassword().equals(userRequest.getPassword());
    }
}
