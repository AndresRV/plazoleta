package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "restaurant_name", nullable = false)
    private String name;
    @Column(name = "restaurant_address", nullable = false)
    private String address;
    @Column(name = "id_owner", nullable = false)
    private Long idOwner;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "logo_url", nullable = false)
    private String logoUrl;
    private Long nit;
}