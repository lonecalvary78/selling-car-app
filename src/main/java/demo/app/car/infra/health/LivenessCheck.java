package demo.app.car.infra.health;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class LivenessCheck implements HealthCheck {
  @ConfigProperty(name="quarkus.application.name")
  private String applicationName;

  @Override
  public HealthCheckResponse call() {
    return HealthCheckResponse.up(applicationName);
  }
}
