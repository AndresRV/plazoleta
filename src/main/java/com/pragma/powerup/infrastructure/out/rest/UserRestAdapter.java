package com.pragma.powerup.infrastructure.out.rest;

import com.pragma.powerup.domain.spi.IUserRestPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRestAdapter implements IUserRestPort {
    private final UserFeignClient userFeignClient;

    @Override
    public Boolean isOwnerUser(Long idUser) {
        return userFeignClient.isOwnerUser(idUser);
    }
}
