package com.pragma.powerup.infrastructure.out.jpa.entity.union;

import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.keys.OrderDishKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "orders_dishes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDishEntity {
    @EmbeddedId
    private OrderDishKey id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idOrder")
    @JoinColumn(name = "id_order")
    private OrderEntity orderEntity;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idDish")
    @JoinColumn(name = "id_dish")
    private DishEntity dishEntity;
    private Integer quantity;
}
