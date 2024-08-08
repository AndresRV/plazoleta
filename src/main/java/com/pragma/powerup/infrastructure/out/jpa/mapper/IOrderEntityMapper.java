package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {
    IOrderDishEntityMapper INSTANCE = Mappers.getMapper(IOrderDishEntityMapper.class);

    OrderEntity toEntity(Order order);
    Order toOrder(OrderEntity orderEntity);
    List<Order> toOrderList(List<OrderEntity> orderEntityList);

    default Page<Order> toOrderPage(Page<OrderEntity> orderEntityPage) {
        List<Order> orders = orderEntityPage.stream()
                .map(orderEntity -> {
                    Order order = this.toOrder(orderEntity);
                    order.setOrderDishList(INSTANCE.toOrderDishList(orderEntity.getOrderDishes().stream().toList()));
                    return order;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(orders, orderEntityPage.getPageable(), orderEntityPage.getTotalElements());
    }
}
