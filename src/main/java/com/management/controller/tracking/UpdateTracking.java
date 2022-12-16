/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.management.controller.tracking;
import com.management.dao.DAOSen;
import com.management.entity.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UpdateTracking", urlPatterns = {"/UpdateTracking"})
public class UpdateTracking extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String idConfirm = request.getParameter("idConfirm");
        HttpSession session = request.getSession();
        User Loged = (User) session.getAttribute("Loged");
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        DAOSen dao = new DAOSen();
        String update = request.getParameter("update");
        if (update != null) {
            String team_id = request.getParameter("team_id");
            String milestone_id = request.getParameter("milestone_id");
            String function_id = request.getParameter("function_id");
            String assigner_id = request.getParameter("assigner_id");
            String assignee_id = request.getParameter("assignee_id");
            String tracking_note = request.getParameter("tracking_note");
            String updates = request.getParameter("updates");
            String status = request.getParameter("status");
            request.setAttribute("team_id", team_id);
            request.setAttribute("milestone_id", milestone_id);
            request.setAttribute("function_id", function_id);
            request.setAttribute("assigner_id", assigner_id);
            request.setAttribute("assignee_id", assignee_id);
            request.setAttribute("updates", updates);
            request.setAttribute("tracking_note", tracking_note);
            request.setAttribute("status", status);
            request.setAttribute("title", "Update ok");
            request.setAttribute("message", "Update status ok roi day");
            request.setAttribute("theme", "Success");
            dao.UpdateTracking(id, team_id, milestone_id, function_id, assigner_id, assignee_id, tracking_note, updates, status);
        }
        String ClassId = request.getParameter("ClassId");
        Tracking a = dao.OneTracking(id);
        request.setAttribute("a", a);
        List<Team> lteam = dao.Team();
        request.setAttribute("lteam", lteam);
        List<Milestone> lMilestone = dao.Milestone2(id);
        request.setAttribute("lMilestone", lMilestone);
        List<Function> lFunction = dao.Function();
        request.setAttribute("lFunction", lFunction);
        List<User> lStudent = dao.Student(id);
        request.setAttribute("lStudent", lStudent);
        List<User> lNotStudent = dao.NotStudent(ClassId);
        request.setAttribute("lNotStudent", lNotStudent);

        if (idConfirm != null) {
            id = request.getParameter("id");
            lStudent = dao.Student(id);
            out.print("<select class=\"form-control form-control-user assignee_id\" name=\"assignee_id\">\n");
            for (User user : lStudent) {
                out.print(" <option value=\"" + user.getUser_id() + "\"");
                if (user.getUser_id() == Loged.getUser_id()) {
                    out.print("selected");
                }
                out.print(" >" + user.getFullname() + "</option>\n");
            }
            out.print("</select>");
            return;
        }
        request.getRequestDispatcher("/views/UpdateTracking.jsp").forward(request, response);
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
