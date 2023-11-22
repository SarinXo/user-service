package com.example.userservice.controllers;

import com.example.userservice.entities.User;
import com.example.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @PostMapping("/entity")
    public ResponseEntity<User> getUser(@RequestBody String login){
        return ResponseEntity.ok(userService.get(login).get());
    }

    @GetMapping("/is-exist")
    public ResponseEntity<Boolean> isExistUser(@RequestBody String login){
        return ResponseEntity.ok(userService.isExist(login));
    }
    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody String login){
        return ResponseEntity.ok(userService.get(login).get());
    }
}
