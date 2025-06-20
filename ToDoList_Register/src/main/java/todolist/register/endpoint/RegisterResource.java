/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.register.endpoint;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import todolist.register.business.RegisterBusiness;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("register/{query}")
public class RegisterResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RegisterResource
     */
    public RegisterResource() {
    }

    /**
     * Retrieves representation of an instance of todolist.register.endpoint.RegisterResource
     * @param query resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml(@PathParam("query") String query) {
        //TODO return proper representation object
        RegisterBusiness register = new RegisterBusiness();
        register.registerUser(query);
        return "";
    }

    /**
     * PUT method for updating or creating an instance of RegisterResource
     * @param query resource URI parameter
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(@PathParam("query") String query, String content) {
    }
}
