package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.DishRequest;

public interface IDishHandler {
    void saveDish(DishRequest dishRequest, Long idUserRequest);
    void updateDish(DishRequest dishRequest, Long idUserRequest);
    void activeDish(DishRequest dishActiveRequest, Long idUserRequest);
}
