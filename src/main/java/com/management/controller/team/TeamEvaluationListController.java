package com.management.controller.team;

import com.management.dao.teamevaluation.DAOTeamEvaluation;
import com.management.entity.TeamEvaluation;
import com.management.entity.User;
import com.management.util.Alert;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TeamEvaluationListController", urlPatterns = {"/TeamEvaluationList"})
public class TeamEvaluationListController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");
            if (Loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
            }
                if (Loged.getRole_id() > 3) {
                    request.setAttribute("alert", new Alert().alert("", "Seems like you don't have permission to do this!", Alert.ERROR));
                    request.getRequestDispatcher("/views/Home.jsp").forward(request, response);
                    return;
                }

            String service = request.getParameter("go");
            DAOTeamEvaluation dao = new DAOTeamEvaluation();
            if (service == null) {
                service = "listAllTeamEval";
            }
            if (service.equals("addEval")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    List<TeamEvaluation> listTeam = dao.viewTeam();
                    
                    request.setAttribute("listTeam", listTeam);
                    request.getRequestDispatcher("/views/evaluation/addTeamEvaluation.jsp").forward(request, response);
                } else {
                    
                }
            }
            if (service.equals("listAllTeamEval")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int team = Integer.parseInt(request.getParameter("team"));
                    List<TeamEvaluation> list = dao.viewTeamEvalList(team);

                    request.setAttribute("TeamEvalList", list);
                    request.getRequestDispatcher("/views/evaluation/TeamEvaluationList.jsp").forward(request, response);
                } else {
                    int teamEvalId = Integer.parseInt(request.getParameter("teamEvalId"));
                    
                    List<TeamEvaluation> listTeam = dao.viewTeamId();
                    request.setAttribute("teamList", listTeam);
                    request.getRequestDispatcher("/views/evaluation/TeamEvaluationList.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("views/404.html").forward(request, response);
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
