package com.example.userservice.handlers.exceptions;

public class UserError extends RuntimeException{

    public UserError(String message) {
        super(message);
    }

}
