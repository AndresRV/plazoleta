package com.pragma.powerup.infrastructure.out.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MS-USER-CLIENT", url = "${external.ms-users}")
public interface UserFeignClient {
    @GetMapping("/api/v1/user/ownerUser/{idUser}")
    Boolean isOwnerUser(@PathVariable("idUser") Long idUser);
}