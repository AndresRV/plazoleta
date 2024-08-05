package com.pragma.powerup.domain.model;

import java.time.LocalDateTime;

public class Order {
    private Long id;
    private Long idClient;
    private LocalDateTime dateTimeOrder;
    //estado
    private Long idChef;
    private Long idRestaurant;
    //lista de platos

    public Order(Long id, Long idClient, LocalDateTime dateTimeOrder, Long idChef, Long idRestaurant) {
        this.id = id;
        this.idClient = idClient;
        this.dateTimeOrder = dateTimeOrder;
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
}
