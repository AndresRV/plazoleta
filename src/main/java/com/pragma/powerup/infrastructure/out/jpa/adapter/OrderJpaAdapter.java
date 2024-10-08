package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.OrderStatusEnum;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.infrastructure.exception.OrderNotFoundException;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    @Override
    public Order saveOrder(Order order) {
        return orderEntityMapper.toOrder(orderRepository.save(orderEntityMapper.toEntity(order)));
    }

    @Override
    public List<Order> getAllOrdersByIdClientAndIdRestaurant(Long idClient, Long idRestaurant) {
        return orderEntityMapper.toOrderList(orderRepository.findAllByIdClientAndIdRestaurant(idClient, idRestaurant));
    }

    @Override
    public Page<Order>  getPagedOrdersByIdRestaurantAndOrderStatusEnum(Long idRestaurant, OrderStatusEnum orderStatusEnum, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderEntityMapper.toOrderPage(orderRepository.findAllByIdRestaurantAndOrderStatusEnum(idRestaurant, orderStatusEnum, pageable));
    }

    @Override
    public Order findById(Long idOrder) {
        return orderEntityMapper.toOrder(orderRepository.findById(idOrder)
                .orElseThrow(OrderNotFoundException::new));
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(orderEntityMapper.toEntity(order));
    }
}
