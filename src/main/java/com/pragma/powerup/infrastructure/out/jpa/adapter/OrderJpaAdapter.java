package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.infrastructure.exception.OrderActiveException;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    @Override
    public void saveOrder(Order order) {
        if (orderRepository.findByIdClientAndIdRestaurant(order.getIdClient(), order.getIdRestaurant()).isPresent()) {
            throw new OrderActiveException();
        }
        orderRepository.save(orderEntityMapper.toEntity(order));
    }
}
