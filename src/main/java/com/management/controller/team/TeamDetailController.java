package com.management.controller.team;

import com.management.dao.teamevaluation.DAOTeam;
import com.management.dao.teamevaluation.DAOTeamDetail;
import com.management.entity.Team;
import com.management.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "TeamDetailController", urlPatterns = {"/TeamDetail"})
public class TeamDetailController extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");
            if (Loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
            }
            String service = request.getParameter("go");
            DAOTeam dao = new DAOTeam();
            DAOTeamDetail dao1 = new DAOTeamDetail();
            if (service == null) {
                service = "UpdateTeam";
            }
            if (service.equals("UpdateTeam")) {
                String TeamID = request.getParameter("team_id");
                String classID = request.getParameter("Class");
                Team list = dao.SearchSetID(TeamID);
                request.setAttribute("Team", list);
                List<Team> listClass = dao.viewClassId();

                request.setAttribute("classList", listClass);
                request.setAttribute("class", classID);
                request.getRequestDispatcher("views/TeamDetail.jsp").forward(request, response);

            }
            if (service.equals("TeamDetail")) {
                String classID = request.getParameter("classID");
                String topicCode = request.getParameter("topicCode");
                String topicName = request.getParameter("topicName");
                String gitlabURL = request.getParameter("gitlabURL");
                int status = Integer.parseInt(request.getParameter("status"));
                dao1.editTeam(status, classID, topicCode, topicName, gitlabURL, status);
                response.sendRedirect("TeamListController");
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
