package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.Utils.Constants;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.exception.OrderActiveException;
import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.OrderStatusEnum;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;


@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;

    @Override
    public Order saveOrder(Order order) {
        ValidateOrdersNotActiveByUserInRestaurant(order.getIdClient(), order.getIdRestaurant());

        order.setDateTimeOrder(ZonedDateTime.now(ZoneId.of(Constants.TIME_ZONE)).toLocalDateTime());
        order.setOrderStatusEnum(OrderStatusEnum.PENDING);
        return orderPersistencePort.saveOrder(order);
    }

    private void ValidateOrdersNotActiveByUserInRestaurant(Long idClient, Long idRestaurant) {
        List<Order> orderList = orderPersistencePort.getAllOrdersByIdClientAndIdRestaurant(idClient, idRestaurant);

        if (orderList.stream()
                     .anyMatch(order -> order.getOrderStatusEnum().equals(OrderStatusEnum.PENDING) ||
                                        order.getOrderStatusEnum().equals(OrderStatusEnum.ACCEPTED) ||
                                        order.getOrderStatusEnum().equals(OrderStatusEnum.READY))) {
            throw new OrderActiveException(Constants.ORDER_ACTIVE_EXISTS);
        }
    }
}
