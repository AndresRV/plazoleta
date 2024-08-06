package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.union.OrderDish;

public interface IOrderDishServicePort {
    void saveOrderDish(OrderDish orderDish);
}
