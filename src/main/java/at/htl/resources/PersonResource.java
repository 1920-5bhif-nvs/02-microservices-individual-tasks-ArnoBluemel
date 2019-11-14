package at.htl.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import at.htl.services.PersonService;

@Path("/persons")
@ApplicationScoped
public class PersonResource {
    @Inject
    @RestClient
    PersonService personService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 5)
    @Counted(name = "persons_called", description = "Amount of requests")
    @Timed(name = "timer", description = "How long this task takes to perform", unit = MetricUnits.MILLISECONDS)
    public Response get() {
        return Response.ok().entity(this.personService.getPersons()).build();
    }
}