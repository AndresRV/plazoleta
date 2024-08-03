package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.infrastructure.exception.DishAlreadyExistsException;
import com.pragma.powerup.infrastructure.exception.DishNotFoundException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

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

    @Override
    public List<Dish> getPagedDishes(Long idCategory, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<DishEntity> dishEntityPage = dishRepository.findByIdCategory(idCategory, pageable);
        if(dishEntityPage.getContent().isEmpty()) {
            throw new NoDataFoundException();
        }
        return dishEntityMapper.toDishList(dishEntityPage.getContent());
    }
}
