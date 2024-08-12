package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Trace;

public interface ITraceRestPort {
    void saveTrace(Trace trace);
}
