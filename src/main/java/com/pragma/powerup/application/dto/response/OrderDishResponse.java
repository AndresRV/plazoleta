package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDishResponse {
    private String name;
    private String category;
    private String description;
    private String imageUrl;
}