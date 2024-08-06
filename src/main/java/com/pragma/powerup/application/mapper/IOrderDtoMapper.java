package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.OrderDto;
import com.pragma.powerup.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDtoMapper {
    List<OrderDto> toDtoList(List<Order> orderList);
}
