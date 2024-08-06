package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.union.OrderDish;
import com.pragma.powerup.domain.spi.IOrderDishPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.keys.OrderDishKey;
import com.pragma.powerup.infrastructure.out.jpa.entity.union.OrderDishEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrderDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderDishRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderDishJpaAdapter implements IOrderDishPersistencePort {
    private final IOrderDishRepository orderDishRepository;
    private final IOrderDishEntityMapper orderDishEntityMapper;
    private final IOrderEntityMapper orderEntityMapper;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public void saveOrderDish(OrderDish orderDish) {
        //TODO: MEJORA posible uso de instancia dentro del mapper orderdish
        OrderDishEntity orderDishEntity = orderDishEntityMapper.toEntity(orderDish);
        orderDishEntity.setId(new OrderDishKey(orderDish.getOrder().getId(), orderDish.getDish().getId()));
        orderDishEntity.setOrderEntity(orderEntityMapper.toEntity(orderDish.getOrder()));
        orderDishEntity.setDishEntity(dishEntityMapper.toEntity(orderDish.getDish()));

        orderDishRepository.save(orderDishEntity);
    }
}
