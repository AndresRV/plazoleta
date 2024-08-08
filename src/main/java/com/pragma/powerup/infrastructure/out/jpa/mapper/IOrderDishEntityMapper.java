package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.union.OrderDish;
import com.pragma.powerup.infrastructure.out.jpa.entity.union.OrderDishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDishEntityMapper {
    OrderDishEntity toEntity(OrderDish order);

    @Mapping(target = "dish", source = "dishEntity")
    OrderDish toOrderDish(OrderDishEntity orderDishEntity);

    default List<OrderDish> toOrderDishList(List<OrderDishEntity> orderDishEntityList) {
        return orderDishEntityList.stream()
                .map(this::toOrderDish)
                .collect(Collectors.toList());
    }
}
