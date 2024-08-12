package com.pragma.powerup.infrastructure.out.rest.trace;

import com.pragma.powerup.domain.model.Trace;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITraceRequestMapper {
    TraceRequest toRequest(Trace trace);
}
