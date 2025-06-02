/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static todolist.persistence.ValidateCredentials.admins;
import static todolist.persistence.ValidateCredentials.users;

/**
 *
 * @author student
 */
public class DatabaseActions {
    public static Connection getCon() {
        Connection con = null;
        try {            
            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ToDoList?autoReconnect=true&useSSL=false", "root", "student");
            con = DriverManager.getConnection(System.getenv("DB_URL"), "root", "student");
        } catch (Exception e) {     
            System.out.println(e);
        }
        return con;
    }
    
    public static void retrieveUsersFromDatabase() {
        try {
            Connection con = getCon();
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM Users")) {                
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String[] user = new String[] {rs.getString(1), rs.getString(2), rs.getString(3)};
                    users.add(user);
                }
                ps.close();
            }
            
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void retrieveAdminsFromDatabase() {
        try {
            Connection con = getCon();
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM Admins")) {                
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String[] admin = new String[] {rs.getString(1), rs.getString(2), rs.getString(3)};
                    admins.add(admin);
                }
                ps.close();
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
