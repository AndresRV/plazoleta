package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Dish;

import java.util.List;

public interface IDishServicePort {
    void saveDish(Dish dish, boolean userRequestIsOwner);
    Dish  getDishByNameAndIdRestaurant(String name, Long idRestaurant);
    void updateDish(Dish dish, boolean userRequestIsOwner);
    List<Dish> getPagedDishes(Long idCategory, int page, int size);
}
