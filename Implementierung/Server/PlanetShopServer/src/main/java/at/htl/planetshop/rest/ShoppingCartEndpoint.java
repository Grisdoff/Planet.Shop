package at.htl.planetshop.rest;

import at.htl.planetshop.business.ShoppingCartFacade;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("shoppingCart")
public class ShoppingCartEndpoint {
    @Inject
    private ShoppingCartFacade shoppingCartFacade;

    @Path("addProductToCart/{productId}/{userId}")
    public void addProductToShoppingCart(@PathParam("productId")Long productId, @PathParam("userId")Long userId){
        shoppingCartFacade.add(productId, userId);
    }

    @Path("removeProductFromCart/{productId}/{userId}")
    public void removeProductFromShoppingCart(@PathParam("productId")Long productId, @PathParam("userId")Long userId){
        shoppingCartFacade.remove(productId, userId);
    }
}
