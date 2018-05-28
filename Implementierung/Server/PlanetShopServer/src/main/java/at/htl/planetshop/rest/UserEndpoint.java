package at.htl.planetshop.rest;

import at.htl.planetshop.business.UserFacade;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("user")
public class UserEndpoint {
    @Inject
    private UserFacade userFacade;

    //add a User
    @Path("addUser/{id}")
    public void addUser(@PathParam("id") Long id) {
        userFacade.createUser(id);
    }

    //delete a User
    @Path("deleteUser/{id}")
    public void deleteUser(@PathParam("id") Long id) {
        userFacade.deleteUser(id);
    }
}
