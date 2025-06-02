/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.viewtasks.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import todolist.viewtasks.helper.Task;

/**
 *
 * @author student
 */
public class Task_CRUD {
    
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
    
    public static Set<Task> searchForTasks(String username){
        System.out.println("test1 username: " + username);
        Set<Task> tasks = new HashSet<Task>();
            
            System.out.println("test24");
        
        try {
            System.out.println("test2");
            Connection con = getCon();
            String query = "SELECT * FROM ToDoList.Tasks WHERE User = \"" + username + "\"";
            PreparedStatement pstmt = con.prepareStatement(query);
            System.out.println("test3");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
            
            System.out.println("test25");
                int id = Integer.parseInt(rs.getString("TaskID"));
                String name = rs.getString("Task_Name");
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                String duedate = rs.getString("DueDate");
                String category = rs.getString("Category");
                
                System.out.println("id: " + id);
                System.out.println("name: " + name);
                System.out.println("description: " + description);
                System.out.println("status: " + status);
                System.out.println("duedate: " + duedate);
                System.out.println("category: " + category);
            
            System.out.println("test26");
                
                Task t = new Task(id, name, description, status, duedate, category, username);
                tasks.add(t);
            
            System.out.println("test27");
            }
            pstmt.close();
            rs.close();
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+tasks.size());
            
            System.out.println("test28");
        
        return tasks;
    }
}
