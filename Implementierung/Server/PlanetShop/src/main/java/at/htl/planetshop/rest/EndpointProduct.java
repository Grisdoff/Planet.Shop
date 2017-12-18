package at.htl.planetshop.rest;

import at.htl.planetshop.entities.Product;
import at.htl.planetshop.facade.ProductFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("planet")
public class EndpointProduct {

    @Inject
    private ProductFacade productFacade;


    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Product> findAll(){
        return productFacade.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Product findById(@PathParam("id") long id){
        return productFacade.findById(id);
    }

    @POST
    public Response save(Product card, @Context UriInfo uriInfo) {
        Product saved = this.productFacade.saveItem(card);
        Long id = (long) saved.getId();
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+ id).build();
        return Response.created(uri).build();
    }
}
