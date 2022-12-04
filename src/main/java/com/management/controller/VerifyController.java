package com.management.controller;
import com.management.dao.DAOSen;
import com.management.entity.User;
import com.management.util.Alert;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet(name = "VerifyController", urlPatterns = {"/Verify"})
public class VerifyController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOSen dao = new DAOSen();
            String Verify = request.getParameter("Verify");
            HttpSession seession = request.getSession();
            if (Verify == null) {
                request.getRequestDispatcher("views/Verify.jsp").forward(request, response);
            } else {
                String code = (String) seession.getAttribute("code");
                String email = (String) seession.getAttribute("email");
                String user = (String) seession.getAttribute("user");
                String pass = (String) seession.getAttribute("pass");
                String name = (String) seession.getAttribute("name");
                String coded = request.getParameter("coded");
                if (code.equals(coded)) {
                    dao.AddUser(email, user, pass, name);
                    request.setAttribute("alert", new Alert().alert("", "Register successfully!", Alert.SUCCESS));
                    request.getRequestDispatcher("views/Login.jsp").forward(request, response);
                } else {
                    request.setAttribute("alert", new Alert().alert("", "Invalid code!", Alert.ERROR));
                    request.getRequestDispatcher("views/Verify.jsp").forward(request, response);
                }
            }
        }catch (Exception e) {
            request.getRequestDispatcher("views/404.html").forward(request, response);
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

