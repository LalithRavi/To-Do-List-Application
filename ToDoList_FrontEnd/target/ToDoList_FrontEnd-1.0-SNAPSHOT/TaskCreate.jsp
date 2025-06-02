<%-- 
    Document   : TaskCreate
    Created on : Feb 23, 2024, 6:50:18 PM
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Task</title>
        <link rel="stylesheet" href="css/TaskCreate.css">
    </head>
    <body>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <script>
                alert('<%= request.getAttribute("errorMessage") %>');
            </script>
        <% } %>
        <div class="wholePage">            
            <h1>Create Task</h1>
            <form name="createTask" action="CreateTask" method="POST">
                <div class="formRow">
                    <label for="nameField">Name:</label>
                    <input type="text" id="nameField" name="nameField" placeholder="Name"/>
                </div>
                <div class="formRow">
                    <label for="descriptionField">Description:</label>
                    <textarea id="descriptionField" name="descriptionField" placeholder="Description"></textarea>
                </div>
                <div class="formRow">
                    <label for="statusField">Status:</label>
                    <select id="statusField" name="statusField">
                        <option value="empty" selected></option>
                        <option value="In Progress">In Progress</option>
                        <option value="Not Started">Not Started</option>
                        <option value="Completed">Completed</option>
                    </select>
                </div>
                <div class="formRow">
                    <label for="dateField">Due Date:</label>
                    <input type="date" id="dateField" name="dateField"/>
                </div>
                <div class="formRow">
                    <label for="categoryField">Category:</label>
                    <input type="text" id="categoryField" name="categoryField"/>
                </div>
                <button type="submit" value="CreateTask" name="CreateTask">Submit</button>
            </form>
        </div>
    </body>
</html>
