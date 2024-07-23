package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.exception.InvalidPhoneNumberException;
import com.pragma.powerup.domain.exception.InvalidUserRequestIsNotOwner;
import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;

    @Override
    public void saveDish(Dish dish, boolean userRequestIsOwner) {
        validateUserRequestIsOwner(userRequestIsOwner);
        dish.setActive(true);
        dishPersistencePort.saveDish(dish);
    }

    @Override
    public Dish getDishByNameAndIdRestaurant(String name, Long idRestaurant) {
        return dishPersistencePort.getDishByNameAndIdRestaurant(name, idRestaurant);
    }

    @Override
    public void updateDish(Dish dish, boolean userRequestIsOwner) {
        validateUserRequestIsOwner(userRequestIsOwner);
        dishPersistencePort.updateDish(dish);
    }

    private static void validateUserRequestIsOwner(boolean userRequestIsOwner) {
        if (!userRequestIsOwner)
            throw new InvalidUserRequestIsNotOwner("Request user  is not the owner of the restaurant");
    }
}
