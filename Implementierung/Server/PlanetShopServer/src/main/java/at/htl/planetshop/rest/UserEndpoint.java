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

    //check Login Datas
    @GET
    @Path("tryLogin/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkUserLogin(@PathParam("username")String username, @PathParam("password")String password) {
        return userFacade.checkUserLogin(username, password);
    }

    //add a User
    @Path("addUser/{username}/{password}")
    public void addUser(@PathParam("username") String username, @PathParam("password") String password) {
        userFacade.createUser(username, password);
    }

    //delete a User
    @Path("deleteUser/{username}")
    public void deleteUser(@PathParam("username") String username) {
        userFacade.deleteUser(username);
    }

    //change password
    @GET
    @Path("changePassword/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean changePassword(@PathParam("username") String username, @PathParam("password") String password) {
        return userFacade.changePassword(username, password);
    }
}
