package at.htl.services;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@ApplicationScoped
// @Path("/exhibits")
public interface ExhibitService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/exhibits")
    JsonArray getExhibits();
}