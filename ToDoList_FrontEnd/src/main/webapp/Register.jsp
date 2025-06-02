<%-- 
    Document   : Register
    Created on : Feb 16, 2024, 11:44:57 AM
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/LoginRegister.css">
        <title>To Do List Application</title>
    </head>
    <body>
        <a href="index.html"><button type="submit" value="Home" name="Home"> Home </button></a>
        <div class="registerHome">
            <div class="register2">
                <h3>Register</h3>
                <form name="register" action="Register" method="POST">
                    <p>Username: </p>
                    <input type="text" name="username" placeholder="Enter Username" size="30"/>
                    <p>Password: </p>
                    <input type="password" name="password" placeholder="Enter Password" size="30"/>
                    <p>Email: </p>
                    <input type="text" name="email" placeholder="Enter Email" size="30" />
                    <p>Role: </p>
                    <select name="role">
                        <option value="1">Admin</option>
                        <option value="2" selected>User</option>
                    </select>
                    <br><br>
                    <button type="submit" value="Register" name="Register"> Register </button>
                </form>
            </div>
            
            <div class="login2">
                <p>Already have an account?</p>
                <a href="Login.jsp"><button type="submit" value="Login" name="Login"> Login </button></a>
            </div>
        </div>
    </body>
</html>