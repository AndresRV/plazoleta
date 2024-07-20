package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dishes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dish_name", nullable = false)
    private String name;
    @Column(name = "id_category", nullable = false)
    private Long idCategory;
    @Column(name = "dish_description", nullable = false)
    private String description;
    private Integer price;
    @Column(name = "id_restaurant", nullable = false)
    private Long idRestaurant;
    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    private Boolean active;
}