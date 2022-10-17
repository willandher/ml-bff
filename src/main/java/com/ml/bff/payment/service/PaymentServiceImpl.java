package com.ml.bff.payment.service;
import com.ml.bff.config.PropertiesConfig;
import com.ml.bff.purcharse.dto.BffPayment;
import com.ml.bff.shared.WebClientService;
import com.ml.bff.shared.WebService;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;

public class PaymentServiceImpl extends WebService implements PaymentService {

    public PaymentServiceImpl(WebClientService webClientService, PropertiesConfig propertiesConfig) {
        super(webClientService, propertiesConfig.service().transaction());
    }


    @Override
    public Future<BffPayment> getPaymentById(Long id) {
        return getWebClientService().requestGetCircuitBreaker(getPort(),getHost(),"/api/v1/payments/"+id,false)
                .map(buffer ->Json.decodeValue(buffer,BffPayment.class));
    }
}
