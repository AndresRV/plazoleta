package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.Utils.Constants;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.exception.InvalidStatusException;
import com.pragma.powerup.domain.exception.OrderActiveException;
import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.OrderStatusEnum;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.domain.spi.IUserRestPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;


@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;
    private final IUserRestPort userRestPort;

    @Override
    public Order saveOrder(Order order) {
        ValidateOrdersNotActiveByUserInRestaurant(order.getIdClient(), order.getIdRestaurant());

        order.setDateTimeOrder(ZonedDateTime.now(ZoneId.of(Constants.TIME_ZONE)).toLocalDateTime());
        order.setOrderStatusEnum(OrderStatusEnum.PENDING);
        return orderPersistencePort.saveOrder(order);
    }

    //TODO: MEJORA en vez de usar Page y compormeter el dominio - se puede usar la definicion fninal de los modelos y dejar Page solo en infraestructura
    @Override
    public Page<Order> getPagedOrders(Integer documentNumberUserRequest, OrderStatusEnum orderStatusEnum, int page, int size) {
        Long idRestaurant = userRestPort.getRestaurantByUser(documentNumberUserRequest);
        return orderPersistencePort.getPagedOrdersByIdRestaurantAndOrderStatusEnum(idRestaurant, orderStatusEnum, page, size);
    }

    @Override
    public void assignOrder(Long idOrder, Long idUserRequest) {
        Order order = orderPersistencePort.findById(idOrder);

        ValidatePreviousStateBeforeAccept(order);

        order.setOrderStatusEnum(OrderStatusEnum.ACCEPTED);
        order.setIdChef(idUserRequest);
        orderPersistencePort.updateOrder(order);
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

    private void ValidatePreviousStateBeforeAccept(Order order) {
        if(!order.getOrderStatusEnum().equals(OrderStatusEnum.PENDING)) {
            throw new InvalidStatusException(Constants.ORDER_INVALID_STATUS);
        }
    }
}
