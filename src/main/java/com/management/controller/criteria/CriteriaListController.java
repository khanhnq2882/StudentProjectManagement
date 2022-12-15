/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.controller.criteria;

import com.management.dao.DAOCriteria;
import com.management.entity.Criteria;
import com.management.entity.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CriteriaListController", urlPatterns = {"/CriteriaList"})
public class CriteriaListController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        User Loged = (User) session.getAttribute("Loged");
        if (Loged == null) {
            request.getRequestDispatcher("Login_sen").forward(request, response);

        }
        String service = request.getParameter("go");
        DAOCriteria dao = new DAOCriteria();
        try {
            if (StringUtils.isNotEmpty(service) && service.equals("updateStatus")) {
                int status = Integer.parseInt(request.getParameter("status"));
                String criId = request.getParameter("criId");
                int n = dao.updateStatus(criId, status);
                response.sendRedirect("CriteriaList");
            } else {
                String submit = request.getParameter("submit");
                List<Criteria> list = dao.viewCriteriaList();
                List<Criteria> listCri = dao.viewSubjectId();
                request.setAttribute("subjectList", listCri);
                request.setAttribute("CriteriaList", list);
                request.getRequestDispatcher("/views/criteria/CriteriaList.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("views/404.html").forward(request, response);
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
