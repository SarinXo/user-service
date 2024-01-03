package com.example.userservice.services.impl;

import com.example.userservice.dto.UserAuthDetails;
import com.example.userservice.entities.User;
import com.example.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + login);
        }
        return new UserAuthDetails(user.get());
    }
}
