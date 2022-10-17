package com.ml.bff.payment.service;

import com.ml.bff.purcharse.dto.BffPayment;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;

public interface PaymentService {
    Future<BffPayment> getPaymentById(Long id);
}
