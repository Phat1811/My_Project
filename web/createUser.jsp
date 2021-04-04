<%-- 
    Document   : createUser
    Created on : Mar 16, 2021, 4:10:33 PM
    Author     : ACER
--%>

<%@page import="phat.dtos.UserErrorDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User Page</title>
    </head>
    <body>
        <%
            UserErrorDTO errorDTO = (UserErrorDTO) request.getAttribute("USER_ERROR");
            if(errorDTO == null) {
                errorDTO = new UserErrorDTO("", "", "", "", "", "","");
            }
        %>
        <a href="login.html">Login</a>
        <div>
            <h1>Create User</h1>
            <form action="MainController" method="POST">
                User ID: <input type="text" name="txtUserID">
                <%=errorDTO.getUserIDError()%></br>

                Full Name: <input type="text" name="txtFullName">
                <%=errorDTO.getFullNameError()%></br>
                
                Address: <input type="text" name="txtAddress">
                <%=errorDTO.getAddressError()%></br>
                
                Phone: <input type="text" name="txtPhone">
                <%=errorDTO.getPhoneError()%></br>

                Role ID: <input type="text" name="txtRoleID" value="US" readonly="true"></br>

                Password: <input type="password" name="txtPassword">
                <%=errorDTO.getPasswordError()%></br>

                Confirm: <input type="password" name="txtConfirm">
                <%=errorDTO.getConfirmError()%></br>

                <input type="submit" name="btnAction" value="Create User">
                <input type="reset"  value="Reset">
            </form>
        </div>
    </body>
</html>
