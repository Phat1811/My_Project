<%-- 
    Document   : searchProduct
    Created on : Mar 18, 2021, 12:43:41 AM
    Author     : ACER
--%>

<%@page import="phat.dtos.UserDTO"%>
<%@page import="java.util.List"%>
<%@page import="phat.dtos.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Page</title>
    </head>
    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        %>
        <a href="MainController?btnAction=Insert Page">Insert New Phone</a></br>
        <a href="MainController?btnAction=Logout">Logout</a></br>
        
        <h3>Name : <%= user.getFullName()%></h3>
        <%
            String searchValue = request.getParameter("name");
            if (searchValue == null) {
                searchValue = "";
            }
        %>
        <form action="MainController">
            Search Name: <input type="text" name="txtSearchName" value="<%= searchValue%>">
            Search Type: <input type="text" name="txtSearchType" value="<%= searchValue%>">
            <input type="submit" name="btnAction" value="Search Product"/>
        </form>
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
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (ProductDTO dto : list) {
                %>
            <form action="MainController" method="POST">
                <tr>
                    <td><%= count++%></td>
                    <td><input type ="text" name="txtProductID" value="<%= dto.getProductID()%>" readonly="true"</td>
                    <td>
                        <input type ="text" name="txtProductName" value="<%= dto.getProductName()%>"/>
                    </td>
                    <td>
                        <input type ="number" name="txtPrice" value="<%= dto.getPrice()%>"/>
                    </td>
                    <td>
                        <input type ="number" name="txtQuantity" value="<%= dto.getQuantity()%>"/>
                    </td>
                    <td>
                        <input type ="text" name="txtCatagoryID" value="<%= dto.getCatagoryID()%>"/>
                    </td>
                    <td>
                        <a href="MainController?btnAction=Delete&txtProductID=<%= dto.getProductID()%>&txtSearchName=<%= searchValue%>&txtSearchType=<%= searchValue%>">Delete</a>

                    </td>

                    <td>
                        <input type="submit" name="btnAction" value="Update"/>
                        <input type="hidden" name="txtSearchName" value="<%=request.getParameter("txtSearchName")%>"/>
                        <input type="hidden" name="txtSearchType" value="<%=request.getParameter("txtSearchType")%>"/>
                    </td>
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
</body>
</html>
