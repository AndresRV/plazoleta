package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    //estado
    @Column(name = "id_chef", nullable = true)
    private Long idChef;
    @Column(name = "id_restaurant", nullable = false)
    private Long idRestaurant;
    //lista de platos
}