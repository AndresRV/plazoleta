package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.RestaurantRequest;
import com.pragma.powerup.application.dto.response.RestaurantResponse;

import java.util.List;
public interface IRestaurantHandler {
    void saveRestaurant(RestaurantRequest restaurantRequest);
    List<RestaurantResponse> getPagedRestaurants(int page, int size);
}
