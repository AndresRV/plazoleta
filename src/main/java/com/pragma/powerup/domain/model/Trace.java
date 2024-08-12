package com.pragma.powerup.domain.model;

public class Trace {
    private Long idOrder;
    private Long idClient;
    private OrderStatusEnum orderStatusOld;
    private OrderStatusEnum orderStatusNew;
    private Long idChef;

    public Trace(Long idOrder, Long idClient, OrderStatusEnum orderStatusOld, OrderStatusEnum orderStatusNew, Long idChef) {
        this.idOrder = idOrder;
        this.idClient = idClient;
        this.orderStatusOld = orderStatusOld;
        this.orderStatusNew = orderStatusNew;
        this.idChef = idChef;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public OrderStatusEnum getOrderStatusOld() {
        return orderStatusOld;
    }

    public void setOrderStatusOld(OrderStatusEnum orderStatusOld) {
        this.orderStatusOld = orderStatusOld;
    }

    public OrderStatusEnum getOrderStatusNew() {
        return orderStatusNew;
    }

    public void setOrderStatusNew(OrderStatusEnum orderStatusNew) {
        this.orderStatusNew = orderStatusNew;
    }

    public Long getIdChef() {
        return idChef;
    }

    public void setIdChef(Long idChef) {
        this.idChef = idChef;
    }
}
