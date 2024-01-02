package com.example.userservice.repositories;

import com.example.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByLogin(String login);

    List<User> findAll();

    Boolean existsUserByLogin(String login);

    Optional<User> findByLogin(String login);
}
