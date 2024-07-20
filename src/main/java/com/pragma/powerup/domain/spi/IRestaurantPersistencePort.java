package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Restaurant;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
    Restaurant getRestauranByNit(Long nit);
}
