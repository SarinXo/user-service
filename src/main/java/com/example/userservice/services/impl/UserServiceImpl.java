package com.example.userservice.services.impl;

import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.Role;
import com.example.userservice.entities.User;
import com.example.userservice.handlers.exceptions.UserError;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserByLogin(String login) {
        User user = userRepository.getUserByLogin(login);
        if(Objects.isNull(user))
            throw new UserError("user doesn't not exist in db");
        return user;
    }

    @Override
    public Boolean isExist(String login) {
        return userRepository.existsUserByLogin(login);
    }

    @Override
    public User addUser(UserDto userDto) {
        if(isExist(userDto.getUsername())){
            throw new UserError("User already exist in database");
        }
        User user = User.builder()
                .login(userDto.getUsername())
                .password(userDto.getPassword())
                .role(Role.ROLE_USER)
                .build();
        return userRepository.save(user);
    }

    @Override
    public Boolean isCorrectUser(UserDto userDto) {
        User user = getUserByLogin(userDto.getUsername());
        return user.getPassword().equals(userDto.getPassword());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findUserById(id);
    }
}
