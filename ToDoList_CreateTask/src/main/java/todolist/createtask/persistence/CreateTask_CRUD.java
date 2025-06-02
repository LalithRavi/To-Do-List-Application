/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.createtask.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author student
 */
public class CreateTask_CRUD {
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

    public static void createTask(String name, String description, String status, String duedate, String category, String username) {
        try {
            Connection con = getCon();
            String query = "INSERT INTO ToDoList.Tasks (Task_Name, Description, Status, DueDate, Category, User) VALUES (\"" + name + "\", \"" + description + "\", \"" + status + "\", \"" + duedate + "\", \"" + category + "\", \"" + username + "\")";
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
