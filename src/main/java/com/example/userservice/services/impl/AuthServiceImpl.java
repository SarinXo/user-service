/*
package com.example.userservice.services.impl;

import com.example.userservice.config.JwtUtilities;
import com.example.userservice.dto.UserDto;
import com.example.userservice.entities.User;
import com.example.userservice.services.AuthService;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager myAuthenticationManager;
    private final JwtUtilities jwtUtilities;
    private final UserService userService;

    @Override
    public String authenticate(UserDto loginDto) {
        Authentication authentication= myAuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userService.getUserByLogin(authentication.getName());
        String token = jwtUtilities.generateToken(user.getLogin(), List.of(user.getRole().name()));
        return token;
    }

}
*/
