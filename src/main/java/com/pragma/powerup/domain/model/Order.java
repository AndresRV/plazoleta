package com.pragma.powerup.domain.model;

import com.pragma.powerup.domain.model.union.OrderDish;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private Long idClient;
    private LocalDateTime dateTimeOrder;
    private OrderStatusEnum orderStatusEnum;
    private Long idChef;
    private Long idRestaurant;
    private List<OrderDish> orderDishList;
    private String claimPin;

    public Order(Long id, Long idClient, LocalDateTime dateTimeOrder, OrderStatusEnum orderStatusEnum, Long idChef, Long idRestaurant) {
        this.id = id;
        this.idClient = idClient;
        this.dateTimeOrder = dateTimeOrder;
        this.orderStatusEnum = orderStatusEnum;
        this.idChef = idChef;
        this.idRestaurant = idRestaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public LocalDateTime getDateTimeOrder() {
        return dateTimeOrder;
    }

    public void setDateTimeOrder(LocalDateTime dateTimeOrder) {
        this.dateTimeOrder = dateTimeOrder;
    }

    public OrderStatusEnum getOrderStatusEnum() {
        return orderStatusEnum;
    }

    public void setOrderStatusEnum(OrderStatusEnum orderStatusEnum) {
        this.orderStatusEnum = orderStatusEnum;
    }

    public Long getIdChef() {
        return idChef;
    }

    public void setIdChef(Long idChef) {
        this.idChef = idChef;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public List<OrderDish> getOrderDishList() {
        return orderDishList;
    }

    public void setOrderDishList(List<OrderDish> orderDishList) {
        this.orderDishList = orderDishList;
    }

    public String getClaimPin() {
        return claimPin;
    }

    public void setClaimPin(String claimPin) {
        this.claimPin = claimPin;
    }
}
