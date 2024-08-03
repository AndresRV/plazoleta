package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.DishRequest;
import com.pragma.powerup.application.dto.response.DishResponse;
import com.pragma.powerup.application.dto.response.RestaurantResponse;

import java.util.List;

public interface IDishHandler {
    void saveDish(DishRequest dishRequest, Long idUserRequest);
    void updateDish(DishRequest dishRequest, Long idUserRequest);
    void activeDish(DishRequest dishActiveRequest, Long idUserRequest);
    List<DishResponse> getPagedDishes(String categoryName, int page, int size);
}
