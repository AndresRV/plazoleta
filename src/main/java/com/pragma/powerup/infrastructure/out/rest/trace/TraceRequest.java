package com.pragma.powerup.infrastructure.out.rest.trace;

import com.pragma.powerup.domain.model.OrderStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TraceRequest {
    private Long idOrder;
    private Long idClient;
    private OrderStatusEnum orderStatusOld;
    private OrderStatusEnum orderStatusNew;
    private Long idChef;
}