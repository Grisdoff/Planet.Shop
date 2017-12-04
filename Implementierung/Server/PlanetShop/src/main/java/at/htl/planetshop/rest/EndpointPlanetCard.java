package at.htl.planetshop.rest;

import at.htl.planetshop.entity.PlanetCard;
import at.htl.planetshop.facade.CardFacade;
import com.sun.jndi.toolkit.url.Uri;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("planet")
public class EndpointPlanetCard {
    @Inject
    CardFacade cardFacade;


    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<PlanetCard> findAll(){
        return cardFacade.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public PlanetCard findById(@PathParam("id") long id){
        return cardFacade.findById(id);
    }

    @POST
    public Response save(PlanetCard card, @Context UriInfo uriInfo) {
        PlanetCard saved = this.cardFacade.saveItem(card);
        Long id = (long) saved.getId();
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+ id).build();
        return Response.created(uri).build();
    }
}
