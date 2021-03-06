package at.htl.planetshop.rest;

import at.htl.planetshop.entities.Product;
import at.htl.planetshop.business.ProductFacade;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Path("product")
public class ProductEndpoint {
    @Inject
    private ProductFacade productFacade;

    //return all Products
    @GET
    @Path("getAllProducts")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getAllProducts(){
        return productFacade.getAllProducts();
    }

    //return Product with matches with id
    @GET
    @Path("getProductById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProductDetails(@PathParam("id") Long id) {
        return productFacade.findById(id);
    }

    //return List of Product with matches one of the sent ids
    @GET
    @Path("getProductsByIds")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getProductsByIds(@javax.ws.rs.QueryParam(value = "ids") List<Long> ids) {
        return productFacade.getProductsByIds(ids);
    }

    //save a Product
    @POST
    public Response save(Product card, @Context UriInfo uriInfo) {
        Product saved = this.productFacade.saveItem(card);
        Long id = (long) saved.getId();
        URI uri = uriInfo.getAbsolutePathBuilder().path("/"+ id).build();
        return Response.created(uri).build();
    }

    //filter
    @GET
    @Path("filter/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray filter(@PathParam("filter") String filter){
        return productFacade.getFilteredProducts(filter);
    }
}