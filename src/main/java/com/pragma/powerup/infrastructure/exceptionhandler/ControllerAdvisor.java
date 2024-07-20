package com.pragma.powerup.infrastructure.exceptionhandler;

import com.pragma.powerup.domain.exception.InvalidNameException;
import com.pragma.powerup.domain.exception.InvalidPhoneNumberException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.exception.RestaurantAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(RestaurantAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantAlreadyExistsException(
            RestaurantAlreadyExistsException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.RESTAURANT_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<Map<String, String>> handleInvalidNameException(
            InvalidNameException invalidNameException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, invalidNameException.getMessage()));
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPhoneNumberException(
            InvalidPhoneNumberException invalidPhoneNumberException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, invalidPhoneNumberException.getMessage()));
    }
}