<%-- 
    Document   : insertProduct
    Created on : Mar 17, 2021, 10:39:03 PM
    Author     : ACER
--%>

<%@page import="phat.dtos.CatagoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="phat.dtos.ProductErrorDTO"%>
<%@page import="phat.dtos.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Product Page</title>
    </head>
    <body>
        <%
            List<CatagoryDTO> listCata = (List<CatagoryDTO>) request.getAttribute("LIST_CATA");
            ProductErrorDTO errorDTO = (ProductErrorDTO) request.getAttribute("PRODUCT_ERROR");
            if (errorDTO == null) {
                errorDTO = new ProductErrorDTO("", "", "", "", "");
            }
        %>
        <form action="MainController" method="POST">
            Product ID: <input type="text" name="txtProductID"></br>
            <%= errorDTO.getProductIDError()%></br>
            Product Name: <input type="text" name="txtProductName"></br>
            <%= errorDTO.getProductNameError()%></br>
            Product Price: <input type="number" name="txtPrice"></br>
            <%= errorDTO.getPriceError()%></br>
            Product Quantity: <input type="number" name="txtQuantity"></br>
            <%= errorDTO.getQuantityError()%></br>
            Category ID: <select name="cmbCate">
                <%
                    if (listCata != null) {
                        for (CatagoryDTO dto : listCata) {
                %>
                <option value="<%=dto.getCatagoryID()%>"> <%= dto.getCatagoryName()%> </option>
                <%
                        }
                    }
                %>
            </select></br>
            <input type="submit" name="btnAction" value="InsertProduct">
            <input type="reset"  value="Reset">
        </form>
    </body>
</html>
