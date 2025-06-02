/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.viewtasks.endpoint;

import java.io.StringWriter;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import todolist.viewtasks.business.ViewTasksBusiness;
import todolist.viewtasks.helper.TasksXML;

/**
 * REST Web Service/home/student/NetBeansProjects/ToDoListApp/src/main/webapp
 *
 * @author student
 */
@Path("viewtasks/{query}")
public class ViewTasksResource {

    @Context
    private UriInfo context;
    
    /**
     * Creates a new instance of ViewTasksResource
     */
    public ViewTasksResource() {
        System.out.println("test31");
    }
    
    /**
     * Retrieves representation of an instance of ryerson.ca.searchbook.endpoint.SearchResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getXml(@PathParam("query") String username) {            
        System.out.println("test8");
        ViewTasksBusiness search= new ViewTasksBusiness();
        TasksXML tasks = search.getTasksByUser(username);
        System.out.println(">>>>>>>>>>>>>>>>>>" + tasks);
            
        System.out.println("test9");
        
        JAXBContext jaxbContext;
        try {            
            System.out.println("test10");
            jaxbContext = JAXBContext.newInstance(TasksXML.class);
        
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            System.out.println("test11");

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            System.out.println("test12");

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(tasks, sw);
            
            System.out.println("test13");

            return (sw.toString());
    
        } catch (JAXBException ex) {
            Logger.getLogger(ViewTasksResource.class.getName()).log(Level.SEVERE, null, ex);
            return("error happened");
        }
    }

    /**
     * PUT method for updating or creating an instance of SearchResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }    
}
