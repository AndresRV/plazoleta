package com.pragma.powerup.domain.spi;

public interface IUserRestPort {
    Boolean isOwnerUser(Long idUser);
    Long getRestaurantByUser(Integer documentNumber);
}
