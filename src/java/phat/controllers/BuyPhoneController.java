/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phat.daos.OrderDAO;
import phat.daos.OrderDetailDAO;
import phat.daos.ProductDAO;
import phat.dtos.CartDTO;
import phat.dtos.OrderDTO;
import phat.dtos.OrderDetailDTO;
import phat.dtos.ProductDTO;

/**
 *
 * @author ACER
 */
public class BuyPhoneController extends HttpServlet {

    private static final String SUCCESS = "SearchPhoneController";
    private static final String ERROR = "viewBillPage.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session=request.getSession();
            CartDTO cart= (CartDTO) session.getAttribute("CART");
            String userID = request.getParameter("txtUserID");
            String totalmoney= request.getParameter("totalMoney");
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date date = new Date(System.currentTimeMillis());
            ProductDAO pdao = new ProductDAO();
            boolean check = true;
            for (ProductDTO productDTO : cart.getCart().values()) {
                if(!pdao.checkQuantity(productDTO.getProductID(), productDTO.getQuantity()) ){
                    check=false;
                }
            }
            if (check == true) {//them du lieu vao 2 bang tblOrder va tblOrderDetail
                OrderDAO orderDao=new OrderDAO();
                String orderID= "O"+(orderDao.getNoOfOrder()+1);
                String orderDate = formatter.format(date);
                OrderDTO orderDto=new OrderDTO(orderID, orderDate, Float.parseFloat(totalmoney), userID);
                orderDao.createOrder(orderDto);
                
                OrderDetailDAO orderDetailDao=new OrderDetailDAO();
                String orderDetailID;
                for (ProductDTO productDTO : cart.getCart().values()) {
                    
                    orderDetailID="D"+(orderDetailDao.getNoOfOrderDetail()+1);
                    OrderDetailDTO orderDetailDto=new OrderDetailDTO(orderDetailID, Float.parseFloat(totalmoney), productDTO.getQuantity(),orderID, productDTO.getProductID());
                    orderDetailDao.createDetailOrder(orderDetailDto);
                    
                }
                request.setAttribute("MESSAGE", "Buy successful !"); 
                url = SUCCESS;
                session.setAttribute("CART", null);
            } else {
                request.setAttribute("MESSAGE_BUY_ERROR", "Buy Fail! We not enought Phone for you");          
           }
        } catch (Exception e) {
            log("Error at BookRoomServlet: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
