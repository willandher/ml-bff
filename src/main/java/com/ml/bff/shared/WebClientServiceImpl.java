package com.ml.bff.shared;

import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WebClientServiceImpl implements WebClientService {
    private final Logger log = LoggerFactory.getLogger(WebClientServiceImpl.class);
    private final WebClient webClient;
    private final WebClientHandler webClientHandler;
    private CircuitBreaker circuitBreaker;
    private static final String REQUEST_GET_INTERNAL = "requestGet internal ";

    public WebClientServiceImpl(WebClient webClient, WebClientHandler webClientHandler) {
        this.webClient = webClient;
        this.webClientHandler = webClientHandler;
    }

    public WebClientServiceImpl(WebClient webClient) {
        this.webClient = webClient;
        this.webClientHandler = new WebClientHandler();
    }

    public WebClientServiceImpl(WebClient webClient, CircuitBreaker circuitBreaker) {
        this.webClient = webClient;
        this.webClientHandler = new WebClientHandler();
        this.circuitBreaker = circuitBreaker;
    }


    @Override
    public Future<Buffer> requestGetCircuitBreaker(Integer port, String host, String requestUri, MultiMap headers, Boolean internal) {
        Promise<Buffer> promise = Promise.promise();
        try {
            log.info(REQUEST_GET_INTERNAL + requestUri);
            circuitBreaker.<Buffer>execute(ar -> webClient.get(port, host, requestUri)
                    .putHeaders(headers)
                    .send(webClientHandler.handlerResult(ar))).onSuccess(promise::complete).onFailure(ab -> {
                    promise.fail(ab.getCause());
            })
            ;
        } catch (Exception exception) {
            promise.fail(exception.getCause());
        }
        return promise.future();
    }

    public Future<Buffer> requestGetCircuitBreaker(Integer port, String host, String requestUri, Boolean internal) {
        Promise<Buffer> promise = Promise.promise();
        try {
            log.info(REQUEST_GET_INTERNAL + requestUri);
            circuitBreaker.<Buffer>execute(ar -> webClient.get(port, host, requestUri).
                    send(webClientHandler.handlerResult(ar))).onSuccess(promise::complete).onFailure(ab -> {
                promise.fail(ab.getCause());
            })
            ;
        } catch (Exception exception) {
            promise.fail(exception.getCause());
        }
        return promise.future();
    }


}