package com.ml.bff.purcharse.service;

import com.ml.bff.payment.service.PaymentService;
import com.ml.bff.purcharse.dto.BffPayment;
import com.ml.bff.purcharse.dto.BffPurcharseData;
import com.ml.bff.purcharse.dto.BffPurcharseInfo;
import com.ml.bff.purcharse.dto.BffShipment;
import com.ml.bff.shipment.service.ShipmentService;
import com.ml.bff.user.dto.PurcharseData;
import com.ml.bff.user.dto.PurcharseInfo;
import com.ml.bff.user.service.UserService;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.Json;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BffPurcharseServiceImpl implements BffPurcharseService {

    private  final UserService userService;
    private  final ShipmentService shipmentService;
    private  final PaymentService paymentService;

    Function<PurcharseData, BffPurcharseData> bffPurcharseDataFunction = purcharseData ->
            BffPurcharseData.builder()
                    .id_compra(purcharseData.getId_compra())
                    .fecha(purcharseData.getFecha())
                    .cantidad(purcharseData.getCantidad())
                    .imagen(purcharseData.getImagen())
                    .titulo(purcharseData.getTitulo())
                    .precio(purcharseData.getPrecio())
                    .id_envio(purcharseData.getId_envio())
                    .id_transaccion(purcharseData.getId_transaccion())
                    .vendedor(purcharseData.getVendedor())
                    .build();

    public BffPurcharseServiceImpl(UserService userService, ShipmentService shipmentService, PaymentService paymentService) {

        this.userService = userService;
        this.shipmentService = shipmentService;
        this.paymentService = paymentService;
    }

    /**
     * Esto se pudiera manejar mejor si con un servicio
     * @param id
     * @param limit
     * @param offset
     * @return
     */

    public Future<BffPurcharseInfo> getPurcharseByUserId(Integer id, Integer limit, Integer offset){
        Promise<BffPurcharseInfo> bffPurcharseInfoPromise = Promise.promise();
        userService.getPurcharseByUser(id, limit, offset).onSuccess(buffer -> {
            var purcharseInfo = Json.decodeValue(buffer, PurcharseInfo.class);
            List<Future> transactionList = new ArrayList<>();
            List<BffPurcharseData> bffPurcharseData = purcharseInfo.getData().parallelStream().map(purcharseData -> {
                transactionList.add(shipmentService.getShippmentById(purcharseData.getId_envio()));
                transactionList.add(paymentService.getPaymentById(purcharseData.getId_transaccion()));
                return bffPurcharseDataFunction.apply(purcharseData);
            }).collect(Collectors.toList());
            CompositeFuture.all(transactionList).onSuccess(ar ->{
                ar.list().forEach(ab ->{
                    if( ab instanceof BffShipment){
                        bffPurcharseData.stream()
                                .filter(filter -> filter.getId_envio().equals(((BffShipment) ab).getId_envio()))
                                .map(filtered -> {
                                    filtered.setShipment((BffShipment) ab);
                                return filtered;
                                }).collect(Collectors.toList());
                    }
                    else if ( ab instanceof BffPayment){
                        bffPurcharseData.stream()
                                .filter(filter -> filter.getId_transaccion().equals(((BffPayment) ab).getId_transaccion()))
                                .map(filtered -> {
                                    filtered.setTransaction((BffPayment) ab);
                                    return filtered;
                                }).collect(Collectors.toList());
                    }
                });
            bffPurcharseInfoPromise.complete(
                    BffPurcharseInfo.builder()
                            .limit(purcharseInfo.getLimit())
                            .offset(purcharseInfo.getOffset())
                            .total(purcharseInfo.getTotal())
                            .data(bffPurcharseData)
                            .build()
            );
            });
        }).onFailure(bffPurcharseInfoPromise::fail);
        return bffPurcharseInfoPromise.future();
    }
}
