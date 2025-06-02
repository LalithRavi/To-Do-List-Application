<%-- 
    Document   : Login
    Created on : Feb 16, 2024, 11:44:16 AM
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>To Do List Application</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/LoginRegister.css">
    </head>
    <body>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <script>
                alert('<%= request.getAttribute("errorMessage") %>');
            </script>
        <% } %>
        <a href="index.html"><button type="submit" value="Home" name="Home"> Home </button></a>
        <div class="home">
            <div class="login">
                <h3>Login</h3>
                <form name="login" action="Login" method="POST">
                    <label for="username">Username: </label>
                    <input type="text" id="username" name="username" placeholder="Enter Username" size="30"/>
                    <br><br>
                    <label for="pwd">Password: </label>
                    <input type="password" id="pwd" name="password" placeholder="Enter Password" size="30"/>
                    <br><br>
                    <button type="submit" value="Login" name="Login"> Login </button>
                </form>
            </div>
            
            <div class="register">
                <p>Don't have an account?</p>
                <a href="Register.jsp"><button type="submit" value="Register" name="Register"> Register </button></a>
            </div>
        </div>
    </body>
</html>