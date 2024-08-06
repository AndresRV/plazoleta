package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.OrderDishRequest;
import com.pragma.powerup.application.dto.request.OrderRequest;
import com.pragma.powerup.application.dto.response.OrderResponse;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.application.mapper.IOrderRequestMapper;
import com.pragma.powerup.application.mapper.IOrderResponseMapper;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.api.IOrderDishServicePort;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.OrderStatusEnum;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.model.union.OrderDish;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler {
    private final IOrderServicePort orderServicePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final IDishServicePort dishServicePort;
    private final IOrderDishServicePort orderDishServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderResponseMapper orderResponseMapper;

//TODO: debe conectar a domain
    @Autowired
    private final IOrderPersistencePort orderPersistencePort;

    @Override
    public void saveOrder(OrderRequest orderRequest) {
        Restaurant restaurant = restaurantServicePort.getRestauranByNit(orderRequest.getRestaurantNit());

        Order order = orderRequestMapper.toOrder(orderRequest);
        order.setIdRestaurant(restaurant.getId());
        order.setId(orderServicePort.saveOrder(order).getId());

        for(OrderDishRequest orderDishRequest : orderRequest.getOrderDishRequestList()) {
            Dish dish = dishServicePort.getDishByNameAndIdRestaurant(orderDishRequest.getDishName(), restaurant.getId());
            OrderDish orderDish = new OrderDish(order, dish, orderDishRequest.getQuantity());
            orderDishServicePort.saveOrderDish(orderDish);
        }
    }

    @Override
    public OrderResponse getPagedOrders(OrderStatusEnum orderStatusEnum, int page, int size) {
        //TODO: se debe llevar a caso de uso

        Page<Order> orderList = orderPersistencePort.getPagedOrders(orderStatusEnum, page, size);

        return orderResponseMapper.toResponse(orderList);
    }
}
