package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.Utils.Constants;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.exception.InvalidClaimPinException;
import com.pragma.powerup.domain.exception.InvalidStatusException;
import com.pragma.powerup.domain.exception.OrderActiveException;
import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.OrderStatusEnum;
import com.pragma.powerup.domain.model.Trace;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.domain.spi.ITraceRestPort;
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
    private final ITraceRestPort traceRestPort;

    @Override
    public Order saveOrder(Order order) {
        ValidateOrdersNotActiveByUserInRestaurant(order.getIdClient(), order.getIdRestaurant());

        order.setDateTimeOrder(ZonedDateTime.now(ZoneId.of(Constants.TIME_ZONE)).toLocalDateTime());
        order.setOrderStatusEnum(OrderStatusEnum.PENDING);
        Order newOrder = orderPersistencePort.saveOrder(order);

        RecordTrace(newOrder, null);
        return newOrder;
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

        ValidatePreviousStateBeforeAcceptOrCancel(order);
        OrderStatusEnum orderStatusOld = order.getOrderStatusEnum();

        order.setOrderStatusEnum(OrderStatusEnum.ACCEPTED);
        order.setIdChef(idUserRequest);
        orderPersistencePort.updateOrder(order);

        RecordTrace(order, orderStatusOld);
    }

    @Override
    public void readyOrder(Long idOrder) {
        Order order = orderPersistencePort.findById(idOrder);

        ValidatePreviousStateBeforeReady(order);
        OrderStatusEnum orderStatusOld = order.getOrderStatusEnum();

        order.setOrderStatusEnum(OrderStatusEnum.READY);
        order.setClaimPin("codigo" + order.getId());
        orderPersistencePort.updateOrder(order);
        //TODO: enviar notificacion a cliente
        RecordTrace(order, orderStatusOld);
    }

    @Override
    public void deliveredOrder(Long idOrder, String claimPin) {
        Order order = orderPersistencePort.findById(idOrder);

        ValidatePreviousStateBeforeDelivered(order);
        ValidateClaimPin(claimPin, order);
        OrderStatusEnum orderStatusOld = order.getOrderStatusEnum();

        order.setOrderStatusEnum(OrderStatusEnum.DELIVERED);
        orderPersistencePort.updateOrder(order);
        RecordTrace(order, orderStatusOld);
    }

    @Override
    public void cancelledOrder(Long idOrder) {
        Order order = orderPersistencePort.findById(idOrder);

        ValidatePreviousStateBeforeAcceptOrCancel(order);
        OrderStatusEnum orderStatusOld = order.getOrderStatusEnum();

        order.setOrderStatusEnum(OrderStatusEnum.CANCELLED);
        orderPersistencePort.updateOrder(order);
        RecordTrace(order, orderStatusOld);
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

    private void ValidatePreviousStateBeforeAcceptOrCancel(Order order) {
        if(!order.getOrderStatusEnum().equals(OrderStatusEnum.PENDING)) {
            throw new InvalidStatusException(Constants.ORDER_INVALID_STATUS);
        }
    }

    private void ValidatePreviousStateBeforeReady(Order order) {
        if(!order.getOrderStatusEnum().equals(OrderStatusEnum.ACCEPTED)) {
            throw new InvalidStatusException(Constants.ORDER_INVALID_STATUS);
        }
    }

    private void ValidatePreviousStateBeforeDelivered(Order order) {
        if(!order.getOrderStatusEnum().equals(OrderStatusEnum.READY)) {
            throw new InvalidStatusException(Constants.ORDER_INVALID_STATUS);
        }
    }

    private void ValidateClaimPin(String claimPin, Order order) {
        if(!claimPin.equals(order.getClaimPin())) {
            throw new InvalidClaimPinException(Constants.INVALID_CLAIM_PIN);
        }
    }

    private void RecordTrace(Order order, OrderStatusEnum orderStatusOld) {
        Trace trace = new Trace(
                order.getId(),
                order.getIdClient(),
                orderStatusOld,
                order.getOrderStatusEnum(),
                order.getIdChef()
        );
        traceRestPort.saveTrace(trace);
    }
}
