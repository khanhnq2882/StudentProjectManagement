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
import java.io.IOException;
import java.util.List;


@WebServlet(name = "TrackingList", urlPatterns = {"/TrackingList"})
public class TrackingList extends HttpServlet {

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
        DAOSen dao = new DAOSen();
        String update = request.getParameter("update");
        String filter = "";
        String order = request.getParameter("order");
        if (order == null) {
            order = " order by b.team_name";
        }
        String class_id = request.getParameter("class_id");
        if (class_id == null || class_id.equals("")) {
            class_id = "1";
        }
        if (class_id != null && !class_id.equals("")) {
            filter += " and g.class_id = " + class_id + "";
        }
        String team_id = request.getParameter("team_id");
        if (team_id != null && !team_id.equals("")) {
            filter += " and b.team_id = " + team_id + "";
        }
        String func_id = request.getParameter("func_id");
        if (func_id != null && !func_id.equals("")) {
            filter += " and d.function_id = " + func_id + "";
        }
        request.setAttribute("team_id", team_id);
        request.setAttribute("func_id", func_id);

        String Search = request.getParameter("Search");
        if (Search != null) {
            String SearchBy = request.getParameter("SearchBy");
            request.setAttribute("SearchBy", SearchBy);
            if (SearchBy != null) {
                String searchTxT = request.getParameter("searchTxT");
                request.setAttribute("searchTxT", searchTxT);
                filter += " and " + SearchBy + " like '%" + searchTxT + "%'";
            }
        }
        request.setAttribute("filter", order);

        String page = request.getParameter("page");
        if (page == null || page.equals("")) {
            page = "1";
        }
        int pages = Integer.parseInt(page);
        int max = 1;
        int count = dao.countTracking(filter);
        if (dao.countTracking(filter) / 10 == 0) {
            max = dao.countTracking(filter) / 10;
        } else {
            max = dao.countTracking(filter) / 10 + 1;
        }
        if (max == 0) {
            max = 1;
        }
        if (pages > max) {
            pages = max;
        }
        if (pages < 1) {
            pages = 1;
        }
        request.setAttribute("pages", pages);
        request.setAttribute("max", max);
        request.setAttribute("count", count);
        if (update != null) {
            String id = request.getParameter("id");
            String status = request.getParameter("status");
            request.setAttribute("id", id);
            request.setAttribute("status", status);
            dao.UpdateTracking(id, status);
            request.setAttribute("title", "Update ok");
            request.setAttribute("message", "Update status ok roi day");
            request.setAttribute("theme", "Success");
        }
        List<Team> team = dao.Team();
        classUser OneClass = dao.OneClass(class_id);
        List<Class_s> classes = dao.Class();
        List<Function> function = dao.Function();
        List<Tracking> list = dao.AllTracking(filter, order);
        request.setAttribute("list", list);
        request.setAttribute("classes", classes);
        request.setAttribute("OneClass", OneClass);
        request.setAttribute("team", team);
        request.setAttribute("function", function);
        request.getRequestDispatcher("/views/ViewTracking.jsp").forward(request, response);

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
