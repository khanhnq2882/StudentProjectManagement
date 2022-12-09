package com.management.controller.Class;

import com.management.dao.DAOChangePass;
import com.management.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ChangePassWordController", urlPatterns = {"/ChangePassWord"})
public class ChangePassWordController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOChangePass daoThanh = new DAOChangePass();
            String service = request.getParameter("go");
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");
            if (Loged == null) {
                request.getRequestDispatcher("/views/Login.jsp").forward(request, response);
            }
            if (service == null) {
                service = "FormPass";
            }
            if (service.equals("FormPass")) {
                session.removeAttribute("mess_p");
                request.getRequestDispatcher("/views/ChangePass.jsp").forward(request, response);
            }
            if (service.equals("ChangeForm")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    session.removeAttribute("mess_p");
                    request.getRequestDispatcher("/views/ChangePass.jsp").forward(request, response);
                } else {
                    String PassNow = request.getParameter("PassNow");
                    String passNew = request.getParameter("passNew");
                    String re_pass = request.getParameter("re_pass");

                    if (PassNow.equals("") || passNew.equals("") || re_pass.equals("")) {

                        request.setAttribute("PassNow", PassNow);
                        request.setAttribute("passNew", passNew);
                        request.setAttribute("re_pass", re_pass);

                        session.setAttribute("mess_p", "Input cannot be null ");
                        request.getRequestDispatcher("/views/ChangePass.jsp").forward(request, response);
                        return;
                    }
                    if (!PassNow.equals(Loged.getPass())) {

                        request.setAttribute("PassNow", PassNow);
                        request.setAttribute("passNew", passNew);
                        request.setAttribute("re_pass", re_pass);

                        session.setAttribute("mess_p", "Your current password is incorrect");
                        request.getRequestDispatcher("/views/ChangePass.jsp").forward(request, response);
                        return;
                    }

                    if (!passNew.equals(re_pass)) {

                        request.setAttribute("PassNow", PassNow);
                        request.setAttribute("passNew", passNew);
                        request.setAttribute("re_pass", re_pass);

                        session.setAttribute("mess_p", "New pass and re-pass are not the same");
                        request.getRequestDispatcher("/views/ChangePass.jsp").forward(request, response);
                        return;
                    }
                    if (passNew.equals(Loged.getPass())) {

                        request.setAttribute("PassNow", PassNow);
                        request.setAttribute("passNew", passNew);
                        request.setAttribute("re_pass", re_pass);

                        session.setAttribute("mess_p", "Old password and new password cannot not the same");
                        request.getRequestDispatcher("/views/ChangePass.jsp").forward(request, response);
                        return;
                    }
                    if (PassNow.equals(Loged.getPass()) && passNew.equals(re_pass)) {

                        daoThanh.UpdatePass(re_pass, Loged.getUser_id());
                        session.setAttribute("ok", 1);
                        session.setAttribute("mess_p", "Your pass has been changed");
                        request.getRequestDispatcher("/views/ChangePass.jsp").forward(request, response);
                    }
                }
            }
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

