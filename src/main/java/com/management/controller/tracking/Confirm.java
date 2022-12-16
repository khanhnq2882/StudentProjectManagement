package com.management.controller.tracking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.dao.DAOSen;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author asus
 */
@WebServlet(name = "Confirm", urlPatterns = {"/Confirm"})
public class Confirm extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String idConfirm = request.getParameter("idConfirm");
        String content = "";
        String color = "";
        String title = "";
        String btnName = "";
        String form = "";
        DAOSen dao = new DAOSen();
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder responseMessage = new StringBuilder();

        switch (idConfirm) {
            case "DeleteClassUser":
                String class_code = request.getParameter("class_code");
                String classuser_id = request.getParameter("classuser_id");
                String name = dao.Userr(classuser_id).getFullname();
                String role = dao.Userr(classuser_id).getRoll_number();
                String class_id2 = request.getParameter("class_id");
                content = "Do you want to kick " + role + " - " + name + " out of class " + class_code + "";
                color = "red";
                title = "Kick out student!";
                btnName = "Kick out";
                form = "<form method=\"POST\" id=\"deleteForm\" action=\"ClassUser4Admin?class_id=" + class_id2 + "\">\n"
                        + "              <input type=\"hidden\" name=\"idclassuser\" value=\"" + classuser_id + "\"/>\n"
                        + "              <input type=\"hidden\" name=\"role\" value=\"" + role + "\"/>\n"
                        + "              <input type=\"hidden\" name=\"name\" value=\"" + name + "\"/>\n"
                        + "              <input type=\"hidden\" name=\"class_code\" value=\"" + class_code + "\"/>\n"
                        + "              <input name=\"delete\" type=\"submit\" value=\"" + btnName + "\" class=\"suubmit btn btn-primary btn\" />\n"
                        + "           </form>\n";
                break;
            case "AddClassUser":
                class_code = request.getParameter("class_code");
                String user_id = request.getParameter("user_id");
                name = dao.Loged(user_id).getFullname();
                role = dao.Loged(user_id).getRoll_number();
                String class_id3 = request.getParameter("class_id");
                content = "Do you want to add " + role + " - " + name + " into class " + class_code + "";
                color = "blue";
                title = "Add student!";
                btnName = "Add";
                form = "<form action=\"ClassUser4Admin\" method=\"POST\">\n"
                        + "              <input type=\"hidden\" name=\"class_id\" value=\"" + class_id3 + "\"/>\n"
                        + "                <input type=\"hidden\" name=\"class_code\" value=\"" + class_code + "\"/>\n"
                        + "                <input type=\"hidden\" name=\"userid\" name=\"userid\" value=\"" + user_id + "\" />\n"
                        + "                <input type=\"hidden\" name=\"fullname\" value=\"" + name + "\" />\n"
                        + "                <input type=\"hidden\" name=\"roll_number\" value=\"" + role + "\" />\n"
                        + "                <input type=\"hidden\" name=\"RealAdd\" value=\"RealAdd\" />\n"
                        + "                <input type=\"submit\" class=\"suubmit btn btn-primary btn\" value=\"Add to Class\"/>\n"
                        + "            </form>";
                break;
            case "UpdateClassUser":
                String class_id = request.getParameter("class_id");
                String teamid = request.getParameter("teamid");
                user_id = request.getParameter("user_id");
                String lead = request.getParameter("lead");
                if (lead == null || lead.equals("")) {
                    lead = "0";
                }
                String date = request.getParameter("date");
                if (date == null || date.equals("")) {
                    date = "00/00/0000";
                }
                String note = request.getParameter("note");
                if (note == null || note.equals("")) {
                    note = "";
                }
                String ongoingeval = request.getParameter("ongoingeval");
                if (ongoingeval == null || ongoingeval.equals("")) {
                    ongoingeval = "0";
                }
                String fpresseval = request.getParameter("fpresseval");
                if (fpresseval == null || fpresseval.equals("")) {
                    fpresseval = "0";
                }
                String ftopiceval = request.getParameter("ftopiceval");
                if (ftopiceval == null || ftopiceval.equals("")) {
                    ftopiceval = "0";
                }
                content = "Do you want to Update";
                color = "#acac00";
                title = "Update student!";
                btnName = "Update";
                form = "<form action=\"StudentInClassDetail\" method=\"POST\">\n"
                        + "                                <input class=\"form-control form-control-user class_id\" type=\"hidden\" name=\"class\" value=\"" + class_id + "\" />\n"
                        + "                                <input class=\"form-control form-control-user user_id\" type=\"hidden\" name=\"user\" value=\"" + user_id + "\" />\n"
                        + "                                <input class=\"form-control form-control-user teamid\" type=\"hidden\" name=\"teamid\" value=\"" + teamid + "\" />\n"
                        + "                                <input class=\"form-control form-control-user lead\" type=\"hidden\" name=\"lead\" value=\"" + lead + "\" />\n"
                        + "                                <input class=\"form-control form-control-user date\" type=\"hidden\" name=\"dropout\" value=\"" + date + "\" />\n"
                        + "                                <input class=\"form-control form-control-user note\" type=\"hidden\" name=\"note\" value=\"" + note + "\" />\n"
                        + "                                <input class=\"form-control form-control-user ongoingeval\" type=\"hidden\" name=\"ongoingeval\" value=\"" + ongoingeval + "\" />\n"
                        + "                                <input class=\"form-control form-control-user fpresseval\" type=\"hidden\" name=\"fpresseval\" value=\"" + fpresseval + "\" />\n"
                        + "                                <input class=\"form-control form-control-user ftopiceval\" type=\"hidden\" name=\"ftopiceval\" value=\"" + ftopiceval + "\" />\n"
                        + "                            <input class=\"suubmit btn btn-primary btn\" type=\"submit\" name=\"update\" value=\"Update ClassUser\" />\n"
                        + "                        </form>";
                break;
            case "UpdateTracking":
                String status = request.getParameter("status");
                String id = request.getParameter("id");
                content = "Do you want to Update this status";
                color = "#acac00";
                title = "Update student!";
                btnName = "Update";
                form = "<form action=\"Tracking\" method=\"POST\">\n"
                        + "      <input type=\"hidden\" name=\"id\" value=\"" + id + "\" />\n"
                        + "      <input type=\"hidden\" name=\"status\" value=\"" + status + "\" />\n"
                        + "      <input type=\"submit\" class=\"suubmit btn btn-primary btn\" name=\"update\" value=\"Update\" />\n"
                        + "</form>";
                break;
            case "UpdateAllTracking":
                id = request.getParameter("id");
                String team_id = request.getParameter("team_id");
                String milestone_id = request.getParameter("milestone_id");
                String function_id = request.getParameter("function_id");
                String assigner_id = request.getParameter("assigner_id");
                String assignee_id = request.getParameter("assignee_id");
                String tracking_note = request.getParameter("tracking_note");
                String update = request.getParameter("updates");
                status = request.getParameter("statuses");

                content = "Do you want to Update this Tracking?";
                color = "#acac00";
                title = "Update Tracking!";
                btnName = "Update";
                form = "<form action=\"UpdateTracking\" method=\"POST\">\n"
                        + "                                <input type=\"text\" name=\"id\" value=\"" + id + "\" />\n"
                        + "                                <input type=\"text\" name=\"team_id\" value=\"" + team_id + "\" />\n"
                        + "                                <input type=\"text\" name=\"milestone_id\" value=\"" + milestone_id + "\" />\n"
                        + "                                <input type=\"text\" name=\"function_id\" value=\"" + function_id + "\" />\n"
                        + "                                <input type=\"text\" name=\"assigner_id\" value=\"" + assigner_id + "\" />\n"
                        + "                                <input type=\"text\" name=\"assignee_id\" value=\"" + assignee_id + "\" />\n"
                        + "                                <input type=\"text\" name=\"tracking_note\" value=\"" + tracking_note + "\" />\n"
                        + "                                <input type=\"text\" name=\"updates\" value=\"" + update + "\" />\n"
                        + "                                <input type=\"text\" name=\"status\" value=\"" + status + "\" />\n"
                        + "                            <input class=\"suubmit btn btn-primary btn\" type=\"submit\" name=\"update\" value=\"Update\" />\n"
                        + "                        </form>";
                break;
            case "AddTracking":
                team_id = request.getParameter("team_id");
                milestone_id = request.getParameter("milestone_id");
                function_id = request.getParameter("function_id");
                assigner_id = request.getParameter("assigner_id");
                assignee_id = request.getParameter("assignee_id");
                tracking_note = request.getParameter("tracking_note");
                update = request.getParameter("updates");
                status = request.getParameter("statuses");

                content = "Do you want to Add this Tracking?";
                color = "#acac00";
                title = "Add Tracking!";
                btnName = "Add";
                form = "<form action=\"AddTracking\" method=\"POST\">\n"
                        + "                                <input type=\"hidden\" name=\"team_id\" value=\"" + team_id + "\" />\n"
                        + "                                <input type=\"hidden\" name=\"milestone_id\" value=\"" + milestone_id + "\" />\n"
                        + "                                <input type=\"hidden\" name=\"function_id\" value=\"" + function_id + "\" />\n"
                        + "                                <input type=\"hidden\" name=\"assigner_id\" value=\"" + assigner_id + "\" />\n"
                        + "                                <input type=\"hidden\" name=\"assignee_id\" value=\"" + assignee_id + "\" />\n"
                        + "                                <input type=\"hidden\" name=\"tracking_note\" value=\"" + tracking_note + "\" />\n"
                        + "                                <input type=\"hidden\" name=\"updates\" value=\"" + update + "\" />\n"
                        + "                                <input type=\"hidden\" name=\"status\" value=\"" + status + "\" />\n"
                        + "                            <input class=\"suubmit btn btn-primary btn\" type=\"submit\" name=\"add\" value=\"Add\" />\n"
                        + "                        </form>";
                break;
        }

        if (color == "") {
            color = " ; color: ;";
        }
        responseMessage.append(
                "<div class=\"modal fade\" id=\"confirm1\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\"\n"
                + "                         aria-hidden=\"true\">\n"
                + "<div class=\"modal-dialog\" role=\"document\">\n"
                + "                            <div class=\"modal-content\">\n"
                + "                                <div class=\"modal-header\">\n"
                + "                                    <h5 class=\"modal-title\" id=\"exampleModalLabel\">" + title + "</h5>\n"
                + "                                    <button class=\"close\" type=\"button\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                + "                                        <span aria-hidden=\"true\">×</span>\n"
                + "                                    </button>\n"
                + "                                </div>\n"
                + "                                <div class=\"modal-body\">" + content + "</div>\n"
                + "                                <div class=\"modal-footer\">\n"
                + "                                    <button class=\"btn btn-secondary\" type=\"button\" data-dismiss=\"modal\">Cancel</button>\n");
        responseMessage.append("" + form + "");
        responseMessage.append("                                </div>\n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                        </div>"
        );
        responseMessage.append(
                "<style>\n"
                + "                            .modal-header, input.btn.btn-primary, .close {\n"
                + "                                color: white;\n"
                + "                                background: " + color + ";\n"
                + "                            }\n"
                + "                            input.btn.btn-primary:hover {\n"
                + "                                color: white;\n"
                + "                                background: repeating-linear-gradient(45deg, #ff0000e8, #0000ffde);\n"
                + "                            }\n"
                + "                        </style>");

        System.out.println(responseMessage.toString());
        out.println(mapper.writeValueAsString(responseMessage.toString()));
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
