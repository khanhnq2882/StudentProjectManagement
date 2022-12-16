package com.management.controller.team;

import com.management.dao.teamevaluation.DAOTeam;
import com.management.dao.teamevaluation.DAOTeamEvaluation;
import com.management.entity.Team;
import com.management.entity.TeamEvaluation;
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
import java.util.Objects;

@WebServlet(name = "TeamEvaluationDetailController", urlPatterns = {"/TeamEvaluationDetail"})
public class TeamEvaluationDetailController extends HttpServlet {

    DAOTeamEvaluation daoTeamEvaluation = new DAOTeamEvaluation();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String teamId = request.getParameter("teamId");

        if(StringUtils.isNotEmpty(teamId)) {
            TeamEvaluation teamEvaluation = daoTeamEvaluation.getLastTeamEvaluation(teamId);
            if(Objects.nonNull(teamEvaluation)) {
                request.setAttribute("teamEvaluation", teamEvaluation);
                request.setAttribute("action", "update");
            } else {
                request.setAttribute("teamId", teamId);
                request.setAttribute("action", "add");
            }
        }
        request.getRequestDispatcher("/views/evaluation/TeamEvaluationDetail.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("views/404.html").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                if (action.equals("add")) {
                    doPost_add(request, response);
                } else if (action.equals("update")) {
                    doPost_update(request, response);
                }
            } else {
                processRequest(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("views/404.html").forward(request, response);
        }
    }

    private void doPost_update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String teamId = request.getParameter("teamId");
        String grade = request.getParameter("grade");
        String note = request.getParameter("note");
        String teamEvaluationId = request.getParameter("teamEvaluationId");

        int n = daoTeamEvaluation.editTeamEval(Integer.parseInt(teamId), grade, note, Integer.parseInt(teamEvaluationId));
        if(n == 1) {
            request.setAttribute("alert", new Alert().alert("", "Update team evaluation successfully!", Alert.SUCCESS));
        } else {
            request.setAttribute("alert", new Alert().alert("", "Update team evaluation failed!", Alert.ERROR));
        }
        doGet(request, response);
        return;
    }

    private void doPost_add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String teamId = request.getParameter("teamId");
        String grade = request.getParameter("grade");
        String note = request.getParameter("note");

        int n = daoTeamEvaluation.addTeamEval(0, 0, Integer.parseInt(teamId), grade, note);
        if(n == 1) {
            request.setAttribute("alert", new Alert().alert("", "Update team evaluation successfully!", Alert.SUCCESS));
        } else {
            request.setAttribute("alert", new Alert().alert("", "Update team evaluation failed!", Alert.ERROR));
        }
        doGet(request, response);
        return;
    }
}
