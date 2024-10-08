package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.OrderRequest;
import com.pragma.powerup.application.dto.response.OrderResponse;
import com.pragma.powerup.domain.model.OrderStatusEnum;

public interface IOrderHandler {
    void saveOrder(OrderRequest orderRequest);
    OrderResponse getPagedOrders(Integer documentNumberUserRequest, OrderStatusEnum orderStatusEnum, int page, int size);
    void assignOrder(Long idOrder, Long idUserRequest);
    void readyOrder(Long idOrder);
    void deliveredOrder(Long idOrder, String claimPin);
    void cancelledOrder(Long idOrder);
}
