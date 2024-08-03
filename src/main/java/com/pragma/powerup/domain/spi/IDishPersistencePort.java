package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.model.Restaurant;

import java.util.List;

public interface IDishPersistencePort {
    void saveDish(Dish dish);
    Dish getDishByNameAndIdRestaurant(String name, Long idRestaurant);
    void updateDish(Dish dish);
    List<Dish> getPagedDishes(Long idCategory, int page, int size);
}
