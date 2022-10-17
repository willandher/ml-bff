package com.ml.bff.shipment.api;


import com.ml.bff.purcharse.dto.BffShipment;
import com.ml.bff.shipment.service.ShipmentService;
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
public class ShipmentController {
    private static final String API_ML_GET_SHIPMENT = "/shipments/{id}";
    @Inject
    ShipmentService shipmentService;

    @Path(API_ML_GET_SHIPMENT)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<BffShipment> getShipmentId(@PathParam("id")Long id) {
        return UniHelper.toUni(shipmentService.getShippmentById(id))
                .onItem().transform(a -> a);
    }
}
