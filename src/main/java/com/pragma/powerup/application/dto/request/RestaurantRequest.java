package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantRequest {
    private String name;
    private String address;
    private Long idOwner;
    private String phoneNumber;
    private String logoUrl;
    private Long nit;
}
