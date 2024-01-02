package com.example.userservice.controllers;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entities.User;
import com.example.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping("/{login}")
    public ResponseEntity<User> getUser(@PathVariable String login) {
        User user = userService.getUserByLogin(login);
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) {
        User user = userService.addUser(userDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/is-exist/{login}")
    public ResponseEntity<Boolean> isExistUser(@PathVariable String login) {
        Boolean isExist = userService.isExist(login);
        return ResponseEntity.ok(isExist);
    }

    @GetMapping("/find")
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/is-correct")
    public ResponseEntity<Boolean> isCorrectUser(@RequestBody UserDto userDto) {
        Boolean isCorrect = userService.isCorrectUser(userDto);
        return ResponseEntity.ok(isCorrect);
    }

}