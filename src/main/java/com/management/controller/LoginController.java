package com.management.controller;

import com.management.dao.DAOSen;
import com.management.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginController", value = "/Login_sen")
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            session.removeAttribute("user");
            session.removeAttribute("pass");
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
                request.getRequestDispatcher("views/Login.jsp").forward(request, response);
            }
            if (Login != null) {
                String user = request.getParameter("user");
                String pass = request.getParameter("pass");
                String passc = dao.encrypt(pass);
                String remember = request.getParameter("remember");
                User Loged = dao.Login(user, pass);
                User Loged1 = dao.Login1(user, pass);
                if (Loged == null && Loged1 == null) {
                    session.setAttribute("user", user);
                    session.setAttribute("pass", pass);
                    request.setAttribute("mess", "Sai tài khoản hoặc mật khẩu rồi bạn!");
                    request.getRequestDispatcher("views/Login.jsp").forward(request, response);
                    return;
                } else {
                    if (Loged != null) {
                        if (Loged.getStatus() == 2) {
                            session.setAttribute("user", user);
                            session.setAttribute("pass", pass);
                            request.setAttribute("mess", "Tài khoản đã bị khóa!");
                            request.getRequestDispatcher("views/Login.jsp").forward(request, response);
                            return;
                        }
                        session.setAttribute("Loged", Loged);
                    }
                    if (Loged1 != null) {
                        if (Loged1.getStatus() == 2) {
                            session.setAttribute("user", user);
                            session.setAttribute("pass", pass);
                            request.setAttribute("mess", "Tài khoản đã bị khóa!");
                            request.getRequestDispatcher("views/Login.jsp").forward(request, response);
                            return;
                        }
                        session.setAttribute("Loged", Loged1);
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
            request.getRequestDispatcher("views/404.html").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}