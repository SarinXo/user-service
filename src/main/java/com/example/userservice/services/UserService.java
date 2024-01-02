package com.example.userservice.services;


import com.example.userservice.dto.UserDto;
import com.example.userservice.entities.User;

import java.util.List;

public interface UserService {

    User getUserByLogin(String login);

    User addUser(UserDto userDto);

    Boolean isExist(String login);

    Boolean isCorrectUser(UserDto userDto);

    List<User> findAll();
}
