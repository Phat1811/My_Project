/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phat.daos.CatagoryDAO;
import phat.daos.ProductDAO;
import phat.dtos.CatagoryDTO;
import phat.dtos.ProductDTO;

/**
 *
 * @author ACER
 */
public class SearchPhoneController extends HttpServlet {

    private final static String ERROR = "shopping.jsp";
    private final static String SUCCESS = "shopping.jsp";
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
            String searchName = request.getParameter("txtPhoneName");
            String searchType = request.getParameter("cmbCate");
            CatagoryDAO dao = new CatagoryDAO();
            List<CatagoryDTO> listCatagory = dao.getListCatagory();
            if (listCatagory != null) {
                request.setAttribute("LIST_CATA", listCatagory);
                url = SUCCESS;
            }
            ProductDAO pdao = new ProductDAO();
            List<ProductDTO> list = pdao.getListProduct(searchName,searchType);
            if (list != null) {
                request.setAttribute("LIST_PRODUCT", list);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at: SearchPhone controller" + e.toString());
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
