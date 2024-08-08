package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.OrderResponse;
import com.pragma.powerup.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderResponseMapper {
    IOrderDtoMapper INSTANCE = Mappers.getMapper(IOrderDtoMapper.class);

    default OrderResponse toResponse(Page<Order> orderList) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderDto(INSTANCE.toDtoList(orderList.getContent()));
        orderResponse.setPages(orderList.getTotalPages());
        return orderResponse;
    }
}
