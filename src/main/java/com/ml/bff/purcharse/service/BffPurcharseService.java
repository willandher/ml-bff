package com.ml.bff.purcharse.service;

import com.ml.bff.purcharse.dto.BffPurcharseInfo;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;

public interface BffPurcharseService {
    Future<Buffer> getPurcharseByUserId(Integer id, Integer limit , Integer offset);
}
