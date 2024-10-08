package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.RestaurantResponse;
import com.pragma.powerup.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {
    List<RestaurantResponse> toResponseList(List<Restaurant> restaurantList);
}
