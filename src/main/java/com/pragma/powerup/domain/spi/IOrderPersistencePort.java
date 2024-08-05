package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Order;

import java.util.List;

public interface IOrderPersistencePort {
    void saveOrder(Order order);
    List<Order> getAllOrdersByIdClientAndIdRestaurant(Long idClient, Long idRestaurant);
}
