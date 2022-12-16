package com.management.controller.team;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.dao.DAOSen;
import com.management.dao.teamevaluation.DAOTeam;
import com.management.entity.Class_s;
import com.management.entity.Team;
import com.management.entity.User;
import com.management.util.Alert;
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

@WebServlet(urlPatterns = {"/TeamManagement"})
public class TeamManagementController extends HttpServlet {

    DAOTeam daoTeam = new DAOTeam();
    DAOSen daoSen = new DAOSen();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classId = request.getParameter("classId");

        if (StringUtils.isEmpty(classId)) {
            classId = "1";
        }
        List<Class_s> classList = daoSen.getAllClass();
        List<Team> teamList = daoTeam.viewTeamList(classId);

        request.setAttribute("classList", classList);
        request.setAttribute("classId", classId);
        request.setAttribute("teamList", teamList);
        request.getRequestDispatcher("views/team/TeamManagement.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");
            if (Loged == null) {
                request.getRequestDispatcher("views/Login.jsp").forward(request, response);
                return;
            }

            String action = request.getParameter("action");
            if (StringUtils.isNotEmpty(action)) {
                if (action.equals("addTeam")) {
                    doGet_addTeam(request, response);
                } else if (action.equals("updateTeam")) {
                    doGet_updateTeam(request, response);
                } else if (action.equals("viewTeamMember")) {
                    doGet_viewTeamMember(request, response);
                }
            } else {
                processRequest(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("views/404.html").forward(request, response);
        }
    }

    private void doGet_viewTeamMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teamId = request.getParameter("teamId");
        List<User> listMember = daoTeam.viewMemberOfTeam(teamId);
        request.setAttribute("listMember", listMember);
        request.setAttribute("teamId", teamId);
        request.getRequestDispatcher("views/team/TeamMember.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");
            if (Loged == null) {
                request.getRequestDispatcher("views/Login.jsp").forward(request, response);
                return;
            }

            String action = request.getParameter("action");
            if (StringUtils.isNotEmpty(action)) {
                if (action.equals("addTeam")) {
                    doPost_addTeam(request, response);
                } else if (action.equals("updateTeam")) {
                    doPost_updateTeam(request, response);
                } else if (action.equals("deleteTeam")) {
                    doPost_deleteTeam(request, response);
                }
            } else {
                processRequest(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("views/404.html").forward(request, response);
        }
    }

    private void doPost_deleteTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teamId = request.getParameter("teamId");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        int n = daoTeam.deleteTeam(teamId);
        if(n == 1) {
            out.println(mapper.writeValueAsString(Alert.SUCCESS));
        } else {
            out.println(mapper.writeValueAsString(Alert.ERROR));
        }
    }

    private void doPost_addTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Team team = new Team();

        team.setTeam_name(request.getParameter("teamName"));
        team.setTeamLeader(request.getParameter("teamLeader"));
        team.setClass_id(request.getParameter("classId"));
        team.setTopic_code(request.getParameter("topicCode"));
        team.setTopic_name(request.getParameter("topicName"));
        team.setGitlab_url(request.getParameter("gitlabUrl"));
        team.setStatus(Integer.parseInt(request.getParameter("status")));

        int n = daoTeam.addTeam(team);
        if(n == 1) {
            request.setAttribute("alert", new Alert().alert("", "Add team successfully!", Alert.SUCCESS));
        } else {
            request.setAttribute("alert", new Alert().alert("", "Add team failed!", Alert.ERROR));
        }
        doGet_addTeam(request, response);
        return;
    }

    private void doPost_updateTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Team team = new Team();

        team.setTeam_name(request.getParameter("teamName"));
        team.setTeamLeader(request.getParameter("teamLeader"));
        team.setClass_id(request.getParameter("classId"));
        team.setTopic_code(request.getParameter("topicCode"));
        team.setTopic_name(request.getParameter("topicName"));
        team.setGitlab_url(request.getParameter("gitlabUrl"));
        team.setStatus(Integer.parseInt(request.getParameter("status")));
        team.setTeam_id(Integer.parseInt(request.getParameter("teamId")));

        int n = daoTeam.updateTeam(team);
        if(n == 1) {
            request.setAttribute("alert", new Alert().alert("", "Update team successfully!", Alert.SUCCESS));
        } else {
            request.setAttribute("alert", new Alert().alert("", "Update team failed!", Alert.ERROR));
        }
        doGet_updateTeam(request, response);
    }

    private void doGet_addTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Class_s> classList = daoSen.getAllClass();
        List<User> studentList = daoSen.getAllUserByRoleId("1");

        request.setAttribute("classList", classList);
        request.setAttribute("studentList", studentList);
        request.getRequestDispatcher("/views/team/AddTeam.jsp").forward(request, response);
    }

    private void doGet_updateTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teamId = request.getParameter("teamId");
        Team team = daoTeam.getTeamById(teamId);
        List<Class_s> classList = daoSen.getAllClass();
        List<User> studentList = daoSen.getAllUserByRoleId("1");

        request.setAttribute("team", team);
        request.setAttribute("classList", classList);
        request.setAttribute("studentList", studentList);
        request.getRequestDispatcher("/views/team/UpdateTeam.jsp").forward(request, response);
    }
}