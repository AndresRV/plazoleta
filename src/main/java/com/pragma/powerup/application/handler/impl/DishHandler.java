package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.DishRequest;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.application.mapper.IDishRequestMapper;
import com.pragma.powerup.domain.api.ICategoryServicePort;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.Category;
import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {
    private final IDishServicePort dishServicePort;
    private final ICategoryServicePort categoryServicePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final IDishRequestMapper dishRequestMapper;

    @Override
    public void saveDish(DishRequest dishRequest) {
        Category category = categoryServicePort.getCategoryByCategoryName(dishRequest.getCategoryName());
        Restaurant restaurant = restaurantServicePort.getRestauranByNit(dishRequest.getRestaurantNit());

        Dish dish = dishRequestMapper.toDish(dishRequest);
        dish.setIdCategory(category.getId());
        dish.setIdRestaurant(restaurant.getId());

        dishServicePort.saveDish(dish);
    }

    @Override
    public void updateDish(DishRequest dishRequest) {
        Restaurant restaurant = restaurantServicePort.getRestauranByNit(dishRequest.getRestaurantNit());

        Dish oldDish = dishServicePort.getDishByNameAndIdRestaurant(dishRequest.getName(), restaurant.getId());

        Dish newDish = dishRequestMapper.toDish(dishRequest);
        newDish.setId(oldDish.getId());
        newDish.setIdCategory(oldDish.getIdCategory());
        newDish.setIdRestaurant(oldDish.getIdRestaurant());
        newDish.setImageUrl(oldDish.getImageUrl());
        newDish.setActive(oldDish.getActive());

        if (newDish.getDescription() == null)
            newDish.setDescription(oldDish.getDescription());

        if (newDish.getPrice() == null)
            newDish.setPrice(oldDish.getPrice());

        dishServicePort.updateDish(newDish);
    }
}
