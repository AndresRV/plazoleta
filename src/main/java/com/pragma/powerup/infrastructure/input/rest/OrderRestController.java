package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.OrderRequest;
import com.pragma.powerup.application.dto.response.OrderResponse;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.domain.model.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderRestController {
    private final IOrderHandler orderHandler;

    @PostMapping("")
    @PreAuthorize("hasRole('Cliente')")
    public ResponseEntity<Void> saveOrder(@RequestBody OrderRequest orderRequest) {
        orderHandler.saveOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/status/{orderStatusEnum}")
    @PreAuthorize("hasRole('Empleado')")
    public ResponseEntity<OrderResponse> getPagedDishes(@PathVariable OrderStatusEnum orderStatusEnum,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "2") int size) {
        return ResponseEntity.ok(orderHandler.getPagedOrders(orderStatusEnum, page, size));
    }
}
