package com.ml.bff.shared;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WebClientHandler {

    private static final Logger log = LoggerFactory.getLogger(WebClientHandler.class);

    public WebClientHandler() {
    }


    public Handler<AsyncResult<HttpResponse<Buffer>>> handlerResult(Promise<Buffer> promise) {
        return (result) -> {
            log.info("Llego al internal.");
            if (result.succeeded()) {
                switch(((HttpResponse)result.result()).statusCode()) {
                case 200:
                    promise.complete((Buffer)((HttpResponse)result.result()).body());
                    break;
                default:
                    log.error("The server response an error with status code: " + ((HttpResponse)result.result()).statusCode());
                    log.error("Status message is: " + ((HttpResponse)result.result()).statusMessage());

                    try {
                        promise.fail(result.cause());
                    } catch (Exception var3) {
                        promise.fail(result.cause());
                    }
                }
            } else {
                promise.fail(result.cause());
            }

        };
    }
}