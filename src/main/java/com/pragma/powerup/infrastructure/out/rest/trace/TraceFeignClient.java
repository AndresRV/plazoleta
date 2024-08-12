package com.pragma.powerup.infrastructure.out.rest.trace;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MS-USER-TRACE", url = "${external.ms-trace}")
public interface TraceFeignClient {
    @PostMapping("/api/v1/trace")
    void saveTrace(@RequestBody TraceRequest traceRequest);
}