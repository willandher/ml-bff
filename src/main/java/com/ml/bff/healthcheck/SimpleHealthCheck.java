package com.ml.bff.healthcheck;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;


@Liveness
@ApplicationScoped
public class SimpleHealthCheck implements HealthCheck {


  @Override
  public HealthCheckResponse call() {
    return HealthCheckResponse.named("UP")
      .up()
      .build();
  }
}
