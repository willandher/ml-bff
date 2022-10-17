package com.ml.bff.shipment.service;
import com.ml.bff.config.PropertiesConfig;
import com.ml.bff.purcharse.dto.BffPayment;
import com.ml.bff.purcharse.dto.BffShipment;
import com.ml.bff.shared.WebClientService;
import com.ml.bff.shared.WebService;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;

import java.util.function.Function;

public class ShipmentServiceImpl extends WebService implements ShipmentService {

    public ShipmentServiceImpl(WebClientService webClientService, PropertiesConfig propertiesConfig) {
        super(webClientService, propertiesConfig.service().transaction());
    }



    @Override
    public Future<BffShipment> getShippmentById(Long id) {
        return getWebClientService().requestGetCircuitBreaker(getPort(),getHost(),"/api/v1/shipments/"+id,false)
                .map(buffer -> Json.decodeValue(buffer,BffShipment.class));
    }
}
