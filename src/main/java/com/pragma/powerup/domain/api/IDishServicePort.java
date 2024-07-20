package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Dish;

public interface IDishServicePort {
    void saveDish(Dish dish);
    Dish  getDishByNameAndIdRestaurant(String name, Long idRestaurant);
    void updateDish(Dish dish);
}
