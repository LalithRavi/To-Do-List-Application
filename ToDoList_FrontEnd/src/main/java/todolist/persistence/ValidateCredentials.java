/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.persistence;

import java.util.ArrayList;

/**
 *
 * @author student
 */
public class ValidateCredentials {
    public static ArrayList<String[]> users = new ArrayList<String[]>();
    public static ArrayList<String[]> admins = new ArrayList<String[]>();
    
    public static int validateLogin(String username, String password) {
        DatabaseActions.retrieveUsersFromDatabase(); DatabaseActions.retrieveAdminsFromDatabase();
        
        if (isAdminValid(username, password)) {
            return 1;
        } else if (isUserValid(username, password)) {
            return 2;
        }
        
        return 0;
    }
    
    private static boolean isUserValid(String username, String password) {
        for (String[] user : users) {
            if (user[0].equals(username) && user[1].equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean isAdminValid(String username, String password) {
        for (String[] admin : admins) {
            if (admin[0].equals(username) && admin[1].equals(password)) {
                return true;
            }
        }
        return false;
    }
}
