package com.example.userservice.services;

import com.example.userservice.dto.UserDto;

public interface AuthService {
    String authenticate(UserDto loginDto);
}
