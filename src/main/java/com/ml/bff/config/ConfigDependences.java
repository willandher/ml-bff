package com.ml.bff.config;

import com.ml.bff.payment.service.PaymentService;
import com.ml.bff.payment.service.PaymentServiceImpl;
import com.ml.bff.purcharse.service.BffPurcharseService;
import com.ml.bff.purcharse.service.BffPurcharseServiceImpl;
import com.ml.bff.shipment.service.ShipmentService;
import com.ml.bff.shipment.service.ShipmentServiceImpl;
import com.ml.bff.shared.WebClientService;
import com.ml.bff.user.service.UserService;
import com.ml.bff.user.service.UserServiceImpl;
import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.circuitbreaker.CircuitBreakerOptions;
import io.vertx.core.Vertx;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ConfigDependences {

    @Inject
    PropertiesConfig propertiesConfig;

    @Inject
    Vertx vertx;


    @Produces
    @Singleton
    @Default
    public CircuitBreaker circuitBreaker() {
        var circuitBreaker = CircuitBreaker.create("circuit-breaker", vertx,
                new CircuitBreakerOptions()
                        .setFallbackOnFailure(true)
                        .setMaxFailures(3)
                        .setMaxRetries(3)
                        .setTimeout(120000)
                        .setResetTimeout(12000)
        );
        circuitBreaker.retryPolicy(retryCount -> retryCount * 1000L);
        return circuitBreaker;
    }
    @Produces
    @ApplicationScoped
    @Default
    UserService userService(CircuitBreaker circuitBreaker){
        return new UserServiceImpl(WebClientService.getWebClientService(propertiesConfig.service().user().port(),circuitBreaker), propertiesConfig);
    }

    @Produces
    @ApplicationScoped
    @Default
    ShipmentService shipmentService(CircuitBreaker circuitBreaker){
        return new ShipmentServiceImpl(WebClientService.getWebClientService(propertiesConfig.service().transaction().port(),circuitBreaker), propertiesConfig);
    }


    @Produces
    @ApplicationScoped
    @Default
    PaymentService paymentService(CircuitBreaker circuitBreaker){
        return new PaymentServiceImpl(WebClientService.getWebClientService(propertiesConfig.service().transaction().port(),circuitBreaker), propertiesConfig);
    }

    @Produces
    @ApplicationScoped
    @Default
    BffPurcharseService bffPurcharseService(UserService userService, PaymentService paymentService, ShipmentService shipmentService){
        return new BffPurcharseServiceImpl(userService,shipmentService,paymentService);
    }

}
