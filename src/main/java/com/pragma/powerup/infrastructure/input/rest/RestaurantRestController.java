package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.RestaurantRequest;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {
    private final IRestaurantHandler restaurantHandler;

    @PostMapping("")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<Void> saveRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        restaurantHandler.saveRestaurant(restaurantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
