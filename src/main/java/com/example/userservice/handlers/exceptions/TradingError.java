package com.example.userservice.handlers.exceptions;

public class TradingError extends RuntimeException {
    public TradingError(String lowBalance) {
        super(lowBalance);
    }
}
