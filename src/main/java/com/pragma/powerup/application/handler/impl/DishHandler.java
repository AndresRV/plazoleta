package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.DishRequest;
import com.pragma.powerup.application.dto.response.DishResponse;
import com.pragma.powerup.application.dto.response.RestaurantResponse;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.application.mapper.IDishRequestMapper;
import com.pragma.powerup.application.mapper.IDishResponseMapper;
import com.pragma.powerup.domain.api.ICategoryServicePort;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.Category;
import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {
    private final IDishServicePort dishServicePort;
    private final ICategoryServicePort categoryServicePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;

    @Override
    public void saveDish(DishRequest dishRequest, Long idUserRequest) {
        Category category = categoryServicePort.getCategoryByCategoryName(dishRequest.getCategoryName());
        Restaurant restaurant = restaurantServicePort.getRestauranByNit(dishRequest.getRestaurantNit());

        Dish dish = dishRequestMapper.toDish(dishRequest);
        dish.setIdCategory(category.getId());
        dish.setIdRestaurant(restaurant.getId());

        dishServicePort.saveDish(dish, restaurant.getIdOwner() == idUserRequest);
    }

    @Override
    public void updateDish(DishRequest dishRequest, Long idUserRequest) {
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

        dishServicePort.updateDish(newDish, restaurant.getIdOwner() == idUserRequest);
    }

    @Override
    public void activeDish(DishRequest dishActiveRequest, Long idUserRequest) {
        Restaurant restaurant = restaurantServicePort.getRestauranByNit(dishActiveRequest.getRestaurantNit());

        Dish oldDish = dishServicePort.getDishByNameAndIdRestaurant(dishActiveRequest.getName(), restaurant.getId());

        Dish newDish = dishRequestMapper.toDish(dishActiveRequest);
        newDish.setId(oldDish.getId());
        newDish.setIdCategory(oldDish.getIdCategory());
        newDish.setIdRestaurant(oldDish.getIdRestaurant());
        newDish.setImageUrl(oldDish.getImageUrl());
        newDish.setDescription(oldDish.getDescription());
        newDish.setPrice(oldDish.getPrice());

        if (newDish.getActive() == null)
            newDish.setActive(oldDish.getActive());

        dishServicePort.updateDish(newDish, restaurant.getIdOwner() == idUserRequest);
    }

    @Override
    public List<DishResponse> getPagedDishes(String categoryName, int page, int size) {
        Category category = categoryServicePort.getCategoryByCategoryName(categoryName);
        return dishResponseMapper.toResponseList(dishServicePort.getPagedDishes(category.getId(), page, size));
    }
}
