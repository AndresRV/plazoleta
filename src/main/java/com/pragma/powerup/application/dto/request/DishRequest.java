package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishRequest {
    private String name;
    private String categoryName;
    private String description;
    private Integer price;
    private Long restaurantNit;
    private String imageUrl;
    private Boolean active;
}
