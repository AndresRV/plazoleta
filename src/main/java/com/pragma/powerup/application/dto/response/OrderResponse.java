package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.application.dto.OrderDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private List<OrderDto> orderDto;
    private Integer pages;
}
