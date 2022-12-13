package com.management.controller;

import com.management.entity.User;
import com.management.dao.DAOSen;
import com.management.entity.classUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ClassUser4Admin", urlPatterns = {"/ClassUser4Admin"})
public class ClassUser4Admin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");
            if (Loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
                return;
            }
            String class_id = request.getParameter("class_id");
            request.setAttribute("class_id", class_id);
            DAOSen dao = new DAOSen();
            List<User> list = dao.StudentInClass(class_id);
            int count = 0;
            for (User user : list) {
                count++;
            }
            classUser oneClass = dao.OneClass(class_id);

            String RealAdd = request.getParameter("RealAdd");
            if (RealAdd != null) {
                String roll_number = request.getParameter("roll_number");
                String userid = request.getParameter("userid");
                String fullname = request.getParameter("fullname");
                String class_code = request.getParameter("class_code");
                String class_id2 = request.getParameter("class_id");
                if (count < 30) {
                    dao.AddClassUser(class_id2, userid);
                    request.setAttribute("title", "Add thành công");
                    request.setAttribute("message", "Đã add " + roll_number + " - " + fullname + " vào lớp " + class_code + " rồi đấy!");
                    request.setAttribute("theme", "Success");
                } else {
                    request.setAttribute("title", "Add thất bại");
                    request.setAttribute("message", "Số lượng tối đa học sinh trong mỗi lớp là 30 người");
                    request.setAttribute("theme", "Warning");
                }
                list = dao.StudentInClass(class_id);
                request.setAttribute("count", count + 1);
                request.setAttribute("list", list);
                request.setAttribute("oneClass", oneClass);
                request.getRequestDispatcher("views/ViewStudenInClass.jsp").forward(request, response);
                return;
            }
            String delete = request.getParameter("delete");
            if (delete != null) {
                String idclassuser = request.getParameter("idclassuser");
                String role = request.getParameter("role");
                String name = request.getParameter("name");
                String class_code = request.getParameter("class_code");
                dao.DeleteClassUser(idclassuser);
                request.setAttribute("title", "Delete thành công");
                request.setAttribute("message", "Học sinh " + role + " - " + name + " vừa bị đá ra khỏi lớp " + class_code + " rồi đấy!");
                request.setAttribute("theme", "Success");
                list = dao.StudentInClass(class_id);
                request.setAttribute("count", count - 1);
                request.setAttribute("list", list);
                request.setAttribute("oneClass", oneClass);
                request.getRequestDispatcher("views/ViewStudenInClass.jsp").forward(request, response);
                return;
            }
            String Add = request.getParameter("Add");
            if (Add != null) {
                switch (Add) {
                    case "Add":
                        String filter = "";
                        String searchTxT = "";
                        String index = request.getParameter("page");
                        String SearchBy = request.getParameter("SearchBy");
                        String SearchMajor = request.getParameter("SearchMajor");
                        if (SearchMajor == null || SearchMajor.equals("")) {
                            SearchMajor = "";
                        } else {
                            filter += " and roll_number like '%" + SearchMajor + "%'";
                        }
                        if (SearchBy == null || SearchBy.equals("")) {
                            SearchBy = "";
                        } else {
                            searchTxT = request.getParameter("searchTxT");
                            if (searchTxT.equalsIgnoreCase("Male") && SearchBy.equals("gender")) {
                                searchTxT = "1";
                            }
                            if (searchTxT.equalsIgnoreCase("Female") && SearchBy.equals("gender")) {
                                searchTxT = "2";
                            }
                            filter += " and " + SearchBy + " like '%" + searchTxT + "%'";
                        }
                        if (index == null || index.equals("")) {
                            index = "1";
                        }
                        int page = Integer.parseInt(index);
                        int maxPage;
                        if (dao.CountHiHi(class_id, filter) % 20 == 0) {
                            maxPage = dao.CountHiHi(class_id, filter) / 20;
                        } else {
                            maxPage = dao.CountHiHi(class_id, filter) / 20 + 1;
                        }
                        List<User> lu = dao.StudentNotInClass(class_id, page, filter);

                        out.print("<div class=\"baoNgoai\" >\n"
                                + "            <div class=\"card shadow mb-4 addstudent\">\n"
                                + "                <div class=\"card-header py-3\"> "
                                + "<li>List all user not in class </li>"
                                + "<li><button class=\"btnout\" onclick=\"outAdd()\">X</button></li>"
                                + "</div>\n"
                                + "                <div class=\"card-body\">\n"
                                + "<ul class=\"spbw\">");
                        if (lu.size() == 0) {
                            page = maxPage;
                            lu = dao.StudentNotInClass(class_id, page, filter);
                            out.print("<li>Max Page là: " + page + "</li>");
                        }
                        out.print("<li>Page: " + page + "</li>\n"
                                + "<ul class=\"ulleft\">"
                                + "<li><select class=\"form-control form-control-user SearchMajor\" name=\"\">\n"
                                + "            <option value=\"\">All Nganh</option>\n");
                        if (SearchMajor.equalsIgnoreCase("SE")) {
                            out.print("            <option selected value=\"SE\">SE</option>\n");
                        }
                        if (!SearchMajor.equalsIgnoreCase("SE")) {
                            out.print("            <option value=\"SE\">SE</option>\n");
                        }
                        if (SearchMajor.equalsIgnoreCase("AI")) {
                            out.print("            <option selected value=\"AI\">AI</option>\n");
                        }
                        if (!SearchMajor.equalsIgnoreCase("AI")) {
                            out.print("            <option value=\"AI\">AI</option>\n");
                        }
                        if (SearchMajor.equalsIgnoreCase("MKT")) {
                            out.print("            <option selected value=\"MKT\">MKT</option>\n");
                        }
                        if (!SearchMajor.equalsIgnoreCase("MKT")) {
                            out.print("            <option value=\"MKT\">MKT</option>\n");
                        }
                        out.print("        </select></li>"
                                + "<li><select class=\"form-control form-control-user SearchBy\" name=\"SearchBy\" >\n"
                                + "            <option value=\"\">Search by</option>\n"
                        );
                        if (SearchBy.equalsIgnoreCase("roll_number")) {
                            out.print("<option selected value=\"roll_number\" >Roll Number</option>\n");
                        }
                        if (!SearchBy.equalsIgnoreCase("roll_number")) {
                            out.print("<option value=\"roll_number\" >Roll Number</option>\n");
                        }
                        if (SearchBy.equalsIgnoreCase("fullname")) {
                            out.print("<option selected value=\"fullname\">Name</option>\n");
                        }
                        if (!SearchBy.equalsIgnoreCase("fullname")) {
                            out.print("<option value=\"fullname\">Name</option>\n");
                        }
                        if (SearchBy.equalsIgnoreCase("gender")) {
                            out.print("<option selected value=\"gender\">Gender</option>\n");
                        }
                        if (!SearchBy.equalsIgnoreCase("gender")) {
                            out.print("<option value=\"gender\">Gender</option>\n");
                        }
                        if (SearchBy.equalsIgnoreCase("email")) {
                            out.print("<option selected value=\"email\">Email</option>\n");
                        }
                        if (!SearchBy.equalsIgnoreCase("email")) {
                            out.print("<option value=\"email\">Email</option>\n");
                        }
                        if (SearchBy.equalsIgnoreCase("mobile")) {
                            out.print("<option selected value=\"mobile\">Mobile</option>\n");
                        }
                        if (!SearchBy.equalsIgnoreCase("mobile")) {
                            out.print("<option value=\"mobile\">Mobile</option>\n");
                        }
                        out.print("        </select></li>"
                                + "<li><input class=\"form-control form-control-user searchTxT\" type=\"text\" name=\"classcode\" value=\"" + searchTxT + "\" /></li>"
                                + "<li><input class=\"form-control form-control-user\" onclick=\"filterAdd()\" type=\"submit\" name=\"search\" value=\"Search\" /></li>"
                                + "</ul>"
                                + "</ul>"
                                + "                    <div class=\"table-responsive\">\n"
                                + "                        <table class=\"table table-bordered sentable\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">\n"
                                + "                            <thead>\n"
                                + "                                <tr>\n"
                                + "                                    <th>Avatar</th>\n"
                                + "                                    <th>Student ID</th>\n"
                                + "                                    <th>Name</th>\n"
                                + "                                    <th>Gender</th>\n"
                                + "                                    <th>Date Of Birth</th>\n"
                                + "                                    <th>Email</th>\n"
                                + "                                    <th>Team</th>\n"
                                + "                                    <th>Leader</th>\n"
                                + "                                </tr>\n"
                                + "                            </thead>\n"
                                + "                            <tbody>\n");
                        for (User o : lu) {
                            out.print("                                    <tr>\n"
                                    + "                                        <td><img src=\"uploads/" + o.getAvatar_link() + "\" alt=\"avatar\" width=\"100\" height=\"100\"></td>\n"
                                    + "                                        <td>" + o.getRoll_number() + "</td>\n"
                                    + "                                        <td>" + o.getFullname() + "</td>                                            \n");
                            if (o.getGender() == 1) {
                                out.print("                                        <td>Male</td>\n");
                            }
                            if (o.getGender() == 2) {
                                out.print("                                        <td>Female</td>\n");
                            }
                            if (o.getGender() > 2 || o.getGender() < 1) {
                                out.print("                                        <td>Orher</td>\n");
                            }
                            out.print("                                        <td>" + o.getDate_of_birth() + "</td>\n"
                                    + "                                        <td>" + o.getTeamName()+ "</td>\n"
                                    + "                                        <td>" + o.getTeamLead()+ "</td>\n"
                                    + "                                        <td>\n");
                            out.print("    <input type=\"submit\" class=\"suubmit\" value=\"Add\" onclick=\"ConfirmAdd(" + o.getUser_id() + ")\"/>");
                            out.print("                                        </td>\n"
                                    + "                                    </tr>\n");
                        }
                        out.print("                            </tbody>\n"
                                + "                        </table>\n"
                                + "<div class=\"paging\">");
                        int pag = page - 1;
                        if (pag > 0) {
                            out.print("<a href=\"#\" onclick=\"IndexAdd(" + pag + ")\"><< </a>");
                        }
                        out.print("<p id=\"showpage\" onclick=\"changetype()\" >" + page + " of " + maxPage + " </p>");
                        out.print("<input id=\"page\" type=\"number\" style=\"display: none\" max=\"" + maxPage + "\" min=\"1\" value=\"" + page + "\" />"
                                + "<a href=\"#\" id=\"gobtn\" onclick=\"filterAdd()\" style=\"display: none\">Go</a>");
//                        for (int i = 1; i <= maxPage; i++) {
//                            if (i == page) {
//                                out.print("<a class=\"lenmau\" href=\"#\" onclick=\"IndexAdd(" + i + ")\" >" + i + " </a>");
//                            } else {
//                                out.print("<a href=\"#\" onclick=\"IndexAdd(" + i + ")\" >" + i + " </a>");
//                            }
//                        }
                        int pages = page + 1;
                        if (pages <= maxPage) {
                            out.print("<a href=\"#\" onclick=\"IndexAdd(" + pages + ")\" >>> </a>");
                        }
                        out.print("</div>"
                                + "                    </div>                        \n"
                                + "                </div>\n"
                                + "            </div>\n"
                                + "        </div>");
                        break;

                    case "OutAdd":
                        break;
                    case "SearchUserClass":
                        String filter2 = "";
                        String SearchBya = request.getParameter("SearchBya");
                        String searchTxTa = request.getParameter("searchTxTa");
                        String class_id3 = request.getParameter("class_id");

                        if (SearchBya == null || SearchBya.equals("") || searchTxTa == null || searchTxTa.equals("")) {
                            SearchBya = "";
                            searchTxTa = "";
                        } else {
                            filter2 += " and " + SearchBya + " like '%" + searchTxTa + "%'";
                        }
                        List<User> lu2 = dao.SearchStudentInClass(class_id3, filter2);
                        out.print("<table class=\"table table-bordered sentable\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">\n"
                                + "                                        <thead>\n"
                                + "                                            <tr>\n"
                                + "                                                <th>Avatar</th>\n"
                                + "                                                <th>Roll Number</th>\n"
                                + "                                                <th>Name</th>\n"
                                + "                                                <th>Gender</th>\n"
                                + "                                                <th>Date Of Birth</th>\n"
                                + "                                                <th>Team</th>\n"
                                + "                                                <th>Leader</th>\n");
                        if (Loged.getRole_id() == 4) {
                            out.print("                                            <th colspan=\"2\">Action</th>\n");
                        }
                        out.print("                                            </tr>\n"
                                + "                                        </thead>\n"
                                + "                                        <tfoot>\n"
                                + "                                            <tr>\n"
                                + "                                                <th>Avatar</th>\n"
                                + "                                                <th>Roll Number</th>\n"
                                + "                                                <th>Name</th>\n"
                                + "                                                <th>Gender</th>\n"
                                + "                                                <th>Date Of Birth</th>\n"
                                + "                                                <th>Team</th>\n"
                                + "                                                <th>Leader</th>\n");
                        if (Loged.getRole_id() == 4) {
                            out.print("                                                    <th colspan=\"2\">Action</th>\n");
                        }
                        out.print("                                            </tr>\n"
                                + "                                        </tfoot>\n"
                                + "                                        <tbody>\n");

                        if (lu2.size() == 0) {
                            out.print("Không có kết quả nào phù hợp");
                        }
                        for (User user : lu2) {
                            out.print("                                                    <td><img src=\"uploads/" + user.getAvatar_link() + "\" alt=\"avatar\" width=\"100\" height=\"100\"></td>\n"
                                    + "                                                    <td>" + user.getRoll_number() + "</td>\n"
                                    + "                                                    <td>" + user.getFullname() + "</td>\n");
                            if (user.getGender() == 1) {
                                out.print("                                                    <td>Male</td>\n");
                            }
                            if (user.getGender() == 2) {
                                out.print("                                                    <td>Female</td>\n");
                            }
                            if (user.getGender() != 1 && user.getGender() != 2) {
                                out.print("                                                    <td>Other</td>\n");
                            }
                            out.print("                                                    <td>" + user.getDate_of_birth() + "</td>\n"
                                    + "                                                    <td>" + user.getTeamName()+ "</td>\n"
                                    + "                                                    <td>" + user.getTeamLead()+ "</td>\n");
                            if (Loged.getRole_id() == 4) {
                                out.print("<td>\n"
                                        + "                                                            <form action=\"StudentInClassDetail\" method=\"POST\">\n"
                                        + "                                                                <input type=\"hidden\" name=\"user\" value=\"" + user.getUser_id() + "\"/>\n"
                                        + "                                                                <input type=\"hidden\" name=\"class\" value=\"" + class_id3 + "\"/>\n"
                                        + "                                                                <input type=\"submit\" class=\"suubmit\" value=\"Detail\"/>\n"
                                        + "                                                            </form>\n"
                                        + "                                                        </td><td>\n"
                                        + "                                                            <input type=\"hidden\" id=\"getclasscode\" value=\"" + class_id3 + "\"/>\n"
                                        + "                                                            <input type=\"submit\" class=\"suubmit\" value=\"Kick out\" onclick=\"Confirm(" + user.getIdclassuser() + ")\"/>\n"
                                        + "                                                        </td>\n");
                            }
                            out.print("                                                </tr>\n");
                        }
                        out.print("                                        </tbody>\n"
                                + "                                    </table>");
                        break;
                }
                return;
            }
            request.setAttribute("count", count);
            request.setAttribute("list", list);
            request.setAttribute("oneClass", oneClass);
            request.getRequestDispatcher("views/ViewStudenInClass.jsp").forward(request, response);
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
