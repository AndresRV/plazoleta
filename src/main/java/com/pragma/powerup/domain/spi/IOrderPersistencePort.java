package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.OrderStatusEnum;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderPersistencePort {
    Order saveOrder(Order order);
    List<Order> getAllOrdersByIdClientAndIdRestaurant(Long idClient, Long idRestaurant);
    Page<Order> getPagedOrdersByIdRestaurantAndOrderStatusEnum(Long idRestaurant, OrderStatusEnum orderStatusEnum, int page, int size);
    Order findById(Long idOrder);
    void updateOrder(Order order);
}
