/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.createtask.endpoint;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import todolist.createtask.business.CreateTaskBusiness;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("createtask/{query}")
public class CreateTaskResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CreateTaskResource
     */
    public CreateTaskResource() {
    }

    /**
     * Retrieves representation of an instance of todolist.createtask.endpoint.CreateTaskResource
     * @param query resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml(@PathParam("query") String query) {
        //TODO return proper representation object
        CreateTaskBusiness create = new CreateTaskBusiness();
        create.createTask(query);
        return "";
    }

    /**
     * PUT method for updating or creating an instance of CreateTaskResource
     * @param query resource URI parameter
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(@PathParam("query") String query, String content) {
    }
}
