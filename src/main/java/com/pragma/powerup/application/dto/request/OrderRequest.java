package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private Long idClient;
    private Long idChef;
    private Long restaurantNit;
    //TODO: MEJORA se puede probar set en vez de list
    private List<OrderDishRequest> orderDishRequestList;
}
