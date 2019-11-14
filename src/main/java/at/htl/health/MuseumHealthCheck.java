package at.htl.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import at.htl.services.ExhibitService;

@Liveness
@ApplicationScoped
public class MuseumHealthCheck implements HealthCheck {

    @Inject
    @RestClient
    ExhibitService exhibitService;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Museum Server");

        try {
            this.exhibitService.getExhibits();
            responseBuilder.up();// .withData("Status", "Up");
        } catch (Exception e) {
            responseBuilder.down().withData("Info", "snafu");
        }

        return responseBuilder.build();
    }
}