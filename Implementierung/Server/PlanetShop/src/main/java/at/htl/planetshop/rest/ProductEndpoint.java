package at.htl.planetshop.rest;

import at.htl.planetshop.entity.Product;
import at.htl.planetshop.facade.ProductFacade;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("planet")
public class ProductEndpoint {
    @Inject
    private ProductFacade productFacade;

    @GET
    @Path("getAllProducts")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public JsonArray getAllProducts(){
        return productFacade.getAllProducts();
    }

    @GET
    @Path("getProductDetails/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Product getProductDetails(@PathParam("id") long id) {
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
