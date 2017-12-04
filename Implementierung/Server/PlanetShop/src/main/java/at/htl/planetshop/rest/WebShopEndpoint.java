package at.htl.planetshop.rest;

import at.htl.planetshop.boundary.Request;
import at.htl.planetshop.boundary.RequestType;
import at.htl.planetshop.entity.PlanetCard;
import at.htl.planetshop.logic.ClientRequestHandler;
import at.htl.planetshop.logic.RequestHandler;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("shop")
public class WebShopEndpoint {

    @Inject
    private RequestHandler requestHandler;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<PlanetCard> answerRequest() {
        return (List<PlanetCard>) requestHandler.answerRequest(new Request(RequestType.GET_ALL_PRODUCTS));
    }

}
