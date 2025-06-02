<%-- 
    Document   : UserHome
    Created on : Feb 16, 2024, 11:45:58 AM
    Author     : student
--%>

<%@page import="todolist.helper.Task"%>
<%@page import="todolist.helper.TasksXML"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <link rel="stylesheet" href="css/HomePage.css">
    </head>
    <body>        
        <% if (request.getAttribute("successMessage") != null) { %>
            <script>
                alert('<%= request.getAttribute("successMessage") %>');
            </script>
        <% } %>
        
        <% if (request.getAttribute("errorMessage") != null) { %>
            <script>
                alert('<%= request.getAttribute("errorMessage") %>');
            </script>
        <% } %>
        
        <a href="Login.jsp"><button type="submit" value="Logout" name="Logout">Logout</button></a>
        <h1>Hello <%= request.getAttribute("username") %>!</h1>
            <input type="hidden" id="selectedTaskDelete" name="selectedTaskDelete" value="" />
            <table border="2">
                <tr>
                    <td>ID</td>
                    <td>Task Name</td>
                    <td>Description</td>
                    <td>Status</td>
                    <td>Due Date</td>
                    <td>Category</td>
                    <td>Edit</td>
                </tr>
                    <% try {
                        TasksXML tasks = (TasksXML) request.getAttribute("taskResults");
                    
                        if (tasks!= null) {
                            for (Task task : tasks.getTasks()) { %>
                                <tr><td><%=task.getId()%></td>
                                <td><%=task.getName()%></td>
                                <td><%=task.getDescription()%></td>
                                <td><%=task.getStatus()%></td>
                                <td><%=task.getDuedate()%></td>
                                <td><%=task.getCategory()%></td>
                                <td><input type="checkbox"></td>
                            <% }
                        }
                    } catch (Exception e) { %>
                                <tr><td>No tasks to display</td>
                    <% } %>
                </tr>
            </table>
        <br>
        
        <!--<button type="submit" value="CreateTask" name="CreateTask">Create Task</button>-->
        <a href="TaskCreate.jsp"><button type="submit" value="CreateTask" name="CreateTask">Create Task</button></a>
    </body>
</html>