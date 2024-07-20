package com.pragma.powerup.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    NO_CATEGORY_FOUND("No data found for the requested category"),
    NO_RESTAURANT_FOUND("No data found for the requested restaurant"),
    NO_DATA_FOUND("No data found for the requested petition"),
    RESTAURANT_ALREADY_EXISTS("There is a restaurant with the same NIT");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}