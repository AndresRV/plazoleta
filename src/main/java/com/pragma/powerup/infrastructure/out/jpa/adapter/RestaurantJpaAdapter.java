package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.exception.RestaurantAlreadyExistsException;
import com.pragma.powerup.infrastructure.exception.RestaurantNotFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        if (restaurantRepository.findByNit(restaurant.getNit()).isPresent()) {
            throw new RestaurantAlreadyExistsException();
        }
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }

    @Override
    public Restaurant getRestauranByNit(Long nit) {
        return restaurantEntityMapper.toRestaurant(restaurantRepository.findByNit(nit)
                .orElseThrow(RestaurantNotFoundException::new));
    }

    @Override
    public List<Restaurant> getPagedRestaurants(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<RestaurantEntity> restaurantEntityPage = restaurantRepository.findAll(pageable);
        if(restaurantEntityPage.getContent().isEmpty()) {
            throw new NoDataFoundException();
        }
        return restaurantEntityMapper.toRestaurantList(restaurantEntityPage.getContent());
    }
}
