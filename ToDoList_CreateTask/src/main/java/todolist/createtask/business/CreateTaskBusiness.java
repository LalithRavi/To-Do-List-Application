/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.createtask.business;

import java.util.HashMap;
import java.util.Map;
import todolist.createtask.persistence.CreateTask_CRUD;

/**
 *
 * @author student
 */
public class CreateTaskBusiness {

    public void createTask(String query) {       
        System.out.println("inside create task microservice");
        Map<String, String> userData = extractTaskData(query);
        String name = userData.get("taskName");
        String description = userData.get("description");
        String status = userData.get("status");
        String duedate = userData.get("duedate");
        String category = userData.get("category");
        String username = userData.get("username");
        System.out.println("username: " + username);
        
        CreateTask_CRUD.createTask(name, description, status, duedate, category, username);
    }
    
    
    
    public static Map<String, String> extractTaskData(String input) {
        Map<String, String> userData = new HashMap<>();
        String[] pairs = input.split("&");
        
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];
                userData.put(key, value);
            }
        }
        return userData;
    }
    
}
