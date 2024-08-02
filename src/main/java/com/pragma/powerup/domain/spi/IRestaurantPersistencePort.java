package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
    Restaurant getRestauranByNit(Long nit);
    List<Restaurant> getPagedRestaurants(int page, int size);
}
