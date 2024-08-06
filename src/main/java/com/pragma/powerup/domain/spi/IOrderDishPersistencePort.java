package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.union.OrderDish;

public interface IOrderDishPersistencePort {
    void saveOrderDish(OrderDish orderDish);
}
