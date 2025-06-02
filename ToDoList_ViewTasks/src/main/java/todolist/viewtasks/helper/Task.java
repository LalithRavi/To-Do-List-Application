/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.viewtasks.helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class Task {
    private int id;
    private String name, description, status, duedate, category, user;

    public Task(int id, String name, String description, String status, String duedate, String category, String user) {
            
            System.out.println("test16");
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.duedate = duedate;
        this.category = category;
        this.user = user;
        System.out.println("test29");
    }
    
    public Task() {
        
    }

    public String getName() {
            
            System.out.println("test17");
        return name;
    }

    public String getDescription() {
            
            System.out.println("test18");
        return description;
    }

    public String getStatus() {
            
            System.out.println("test19");
        return status;
    }

    public String getDuedate() {
            
            System.out.println("test20");
        return duedate;
    }

    public String getCategory() {
            
            System.out.println("test21");
        return category;
    }

    public String getUser() {
            
            System.out.println("test22");
        return user;
    }

    public int getId() {
            
            System.out.println("test23");
        return id;
    }
}
