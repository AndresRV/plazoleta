package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.Utils.Constants;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;

import java.time.ZoneId;
import java.time.ZonedDateTime;


@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;

    @Override
    public void saveOrder(Order order) {
        order.setDateTimeOrder(ZonedDateTime.now(ZoneId.of(Constants.TIME_ZONE)).toLocalDateTime());
        orderPersistencePort.saveOrder(order);
    }
}
