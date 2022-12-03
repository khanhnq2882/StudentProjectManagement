package com.management.controller;

import com.management.dao.DAOChangePass;
import com.management.dao.DAOSen;
import com.management.entity.ClassUser;
import com.management.entity.Class_s;
import com.management.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

@WebServlet(name = "HomeController", urlPatterns = {"/Home"})
public class HomeController extends HttpServlet {

    static DAOSen daosen = new DAOSen();
    static DAOChangePass daoChangePass = new DAOChangePass();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");

            if (Loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
                return;
            }
            List<ClassUser> listCU = daosen.AllClassUser(Loged.getUser_id());
            session.setAttribute("listCU", listCU);
            
            Vector<Class_s> vect = daoChangePass.viewClassByStudent(Loged.getUser_id() + "");
            request.setAttribute("vectC", vect);
            
            request.getRequestDispatcher("jsp/Home.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("404.html").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
