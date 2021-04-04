<%-- 
    Document   : shoping
    Created on : Mar 9, 2021, 9:12:45 PM
    Author     : ACER
--%>

<%@page import="phat.dtos.UserDTO"%>
<%@page import="phat.dtos.ProductDTO"%>
<%@page import="phat.dtos.CatagoryDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
    </head>
    <body>

        <%
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");

            List<CatagoryDTO> listCata = (List<CatagoryDTO>) request.getAttribute("LIST_CATA");
        %>

        <%
            if (user != null) {
        %>

        <h3>Name : <%=user.getFullName()%></h3>
        <a href="MainController?btnAction=Logout">Logout</a>
        <a href="MainController?btnAction=View">View</a></br>
        <%
        } else {
        %>
        <a href="MainController?btnAction=Login">Login</a></br>
        <%
            }

        %>
        <form action="MainController" method="POST">
            Phone Name: <input type="text" name="txtPhoneName" value="<%= request.getParameter("txtPhoneName") == null ? "" : request.getParameter("txtPhoneName")%>">
            Select your category: 
            <select name="cmbCate">
                <%
                    if (listCata != null) {
                        for (CatagoryDTO dto : listCata) {
                %>
                <option value="<%=dto.getCatagoryID()%>"> <%= dto.getCatagoryName()%> </option>
                <%
                    }
                %>
                <option value=""> All </option>
                <%
                    }

                %>
            </select>
            <input type="submit" name="btnAction" value="SearchPhone">

        </form>

        <%                String message = (String) request.getAttribute("MESSAGE");
            if (message != null) {
        %>
        <h3>
            <%= message%>
        </h3>
        <%
            }
        %>

        <div>
            <%
                List<ProductDTO> list = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
                if (list != null) {

            %>
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Category ID</th>
                    </tr>
                </thead>
                <tbody>
                    <%                        int count = 0;
                        for (ProductDTO dto : list) {
                    %>
                <form action="MainController" method="POST">
                    <tr>
                        <td><%= ++count%></td>
                        <td><%= dto.getProductID()%></td>
                        <td><%= dto.getProductName()%></td>
                        <td><%= dto.getPrice()%></td>
                        <td><input type="number" name="txtQuantity" value="1" min="1" max="<%= dto.getQuantity()%>" step="1"/></td>
                        <td><%= dto.getCatagoryID()%></td>
                    <input type="hidden" name="txtProductID" value="<%= dto.getProductID()%>"/>
                    <input type="hidden" name="txtProductName" value="<%= dto.getProductName()%>"/>
                    <input type="hidden" name="txtPrice" value="<%= dto.getPrice()%>"/>
                    <input type="hidden" name="txtCatagoryID" value="<%= dto.getCatagoryID()%>"/>
                    <%
                        if (user != null) {
                    %>
                    <td>
                        <input type="submit" name="btnAction" value="Add"/>
                    </td>
                    <%
                        }
                    %>
                    <input type="hidden" name="txtPhoneName" value="<%= request.getParameter("txtPhoneName") == null ? "" : request.getParameter("txtPhoneName")%>">
                    <input type="hidden" name="cmbCate" value="<%= request.getParameter("cmbCate") == null ? "" : request.getParameter("cmbCate")%>">
                    </tr>
                </form>
                <%
                    }
                %>
                </tbody>
            </table>
            <%
                }
            %>


        </div>


    </body>
</html>
