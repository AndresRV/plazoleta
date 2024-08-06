package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDishRequest {
    private String dishName;
    private Integer quantity;
}
