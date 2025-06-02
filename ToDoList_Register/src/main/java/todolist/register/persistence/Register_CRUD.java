/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.register.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author student
 */
public class Register_CRUD {
    private static Connection getCon(){
        System.out.println("test30");
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ToDoList", "root", "student");
            con = DriverManager.getConnection(System.getenv("DB_URL"), "root", "student");
            System.out.println("Connection established.");
        } catch(Exception e) {
            System.out.println(e);
        }
        return con;
    }
    
    public static void registerUser(String username, String password, String email, int role) {
        try {
            Connection con = getCon();
            String query = "";
            if (role == 1) {
                query = "INSERT INTO ToDoList.Admins (username, password, email) VALUES (\"" + username + "\", \"" + password + "\", \"" + email + "\")";
            } else if (role == 2) {
                query = "INSERT INTO ToDoList.Users (username, password, email) VALUES (\"" + username + "\", \"" + password + "\", \"" + email + "\")";
            }
            
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();            
            pstmt.close();
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
