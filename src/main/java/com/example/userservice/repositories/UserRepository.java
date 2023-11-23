package com.example.userservice.repositories;

import com.example.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User getUserByLogin(String login);

    Boolean existsUserByLogin(String login);

}
