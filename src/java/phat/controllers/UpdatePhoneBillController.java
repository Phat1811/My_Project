/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phat.dtos.CartDTO;
import phat.dtos.ProductDTO;

/**
 *
 * @author ACER
 */
public class UpdatePhoneBillController extends HttpServlet {

    private final static String ERROR = "viewBillPage.jsp";
    private final static String SUCCESS = "viewBillPage.jsp";
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
        String url=ERROR;
        try {
            String quantityText=request.getParameter("txtQuantity");
            String productID=request.getParameter("txtProductID");
            int quantity = Integer.parseInt(quantityText);
            HttpSession session = request.getSession();
            ProductDTO product = new ProductDTO();
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            for (ProductDTO dto : cart.getCart().values()) {
                if(dto.getProductID().equals(productID)){
                    product = new ProductDTO(productID, dto.getProductName(), dto.getPrice(), quantity, dto.getCatagoryID());
                    break;
                }
            }
            cart.update(product);
            session.setAttribute("CART", cart);
            url = SUCCESS;
            
        } catch (Exception e) {
            log("Error at UpdateBillSerlvet: " + e.toString());
        }finally{
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
