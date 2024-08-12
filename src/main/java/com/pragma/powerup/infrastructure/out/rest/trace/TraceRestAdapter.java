package com.pragma.powerup.infrastructure.out.rest.trace;

import com.pragma.powerup.domain.model.Trace;
import com.pragma.powerup.domain.spi.ITraceRestPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TraceRestAdapter implements ITraceRestPort {
    private final TraceFeignClient traceFeignClient;
    private final ITraceRequestMapper traceRequestMapper;

    @Override
    public void saveTrace(Trace trace) {
        traceFeignClient.saveTrace(traceRequestMapper.toRequest(trace));
    };
}
