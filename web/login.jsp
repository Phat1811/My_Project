<%-- 
    Document   : login
    Created on : Mar 17, 2021, 1:27:29 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
     <body>
        <h1>Login</h1>
        <form action="MainController" method="POST">
            User ID:<input type="text" name="txtUserID"/></br>
            Password: <input type="password" name="txtPassword"/></br>
            <input type="submit" name="btnAction" value="Login"/>
            <input type="reset" value="Reset"/>
        </form>
        <a href="MainController?btnAction=Create Page">Create User</a>
        <a href="MainController?btnAction=SearchPhone">Shopping Page</a>
    </body>
</html>
