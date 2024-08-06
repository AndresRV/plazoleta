package com.pragma.powerup.domain.exception;

public class OrderActiveException extends RuntimeException {
    public OrderActiveException(String message) {
        super(message);
    }
}

