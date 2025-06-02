/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.business;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;
import todolist.helper.TasksXML;

/**
 *
 * @author student
 */
public class Business {
    public static boolean isAuthenticated(String username, String passwrod) {
        return true;
    }
    
    public static TasksXML getServicesViewTasks(String query, String token) throws IOException {

        Client searchclient = ClientBuilder.newClient();
//        WebTarget searchwebTarget = searchclient.target("http://localhost:8080/ToDoList_ViewTasks/webresources/viewtasks");
        WebTarget searchwebTarget = searchclient.target("http://" + System.getenv("viewtaskservice") + "/ToDoList_ViewTasks/webresources/viewtasks");
        System.out.println("Query: " + query);
        InputStream is = searchwebTarget.path(query).request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        System.out.println("xml: " + xml);
        TasksXML tasks = taskxmltoObjects(xml);        
        return tasks;
    }
    
    public static void getServicesRegisterUser(String query, String token) throws IOException {
        Client searchclient = ClientBuilder.newClient();
//        WebTarget searchwebTarget = searchclient.target("http://localhost:8080/ToDoList_Register/webresources/register");
        WebTarget searchwebTarget = searchclient.target("http://" + System.getenv("registerservice") + "/ToDoList_Register/webresources/register");      
        InputStream is = searchwebTarget.path(query).request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
    }
    
    public static void getServicesCreateTask(String query, String token) throws IOException {
        Client searchclient = ClientBuilder.newClient();
//        WebTarget searchwebTarget = searchclient.target("http://localhost:8080/ToDoList_CreateTask/webresources/createtask");
        WebTarget searchwebTarget = searchclient.target("http://" + System.getenv("createtaskservice") + "/ToDoList_CreateTask/webresources/createtask");       
        InputStream is = searchwebTarget.path(query).request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        
    }
    
    private static TasksXML taskxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(TasksXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            TasksXML tasks = (TasksXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return tasks;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}