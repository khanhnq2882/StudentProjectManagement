package com.management.controller;

import com.management.dao.DAOSen;
import com.management.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", urlPatterns = {"/Login_sen"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession seession = request.getSession();
            seession.removeAttribute("user");
            seession.removeAttribute("pass");
            DAOSen dao = new DAOSen();
            Cookie arr[] = request.getCookies();
            if (arr != null) {
                for (Cookie o : arr) {
                    if (o.getName().equals("userC")) {
                        request.setAttribute("username", o.getValue());
                    }
                    if (o.getName().equals("passC")) {
                        request.setAttribute("password", o.getValue());
                    }
                }
            }
            String Login = request.getParameter("Login");
            if (Login == null) {
                request.getRequestDispatcher("jsp/Login.jsp").forward(request, response);
            }
            if (Login != null) {
                String user = request.getParameter("user");
                String pass = request.getParameter("pass");
                String passc = dao.encrypt(pass);
                String remember = request.getParameter("remember");
                User Loged = dao.Login(user, passc);
                User Loged1 = dao.Login1(user, passc);
                if (Loged == null && Loged1 == null) {
                    seession.setAttribute("user", user);
                    seession.setAttribute("pass", pass);
                    request.setAttribute("mess", "Sai tài khoản hoặc mật khẩu rồi bạn!");
                    request.getRequestDispatcher("jsp/Login.jsp").forward(request, response);
                    return;
                } else {
                    if (Loged != null) {
                        if (Loged.getStatus() == 2) {
                            seession.setAttribute("user", user);
                            seession.setAttribute("pass", pass);
                            request.setAttribute("mess", "Tài khoản đã bị khóa!");
                            request.getRequestDispatcher("jsp/Login.jsp").forward(request, response);
                            return;
                        }
                        seession.setAttribute("Loged", Loged);
                    }
                    if (Loged1 != null) {
                        if (Loged1.getStatus() == 2) {
                            seession.setAttribute("user", user);
                            seession.setAttribute("pass", pass);
                            request.setAttribute("mess", "Tài khoản đã bị khóa!");
                            request.getRequestDispatcher("jsp/Login.jsp").forward(request, response);
                            return;
                        }
                        seession.setAttribute("Loged", Loged1);
                    }
                    Cookie uC = new Cookie("userC", user);
                    Cookie pC = new Cookie("passC", pass);
                    uC.setMaxAge(60 * 60 * 24 * 365);
                    if (remember != null) {
                        pC.setMaxAge(60 * 60 * 24 * 365);
                    } else {
                        pC.setMaxAge(0);
                    }
                    response.addCookie(pC);
                    response.addCookie(uC);
                    request.getRequestDispatcher("Home").forward(request, response);
                    return;
                }
            }
        } catch (Exception e) {
            request.getRequestDispatcher("404.html").forward(request, response);
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
