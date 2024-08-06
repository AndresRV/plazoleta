package com.pragma.powerup.application.dto;

import com.pragma.powerup.application.dto.response.OrderDishResponse;
import com.pragma.powerup.domain.model.OrderStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private Long idClient;
    private LocalDateTime dateTimeOrder;
    private OrderStatusEnum orderStatusEnum;
    private Long idChef;
    //TODO: MEJORA se puede probar set en vez de list
    private List<OrderDishResponse> orderDishResponseList;
}
