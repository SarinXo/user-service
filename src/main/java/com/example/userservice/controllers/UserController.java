package com.example.userservice.controllers;

import com.example.userservice.dto.UserRequest;
import com.example.userservice.entities.User;
import com.example.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping("/{login}")
    public ResponseEntity<User> getUser(@PathVariable String login){
        User user = userService.getUser(login);
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody UserRequest userRequest){
        User user = userService.addUser(userRequest);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/is-exist/{login}")
    public ResponseEntity<Boolean> isExistUser(@PathVariable String login){
        Boolean isExist = userService.isExist(login);
        return ResponseEntity.ok(isExist);
    }

    @PostMapping("/is-correct")
    public ResponseEntity<Boolean> isCorrectUser(@RequestBody UserRequest userRequest){
        Boolean isExist = userService.isCorrectUser(userRequest);
        return ResponseEntity.ok(isExist);
    }
}
