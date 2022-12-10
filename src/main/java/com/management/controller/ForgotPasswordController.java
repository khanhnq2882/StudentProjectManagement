package com.management.controller;

import com.management.dao.DAOSen;
import com.management.entity.User;
import com.management.util.AbstractConstants;
import com.management.util.Alert;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ForgotPasswordController", urlPatterns = {"/ForgotPassword"})
public class ForgotPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("views/ForgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            DAOSen dao = new DAOSen();
            String email = request.getParameter("email");
            User a = dao.EmailExist(email);
            if (a == null) {
                request.setAttribute("alert", new Alert().alert("", "Email is not existed!", Alert.ERROR));
                request.getRequestDispatcher("views/ForgotPassword.jsp").forward(request, response);
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
                try {
                    dao.send(email, subject, message, AbstractConstants.EMAIL_USERNAME, AbstractConstants.EMAIL_PASSWORD);
                } catch (MessagingException e) {
                    e.printStackTrace();
                    request.getRequestDispatcher("views/404.html").forward(request, response);
                    return;
                }
                dao.ChangePassbyEmail(email, code);
                request.setAttribute("alert", new Alert().alert("", "A new password was send to your email!", Alert.SUCCESS));
                request.getRequestDispatcher("views/Login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.getRequestDispatcher("views/404.html").forward(request, response);
        }
    }

}

