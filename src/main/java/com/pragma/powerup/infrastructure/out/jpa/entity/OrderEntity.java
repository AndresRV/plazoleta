package com.pragma.powerup.infrastructure.out.jpa.entity;

import com.pragma.powerup.domain.model.OrderStatusEnum;
import com.pragma.powerup.infrastructure.out.jpa.entity.union.OrderDishEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_client", nullable = false)
    private Long idClient;
    @Column(name = "date_time_order", nullable = false)
    private LocalDateTime dateTimeOrder;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatusEnum;
    @Column(name = "id_chef", nullable = true)
    private Long idChef;
    @Column(name = "id_restaurant", nullable = false)
    private Long idRestaurant;
    @OneToMany(mappedBy = "orderEntity")
    private Set<OrderDishEntity> orderDishes = new HashSet<>();
    @Column(name = "claim_pin", nullable = true)
    private String claimPin;
}