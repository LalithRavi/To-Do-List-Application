/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.register.business;

import java.util.HashMap;
import java.util.Map;
import todolist.register.persistence.Register_CRUD;

/**
 *
 * @author student
 */
public class RegisterBusiness {
    public void registerUser(String query){
        Map<String, String> userData = extractUserData(query);
        String username = userData.get("username");
        String password = userData.get("password");
        String email = userData.get("email");
        int role = Integer.parseInt(userData.get("role"));
        
        Register_CRUD.registerUser(username, password, email, role);
    }
    
    public static Map<String, String> extractUserData(String input) {
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