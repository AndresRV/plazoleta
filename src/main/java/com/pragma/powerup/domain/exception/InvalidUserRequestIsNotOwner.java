package com.pragma.powerup.domain.exception;

public class InvalidUserRequestIsNotOwner extends RuntimeException {
    public InvalidUserRequestIsNotOwner(String message) {
        super(message);
    }
}
