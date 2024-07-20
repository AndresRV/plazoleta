package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.infrastructure.exception.DishAlreadyExistsException;
import com.pragma.powerup.infrastructure.exception.DishNotFoundException;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public void saveDish(Dish dish) {
        if (dishRepository.findByNameAndIdRestaurant(dish.getName(), dish.getIdRestaurant()).isPresent()) {
            throw new DishAlreadyExistsException();
        }

        dishRepository.save(dishEntityMapper.toEntity(dish));
    }

    @Override
    public Dish getDishByNameAndIdRestaurant(String name, Long idRestaurant) {
        return dishEntityMapper.toDish(dishRepository.findByNameAndIdRestaurant(name, idRestaurant)
                .orElseThrow(DishNotFoundException::new));
    }

    @Override
    public void updateDish(Dish dish) {
        dishRepository.save(dishEntityMapper.toEntity(dish));
    }
}
