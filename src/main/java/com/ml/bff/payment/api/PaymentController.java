package com.ml.bff.payment.api;


import com.ml.bff.payment.service.PaymentService;
import com.ml.bff.purcharse.dto.BffPayment;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.UniHelper;
import io.vertx.core.buffer.Buffer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/ml/v1/bff")
public class PaymentController {
    private static final String API_ML_GET_SHIPMENT = "/payments/{id}";
    @Inject
    PaymentService shipmentService;
    @Path(API_ML_GET_SHIPMENT)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<BffPayment> getPaymentById(@PathParam("id")Long id) {
        return UniHelper.toUni(shipmentService.getPaymentById(id))
                .onItem().transform(a -> a);
    }
}
