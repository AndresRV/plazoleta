package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.OrderStatusEnum;
import org.springframework.data.domain.Page;

public interface IOrderServicePort {
    Order saveOrder(Order order);
    Page<Order> getPagedOrders(Integer documentNumberUserRequest, OrderStatusEnum orderStatusEnum, int page, int size);
    void assignOrder(Long idOrder, Long idUserRequest);
    void readyOrder(Long idOrder, Long idUserRequest);
    void deliveredOrder(Long idOrder, Long idUserRequest, String claimPin);
    /*void cancelledOrder(Long idOrder, Long idUserRequest);*/
}
