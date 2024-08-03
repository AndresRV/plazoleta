package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.DishResponse;
import com.pragma.powerup.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishResponseMapper {
    List<DishResponse> toResponseList(List<Dish> dishList);
}
