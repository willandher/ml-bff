package com.ml.bff.shipment.service;

import com.ml.bff.purcharse.dto.BffShipment;
import io.vertx.core.Future;


public interface ShipmentService {
    Future<BffShipment> getShippmentById(Long id);
}
