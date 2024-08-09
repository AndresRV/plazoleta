package com.pragma.powerup.domain.exception;

public class InvalidClaimPinException extends RuntimeException {
    public InvalidClaimPinException(String message) {
        super(message);
    }
}

