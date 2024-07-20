package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;

    @Override
    public void saveDish(Dish dish) {
        //TODO: valiadar solo propietario (Solo el propietario de un restaurante puede crear platos)
        dish.setActive(true);
        dishPersistencePort.saveDish(dish);
    }

    @Override
    public Dish getDishByNameAndIdRestaurant(String name, Long idRestaurant) {
        return dishPersistencePort.getDishByNameAndIdRestaurant(name, idRestaurant);
    }

    @Override
    public void updateDish(Dish dish) {
        dishPersistencePort.updateDish(dish);
    }
}
