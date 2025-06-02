/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.helper;

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
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.duedate = duedate;
        this.category = category;
        this.user = user;
    }
    
    public Task() {
        
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getDuedate() {
        return duedate;
    }

    public String getCategory() {
        return category;
    }

    public String getUser() {
        return user;
    }

    public int getId() {
        return id;
    }
}