package com.pragma.powerup.domain.exception;

public class InvalidOwnerUser extends RuntimeException {
    public InvalidOwnerUser(String message) {
        super(message);
    }
}
