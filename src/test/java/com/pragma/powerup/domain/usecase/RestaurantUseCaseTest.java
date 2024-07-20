package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.InvalidNameException;
import com.pragma.powerup.domain.exception.InvalidPhoneNumberException;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RestaurantUseCaseTest {
    @Mock
    private IRestaurantPersistencePort restaurantPersistentPort;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant(
                1L,
                "xx",
                "xx",
                1L,
                "+123456789123",
                "xx",
                1L
        );
    }

    @Test
    void saveRestaurant() {
        restaurantUseCase.saveRestaurant(restaurant);

        verify(restaurantPersistentPort).saveRestaurant(restaurant);
    }

    @Test
    void saveRestaurantInvalidName() {
        restaurant.setName("1");

        Exception exception = Assertions.assertThrows(InvalidNameException.class, () -> restaurantUseCase.saveRestaurant(restaurant));

        assertEquals("Invalid name", exception.getMessage());
    }

    @Test
    void saveRestaurantInvalidPhoneNumber() {
        restaurant.setPhoneNumber("+123");

        Exception exception = Assertions.assertThrows(InvalidPhoneNumberException.class, () -> restaurantUseCase.saveRestaurant(restaurant));

        Assertions.assertEquals("Invalid number phone", exception.getMessage());
    }
}