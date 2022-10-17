package com.ml.bff.purcharse.service;

import com.ml.bff.purcharse.dto.BffPurcharseInfo;
import io.vertx.core.Future;

public interface BffPurcharseService {
    Future<BffPurcharseInfo> getPurcharseByUserId(Integer id, Integer limit , Integer offset);
}
