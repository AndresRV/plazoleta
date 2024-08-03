package com.pragma.powerup.infrastructure.input.rest;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.pragma.powerup.application.dto.request.DishRequest;
import com.pragma.powerup.application.dto.response.DishResponse;
import com.pragma.powerup.application.dto.response.RestaurantResponse;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.infrastructure.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dish")
@RequiredArgsConstructor
@PreAuthorize("denyAll()")
public class DishRestController {
    private final IDishHandler dishHandler;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("")
    @PreAuthorize("hasRole('Propietario')")
    public ResponseEntity<Void> saveDish(HttpServletRequest request, @RequestBody DishRequest dishRequest) {
        Long idUserRequest = extractIdUserRequest(request.getHeader(HttpHeaders.AUTHORIZATION));
        dishHandler.saveDish(dishRequest, idUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("")
    @PreAuthorize("hasRole('Propietario')")
    public ResponseEntity<Void> updateDish(HttpServletRequest request, @RequestBody DishRequest dishRequest) {
        Long idUserRequest = extractIdUserRequest(request.getHeader(HttpHeaders.AUTHORIZATION));
        dishHandler.updateDish(dishRequest, idUserRequest);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("")
    @PreAuthorize("hasRole('Propietario')")
    public ResponseEntity<Void> activeDish(HttpServletRequest request, @RequestBody DishRequest dishActiveRequest) {
        Long idUserRequest = extractIdUserRequest(request.getHeader(HttpHeaders.AUTHORIZATION));
        dishHandler.activeDish(dishActiveRequest, idUserRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{categoryName}")
    @PreAuthorize("hasRole('Cliente')")
    public ResponseEntity<List<DishResponse>> getPagedDishes(@PathVariable String categoryName,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "2") int size) {
        return ResponseEntity.ok(dishHandler.getPagedDishes(categoryName, page, size));
    }

    private Long extractIdUserRequest(String jwtToken) {
        Long idUserResquest = 0L;

        if (jwtToken != null) {
            jwtToken = jwtToken.substring(7);
            DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);
            idUserResquest = Long.parseLong(jwtUtils.getSpecificClaim(decodedJWT, "ui").toString());
        }

        return idUserResquest;
    }
}
