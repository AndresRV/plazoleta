package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Long idClient;
    private Long idChef;
    private Long restaurantNit;
    //lista de platos
}
