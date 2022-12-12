package com.management.controller.team;

import com.management.dao.DAOSen;
import com.management.dao.teamevaluation.DAOTeam;
import com.management.entity.Class_s;
import com.management.entity.Team;
import com.management.entity.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/TeamManagement"})
public class TeamManagementController extends HttpServlet {

    DAOTeam daoTeam = new DAOTeam();
    DAOSen daoSen = new DAOSen();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String classId = request.getParameter("classId");

            if (StringUtils.isEmpty(classId)) {
                classId = "1";
            }
            List<Class_s> classList = daoSen.getAllClass();
            List<Team> teamList = daoTeam.viewTeamList(classId);

            request.setAttribute("classList", classList);
            request.setAttribute("classId", classId);
            request.setAttribute("teamList", teamList);
            request.getRequestDispatcher("views/TeamManagement.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("views/404.html").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User Loged = (User) session.getAttribute("Loged");
        if (Loged == null) {
            request.getRequestDispatcher("views/Login.jsp").forward(request, response);
            return;
        }

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
