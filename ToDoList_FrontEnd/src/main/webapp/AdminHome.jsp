<%-- 
    Document   : AdminHome
    Created on : Feb 16, 2024, 11:45:31 AM
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="css/HomePage.css">
    </head>
    <body>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <script>
                alert('<%= request.getAttribute("errorMessage") %>');
            </script>
        <% } %>
        <h1>Hello <%= request.getAttribute("username") %>!</h1>
        <a href="ManageAccounts.jsp"><button type="submit" value="Manage" name="Manage">Manage Accounts</button></a>
        <a href="Login.jsp"><button type="submit" value="Logout" name="Logout">Logout</button></a>
    </body>
</html>
