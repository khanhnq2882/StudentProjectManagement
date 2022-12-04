package com.management.controller;

import com.management.dao.DAOSen;
import com.management.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Forgot_Password", urlPatterns = {"/Forgot_Password"})
public class ForgotPasswordController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String Reset = request.getParameter("Reset");
            if (Reset == null) {
                request.getRequestDispatcher("jsp/forgot_password.jsp").forward(request, response);
            }
            if (Reset != null) {
                DAOSen dao = new DAOSen();
                String email = request.getParameter("email");
                User a = dao.EmailExist(email);
                if (a == null) {
                    request.setAttribute("mess", "Email không tồn tại.");
                    request.getRequestDispatcher("jsp/forgot_password.jsp").forward(request, response);
                } else {
                    String subject = "This mail just for test.";
                    String code = dao.RandomBullSh();
                    String codec = dao.encrypt(code);
                    String message = "<!DOCTYPE html>\n"
                            + "<html lang=\"en\">\n"
                            + "\n"
                            + "<head>\n"
                            + "</head>\n"
                            + "\n"
                            + "<body>\n"
                            + "    <h3 style=\"color: blue;\">This mail just for test</h3>\n"
                            + "    <div>Forget password. It's not my work</div>\n"
                            + "    <div>Password changed!</div>\n"
                            + "    <div>Your new password:</div>\n"
                            + "    <h1>" + code + "</h1>\n"
                            + "\n"
                            + "</body>\n"
                            + "\n"
                            + "</html>";
                    dao.send(email, subject, message, "ducnmhe150901@fpt.edu.vn", "sechan76");
                    dao.ChangePassbyEmail(email, codec);
                    request.setAttribute("mailmess", "Password mới reset đã được gửi qua mail");
                    request.getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            request.getRequestDispatcher("404.html").forward(request, response);
        }
    }
}

