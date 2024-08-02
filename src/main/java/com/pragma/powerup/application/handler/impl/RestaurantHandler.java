package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RestaurantRequest;
import com.pragma.powerup.application.dto.response.RestaurantResponse;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import com.pragma.powerup.application.mapper.IRestaurantRequestMapper;
import com.pragma.powerup.application.mapper.IRestaurantResponseMapper;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {
    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;

    @Override
    public void saveRestaurant(RestaurantRequest restaurantRequest) {
        restaurantServicePort.saveRestaurant(restaurantRequestMapper.toRestaurant(restaurantRequest));
    }

    @Override
    public List<RestaurantResponse> getPagedRestaurants(int page, int size) {
        return restaurantResponseMapper.toResponseList(restaurantServicePort.getPagedRestaurants(page, size));
    }
}
