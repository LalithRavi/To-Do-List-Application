/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.viewtasks.business;

import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import todolist.viewtasks.helper.Task;
import todolist.viewtasks.helper.TasksXML;
import todolist.viewtasks.persistence.Task_CRUD;

/**
 *
 * @author student
 */
public class ViewTasksBusiness {
    
    public  TasksXML getTasksByUser(String username){
        System.out.println("test4 username: " + username);
        Set<Task> tasks = Task_CRUD.searchForTasks(username);
        Map<String ,Task> allUserTasks= new HashMap ();
        
        System.out.println("test5");

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&"+ tasks.size());
        
        for(Task task : tasks){
            allUserTasks.put(String.valueOf(task.getId()), task);
        }
        
        System.out.println("**********************"+ allUserTasks.size());
        
        System.out.println("test6");
        
        TasksXML t;
        t = new TasksXML();
        t.setTasks(new ArrayList(allUserTasks.values()));        
        System.out.println("test7");
        return (t);
    }
}
