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
import phat.daos.UserDAO;
import phat.dtos.UserDTO;
import phat.dtos.UserErrorDTO;

/**
 *
 * @author ACER
 */
public class CreateUserController extends HttpServlet {

    private final static String ERROR = "createUser.jsp";
    private final static String SUCCESS = "login.jsp";
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
        UserErrorDTO errorObject = new UserErrorDTO("", "", "", "", "", "", "");
        try {
            String userID = request.getParameter("txtUserID");
            String fullName = request.getParameter("txtFullName");
            String roleID = request.getParameter("txtRoleID");
            String address = request.getParameter("txtAddress");
            String phone = request.getParameter("txtPhone");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            
            
            UserDAO dao = new UserDAO();
            boolean check = true;
            if (userID.isEmpty()) {
                check = false;
                errorObject.setUserIDError("UserID is not empty !");
            }
            if (fullName.isEmpty()) {
                check = false;
                errorObject.setFullNameError("FullName is not empty !");
            }
            if (password.isEmpty()) {
                check = false;
                errorObject.setPasswordError("Password is not empty !");
            }
            if (address.isEmpty()) {
                check = false;
                errorObject.setAddressError("Address is not empty !");
            }
            if (phone.isEmpty() || phone.length() > 10) {
                check = false;
                errorObject.setPhoneError("Phone must have 10 numbers !");
            }
            if (!password.equals(confirm)) {
                check = false;
                errorObject.setConfirmError("Confirm must be the same as password");
            }
            if (dao.checkID(userID)==true) {
                check = false;
                errorObject.setUserIDError("UserID is duplicate !");
            }
            if (check == true) {
                UserDTO user = new UserDTO(userID, fullName, address, phone, password, roleID);
                dao.create(user);
                url = SUCCESS;
            } else {
                request.setAttribute("USER_ERROR", errorObject);
            }

        } catch (Exception e) {
            log("Error at CreateUserServlet: " + e.toString());
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
