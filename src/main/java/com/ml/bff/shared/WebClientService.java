package com.ml.bff.shared;

import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;


public interface WebClientService {

    static WebClientService getWebClientService(Integer port, CircuitBreaker circuitBreaker) {
        WebClientOptions webClientOptions = new WebClientOptions();
        switch(port) {
        case 443:
            webClientOptions.setSsl(true).setVerifyHost(false).setTrustAll(true).setDefaultPort(port);
            HttpClient client = Vertx.vertx().createHttpClient(webClientOptions);
            WebClient webClient = WebClient.wrap(client);
            return new WebClientServiceImpl(webClient, circuitBreaker);
        default:
            WebClient webClientSsh = WebClient.wrap(Vertx.vertx().createHttpClient());
            return new WebClientServiceImpl(webClientSsh, circuitBreaker);
        }
    }

    Future<Buffer> requestGetCircuitBreaker(Integer port, String host, String requestUri, MultiMap headers, Boolean internal);

    Future<Buffer> requestGetCircuitBreaker(Integer port, String host, String requestUri, Boolean internal);


}
