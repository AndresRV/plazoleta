package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.OrderDto;
import com.pragma.powerup.application.dto.response.OrderDishResponse;
import com.pragma.powerup.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDtoMapper {
    @Mapping(target = "idOrder", source = "id")
    OrderDto toDtoList(Order order);

    default List<OrderDto> toDtoList(List<Order> orderList) {
        return orderList.stream()
                .map(order -> {
                    OrderDto orderDto = this.toDtoList(order);

                    List<OrderDishResponse> orderDishResponseList = order.getOrderDishList().stream()
                            .map(orderDish -> {
                                OrderDishResponse orderDishResponse = new OrderDishResponse();
                                orderDishResponse.setName(orderDish.getDish().getName());
                                orderDishResponse.setDescription(orderDish.getDish().getDescription());
                                orderDishResponse.setImageUrl(orderDish.getDish().getImageUrl());
                                orderDishResponse.setQuantity(orderDish.getQuantity());
                                return orderDishResponse;
                            })
                            .toList();
                    orderDto.setOrderDishResponseList(orderDishResponseList);

                    return  orderDto;
                })
                .toList();
    }
}
