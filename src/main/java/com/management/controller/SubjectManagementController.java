package com.management.controller;

import com.management.dao.DAOChangePass;
import com.management.dao.DAOSen;
import com.management.entity.Subject;
import com.management.entity.User;
import com.management.util.Alert;
import com.management.util.EncodeSring;
import com.management.util.Extracted;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "SubjectManagementController", value = "/SubjectManagement")
public class SubjectManagementController extends HttpServlet {

    DAOSen daoSen = new DAOSen();
    DAOChangePass daoChangePass = new DAOChangePass();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Map<String, String> property)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User Loged = (User) session.getAttribute("Loged");

        if (Loged == null) {
            request.getRequestDispatcher("Login_sen").forward(request, response);
            return;
        }

        String subjectCode = request.getParameter("subjectCode");
        String authorId = request.getParameter("authorId");
        String status = request.getParameter("status");

        StringBuilder filter = new StringBuilder();

        if (property != null) {
            if (StringUtils.isEmpty(status)) {
                status = StringUtils.isNotEmpty(property.get("status")) ? property.get("status") : "";
            }
            if (StringUtils.isEmpty(authorId)) {
                authorId = StringUtils.isNotEmpty(property.get("authorId")) ? property.get("authorId") : "";
            }
            if (StringUtils.isEmpty(subjectCode)) {
                subjectCode = StringUtils.isNotEmpty(property.get("subjectCode")) ? property.get("subjectCode") : "";
            }
        }

        if (StringUtils.isNotEmpty(status)) {
            if (StringUtils.isNotEmpty(filter)) {
                filter.append(" and ");
            }
            filter.append("status = " + status);
        }
        if (StringUtils.isNotEmpty(authorId)) {
            if (StringUtils.isNotEmpty(filter)) {
                filter.append(" and ");
            }
            filter.append("author_id = " + authorId);
        }
        if (StringUtils.isNotEmpty(subjectCode)) {
            if (StringUtils.isNotEmpty(filter)) {
                filter.append(" and ");
            }
            filter.append("subject_code like '%" + subjectCode + "%' or subject_name like '%" + subjectCode + "%'");
        }

        int count = daoSen.countSubject(filter.toString());
        int size = 9;
        request = Extracted.extracted(request, count, size);

        List<Subject> list = daoSen.getFilterSubject(filter.toString(), (int) request.getAttribute("indexPage"), size);

        request.setAttribute("list", list);
        request.setAttribute("listAuthor", daoSen.AllAuthor());
        request.setAttribute("subjectCode", subjectCode);
        request.setAttribute("authorId", authorId);
        request.setAttribute("status", status);

        request.getRequestDispatcher("views/SubjectManagement.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            Map<String, String> property = new HashMap<>();

            if (StringUtils.isNotEmpty(request.getParameter("param"))) {
                String paramDecode = EncodeSring.decode(request.getParameter("param"));
                for (String str : paramDecode.split("&")) {
                    int index = str.indexOf("=");

                    property.put(str.substring(0, index), str.substring(++index));
                }
            }

            processRequest(request, response, property);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("views/404.html").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        User Loged = (User) session.getAttribute("Loged");

        if (Loged == null) {
            request.getRequestDispatcher("Login_sen").forward(request, response);
            return;
        }

        try {
            String action = request.getParameter("action");
            if (action.equals("search")) {
                doPost_search(request, response);
            } else if (action.equals("add")) {
                doPost_add(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("views/404.html").forward(request, response);
        }
    }

    private void doPost_add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("scode");
        String name = request.getParameter("scode");
        String author = request.getParameter("aname");
        if(daoSen.checkExistedSubject(code, name)) {
            request.setAttribute("alert", new Alert().alert("", "Subject was existed!", Alert.ERROR));
            processRequest(request, response, null);
        } else {
            daoSen.addSubject(code, name, author, "1");
            request.setAttribute("alert", new Alert().alert("", "Add new subject successfully!", Alert.SUCCESS));
            processRequest(request, response, null);
        }
    }

    private void doPost_search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            processRequest(request, response, null);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("views/404.html").forward(request, response);
        }
    }
}