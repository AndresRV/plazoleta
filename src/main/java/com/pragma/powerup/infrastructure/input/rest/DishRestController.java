package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.DishRequest;
import com.pragma.powerup.application.handler.IDishHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dish")
@RequiredArgsConstructor
@PreAuthorize("denyAll()")
public class DishRestController {
    private final IDishHandler dishHandler;

    @PostMapping("")
    @PreAuthorize("hasRole('Propietario')")
    public ResponseEntity<Void> saveDish(@RequestBody DishRequest dishRequest) {
        dishHandler.saveDish(dishRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("")
    @PreAuthorize("hasRole('Propietario')")
    public ResponseEntity<Void> updateDish(@RequestBody DishRequest dishRequest) {
        dishHandler.updateDish(dishRequest);
        return ResponseEntity.noContent().build();
    }
}
