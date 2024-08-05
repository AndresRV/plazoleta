package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.OrderRequest;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.application.mapper.IOrderRequestMapper;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler {
    private final IOrderServicePort orderServicePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final IOrderRequestMapper orderRequestMapper;

    @Override
    public void saveOrder(OrderRequest orderRequest) {
        Restaurant restaurant = restaurantServicePort.getRestauranByNit(orderRequest.getRestaurantNit());

        Order order = orderRequestMapper.toOrder(orderRequest);
        order.setIdRestaurant(restaurant.getId());

        orderServicePort.saveOrder(order);
    }
}
