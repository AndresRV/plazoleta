package com.pragma.powerup.infrastructure.out.jpa.entity.keys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDishKey implements Serializable {
    @Column(name = "id_order")
    private Long idOrder;
    @Column(name = "id_dish")
    private Long idDish;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDishKey that = (OrderDishKey) o;
        return Objects.equals(idOrder, that.idOrder) &&
                Objects.equals(idDish, that.idDish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idDish);
    }
}
