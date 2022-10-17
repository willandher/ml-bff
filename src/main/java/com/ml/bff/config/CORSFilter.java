package com.ml.bff.config;

import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

  public final Logger log = LoggerFactory.getLogger(CORSFilter.class);

  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
    log.debug("Modifing response with CORSFIlter: " + responseContext.getHeaders());
    MultivaluedMap<String, Object> headers = responseContext.getHeaders();
    headers.putSingle("Access-Control-Allow-Origin", "*");
    headers.putSingle("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS");
    headers.putSingle("Access-Control-Max-Age", "3600");
    headers.putSingle("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    log.debug("Modified to add the required header: " + responseContext.getHeaders());
  }
}
