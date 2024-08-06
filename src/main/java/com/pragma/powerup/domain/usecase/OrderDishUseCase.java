package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderDishServicePort;
import com.pragma.powerup.domain.model.union.OrderDish;
import com.pragma.powerup.domain.spi.IOrderDishPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderDishUseCase implements IOrderDishServicePort {
    private final IOrderDishPersistencePort orderDishPersistencePort;

    @Override
    public void saveOrderDish(OrderDish orderDish) {
        orderDishPersistencePort.saveOrderDish(orderDish);
    }
}
