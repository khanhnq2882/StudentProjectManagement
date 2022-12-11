package com.management.controller.setting;

import com.management.dao.DAOSetting;
import com.management.dao.DAOSettingDetail;
import com.management.entity.Setting;
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

@WebServlet(name = "SettingDetail", urlPatterns = {"/SettingDetail"})
public class SettingDetail extends HttpServlet {

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
            DAOSetting dao = new DAOSetting();
            DAOSettingDetail dao1 = new DAOSettingDetail();
            if (service == null) {
                service = "UpdateSetting";
            }
            if (service.equals("UpdateSetting")) {
                String SetID = request.getParameter("setting_id");
                String typeID = request.getParameter("Type");
                //out.print(typeID);
                Setting list = dao.SearchSetID(SetID);
                request.setAttribute("Setting", list);
                //out.print(list);
                List<Setting> listType = dao.viewType();
                request.setAttribute("listType", listType);
                // out.print(listType);
                request.setAttribute("s", list);
                request.setAttribute("type", typeID);
                request.getRequestDispatcher("/Setting/SettingDetails.jsp").forward(request, response);
            }
            if (service.equals("UpdateDetail")) {
                int id = Integer.parseInt(request.getParameter("setting_id"));
                int group = Integer.parseInt(request.getParameter("settingType"));
                String set_order = request.getParameter("order");
                String set_title = request.getParameter("lessontype");
                String set_value = request.getParameter("value");
                int status = Integer.parseInt(request.getParameter("status"));
                String note = request.getParameter("description");
                // request.setAttribute("script", note);
//                out.print(group+set_title+set_value+set_order+set_status+scrip);
                dao1.editSetting(id, group, set_title, set_value, set_order, status, note);
                response.sendRedirect("SettingListServlet");
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
