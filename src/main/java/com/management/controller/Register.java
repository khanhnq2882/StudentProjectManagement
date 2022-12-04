package com.management.controller;


import com.management.dao.DAOSen;
import com.management.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOSen dao = new DAOSen();
            String Register = request.getParameter("Register");
            if (Register == null) {
                request.getRequestDispatcher("views/register.jsp").forward(request, response);
            } else {
                List<User> list = dao.AllUser();
                String user = request.getParameter("user");
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String pass = request.getParameter("pass");
                String repass = request.getParameter("repass");
                request.setAttribute("puser", user);
                request.setAttribute("pname", name);
                request.setAttribute("pemail", email);
                request.setAttribute("ppass", pass);
                request.setAttribute("prepass", repass);

                for (User o : list) {
                    if (user.equals(o.getUser())) {
                        request.setAttribute("err", "Tài khoản đã tồn tại!");
                        request.getRequestDispatcher("views/register.jsp").forward(request, response);
                    }
                    if (email.equals(o.getEmail())) {
                        request.setAttribute("err", "Email đã có người sử dụng!");
                        request.getRequestDispatcher("views/register.jsp").forward(request, response);
                    }
                }
                if (!pass.equals(repass)) {
                    request.setAttribute("err", "Mật khẩu không giống nhau!");
                    request.getRequestDispatcher("views/register.jsp").forward(request, response);
                }
                if (user.length() > 15 || pass.length() > 15 || user.length() < 3 || pass.length() < 6) {
                    request.setAttribute("err", "Tài khoản phải lớn hơn 3 và bé hơn 15 ký tự <br> Mật khẩu phải lớn hơn 6 và bé hơn 15 ký tự");
                    request.getRequestDispatcher("views/register.jsp").forward(request, response);
                } else {
                    request.setAttribute("err", "Đăng kí thành công. Check mail để verify.");
                    String subject = "This mail just for test.";
                    String code = dao.RandomBullSh();
                    String message = "<!DOCTYPE html>\n"
                            + "<html lang=\"en\">\n"
                            + "\n"
                            + "<head>\n"
                            + "</head>\n"
                            + "\n"
                            + "<body>\n"
                            + "    <h3 style=\"color: blue;\">This mail just for test</h3>\n"
                            + "    <div>Regis successful</div>\n"
                            + "    <div>Pls verify your account.</div>\n"
                            + "    <h1>" + code + "</h1>\n"
                            + "\n"
                            + "</body>\n"
                            + "\n"
                            + "</html>";
                    dao.send(email, subject, message, "thinhnbhe141465@fpt.edu.vn", "191121121994ZZZzzz");
                    HttpSession sees = request.getSession();
                    sees.setAttribute("code", code);
                    sees.setAttribute("name", name);
                    sees.setAttribute("email", email);
                    sees.setAttribute("pass", pass);
                    sees.setAttribute("user", user);
                    request.getRequestDispatcher("Verify").forward(request, response);
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
