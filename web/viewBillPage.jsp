<%-- 
    Document   : shoping
    Created on : Mar 9, 2021, 9:12:45 PM
    Author     : ACER
--%>
<%@page import="phat.dtos.ProductDTO"%>
<%@page import="java.util.Set"%>
<%@page import="phat.dtos.UserDTO"%>
<%@page import="phat.dtos.CartDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Page</title>
        <link rel="stylesheet" href="CSS/viewBillPage.css">
        
    </head>
    <body>
        
                <h1>Your bill: </h1>
                <%
                UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                CartDTO cart = (CartDTO) session.getAttribute("CART");
                float totalMoney = 0;
                if (cart != null) {
                    int no = 1;      
                %>
                    <h3>Name : <%=user.getFullName()%></h3>
                    
                                
                    <br>
                    <table border="1">
                        <thead>
                            <tr>
    
                                <th>No</th>
                                <th>Phone</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Catagory</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%  
                                Set<String> key=cart.getCart().keySet();
                                for (String string : key) {
                                    totalMoney += cart.getCart().get(string).getPrice() * cart.getCart().get(string).getQuantity();
                            %>
                                    <form action="MainController">
                                        <tr>
                                            <td><%= no++%></td>
                                            <td><%= cart.getCart().get(string).getProductName()%></td>
                                            <td><%= cart.getCart().get(string).getPrice()%></td>
                                            <td><input type="number" name="txtQuantity" value="<%= cart.getCart().get(string).getQuantity()%>"</td>
                                            <td><%= cart.getCart().get(string).getCatagoryID()%></td>
                                            <td>                       
                                                <input type="submit" name="btnAction" value="Update Phone"/>
                                                <input type="hidden" name="txtProductID" value="<%= cart.getCart().get(string).getProductID()%>"/>
                                            </td>
                                            <td>                       
                                                <input type="submit" name="btnAction" value="Delete Phone"/>
                                                <input type="hidden" name="txtProductID" value="<%= cart.getCart().get(string).getProductID()%>"/>
                                            </td>
                                        </tr>
                                    </form>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
         
                    <h2>Total: <%=totalMoney%></h2>
                    
                    <form action="MainController" >
                        <input type="submit" name="btnAction" value="Buy Phone"/>
                        <input type="hidden" name="txtUserID" value="<%=user.getUserID()%>"/>    
                        <input type="hidden" name="totalMoney" value="<%=totalMoney%>"/>  
                    </form>


                <%
                }
                %>

                <% String messageBuy = (String) request.getAttribute("MESSAGE_BUY");
                    if (messageBuy != null) {
                %>   
                        <h3><%=messageBuy%></h3>
                <%  }
                %>
                <a href="SearchPhoneController" class="add">Add more Phone !</a> 

                <% String messageErrorBuy = (String) request.getAttribute("MESSAGE_BUY_ERROR");
                    if (messageErrorBuy != null) {
                %>   
                        <h3><%=messageErrorBuy%></h3>
                <%  }
                %>
            
                
        
   </body>
</html>
