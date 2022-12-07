package com.management.controller;

import com.management.dao.DAOChangePass;
import com.management.dao.DAOSen;
import com.management.entity.Subject;
import com.management.entity.User;
import com.management.util.Extracted;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SubjectListController", value = "/SubjectList")
public class SubjectListController extends HttpServlet {

    DAOSen daoSen = new DAOSen();
    DAOChangePass daoChangePass = new DAOChangePass();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOSen dao = new DAOSen();
            DAOChangePass daoc = new DAOChangePass();
            List<User> listU = dao.AllAuthor();
            String add = request.getParameter("add");
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");

            if (Loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
                return;
            }
            if (add != null) {
                String status = "1";
                String code = daoc.chuannHoa(request.getParameter("code"));
                String name = daoc.chuannHoa(request.getParameter("name"));
                String author = daoc.chuannHoa(request.getParameter("author"));
                List<Subject> listl = dao.AllSubjecta();
                for (Subject o : listl) {
                    if (o.getSubject_code().equals(code) || o.getSubject_name().equals(name)) {
                        request.setAttribute("title", "Add thất bại");
                        request.setAttribute("message", "Môn học đã tồn tại!");
                        request.setAttribute("theme", "Danger");
                        request.setAttribute("active", "active");
                        request.setAttribute("code", code);
                        request.setAttribute("name", name);
                        request.setAttribute("author", author);
                        List<Subject> listl2 = dao.AllSubjecta2();
                        List<Subject> listl3 = dao.AllSubjecta();
                        int n = 0;
                        int nn = 0;
                        for (Subject subject : listl2) {
                            n++;
                        }
                        for (Subject subject : listl3) {
                            nn++;
                        }
                        request.setAttribute("count", n);
                        request.setAttribute("count1", nn);
                        List<Subject> list = dao.AllSubject(0, "subject_code");
                        request.setAttribute("list", list);
                        List<Subject> list2 = dao.AllSubject2(0, "subject_code");
                        request.setAttribute("list2", list2);
                        request.setAttribute("listU", listU);
                        request.getRequestDispatcher("views/SubjectList.jsp").forward(request, response);

                        return;
                    }
                }
                if (code.length() > 15 || name.length() > 100) {
                    request.setAttribute("title", "Add thất bại");
                    request.setAttribute("message", "Subject code hoặc Subject name quá dài!");
                    request.setAttribute("theme", "Danger");
                    request.setAttribute("active", "active");
                    request.setAttribute("code", code);
                    request.setAttribute("name", name);
                    request.setAttribute("author", author);
                }
                if (code.equals("") || name.equals("") || author.equals("name")) {
                    request.setAttribute("title", "Add thất bại");
                    request.setAttribute("message", "Hãy nhập đầy đủ thông tin!");
                    request.setAttribute("theme", "Danger");  // Danger == mau do
                    request.setAttribute("active", "active");
                    request.setAttribute("code", code);
                    request.setAttribute("name", name);
                    request.setAttribute("author", author);
                } else {
                    request.setAttribute("title", "Thêm thành công");
                    request.setAttribute("message", "Add successfully!");
                    request.setAttribute("theme", "Success");   // Success == mau xanh
                    dao.addSubject(code, name, author, status);
                }
            }
            List<Subject> listl2 = dao.AllSubjecta2();
            List<Subject> listl3 = dao.AllSubjecta();
            int n = 0;
            int nn = 0;
            for (Subject subject : listl2) {
                n++;
            }
            for (Subject subject : listl3) {
                nn++;
            }

            request.setAttribute("count", n);
            request.setAttribute("count1", nn);
            List<Subject> list = dao.AllSubject(0, "subject_code");
            request.setAttribute("list", list);
            List<Subject> list2 = dao.AllSubject2(0, "subject_code");
            request.setAttribute("list2", list2);
            request.setAttribute("listU", listU);
            request.getRequestDispatcher("views/SubjectList.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            System.out.println("checkpoint");
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");

            if (Loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
                return;
            }

            String status = "";

            if(status.equals("")) {
                if(Loged.getRole_id() != 4) {
                    status = "1";
                }
            }

            StringBuilder filter = new StringBuilder();
            if (!status.equals("")) {
                filter.append("status = " + status);
            }

            int count = daoSen.countSubject(status);
            int size = 9;
            request = Extracted.extracted(request, count, size);

            List<Subject> list = daoSen.getFilterSubject(filter.toString(), (int) request.getAttribute("index"), size);

            request.setAttribute("list", list);
            request.setAttribute("listAuthor", daoSen.AllAuthor());
            request.getRequestDispatcher("views/SubjectList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("views/404.html").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if(action.equals("search")) {
            doPost_search(request,response);
        } else if (action.equals("add")) {
            doPost_add(request,response);
        }
    }

    private void doPost_add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String status = "1";
//        String code = daoc.chuannHoa(request.getParameter("code"));
//        String name = daoc.chuannHoa(request.getParameter("name"));
//        String author = daoc.chuannHoa(request.getParameter("author"));
//        List<Subject> listl = dao.AllSubjecta();
//        for (Subject o : listl) {
//            if (o.getSubject_code().equals(code) || o.getSubject_name().equals(name)) {
//                request.setAttribute("title", "Add thất bại");
//                request.setAttribute("message", "Môn học đã tồn tại!");
//                request.setAttribute("theme", "Danger");
//                request.setAttribute("active", "active");
//                request.setAttribute("code", code);
//                request.setAttribute("name", name);
//                request.setAttribute("author", author);
//                List<Subject> listl2 = dao.AllSubjecta2();
//                List<Subject> listl3 = dao.AllSubjecta();
//                int n = 0;
//                int nn = 0;
//                for (Subject subject : listl2) {
//                    n++;
//                }
//                for (Subject subject : listl3) {
//                    nn++;
//                }
//                request.setAttribute("count", n);
//                request.setAttribute("count1", nn);
//                List<Subject> list = dao.AllSubject(0, "subject_code");
//                request.setAttribute("list", list);
//                List<Subject> list2 = dao.AllSubject2(0, "subject_code");
//                request.setAttribute("list2", list2);
//                request.setAttribute("listU", listU);
//                request.getRequestDispatcher("views/SubjectList.jsp").forward(request, response);
//
//                return;
//            }
//        }
//        if (code.length() > 15 || name.length() > 100) {
//            request.setAttribute("title", "Add thất bại");
//            request.setAttribute("message", "Subject code hoặc Subject name quá dài!");
//            request.setAttribute("theme", "Danger");
//            request.setAttribute("active", "active");
//            request.setAttribute("code", code);
//            request.setAttribute("name", name);
//            request.setAttribute("author", author);
//        }
//        if (code.equals("") || name.equals("") || author.equals("name")) {
//            request.setAttribute("title", "Add thất bại");
//            request.setAttribute("message", "Hãy nhập đầy đủ thông tin!");
//            request.setAttribute("theme", "Danger");  // Danger == mau do
//            request.setAttribute("active", "active");
//            request.setAttribute("code", code);
//            request.setAttribute("name", name);
//            request.setAttribute("author", author);
//        } else {
//            request.setAttribute("title", "Thêm thành công");
//            request.setAttribute("message", "Add successfully!");
//            request.setAttribute("theme", "Success");   // Success == mau xanh
//            dao.addSubject(code, name, author, status);
//        }
    }

    private void doPost_search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");

            if (Loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
                return;
            }

            String subjectCode = request.getParameter("subjectCode");
            String authorId = request.getParameter("authorId");
            String status = request.getParameter("status");

            int count = daoSen.countSubject(status);
            int size = 9;
            request = Extracted.extracted(request, count, size);

            StringBuilder filter = new StringBuilder();

            if (!status.equals("")) {
                if(!filter.toString().equals("")) {
                    filter.append(" and ");
                }
                filter.append("status = " + status);
            }
            if (!authorId.equals("")) {
                if(!filter.toString().equals("")) {
                    filter.append(" and ");
                }
                filter.append("author_id like '%" + authorId + "%'");
            }
            if (!subjectCode.equals("")) {
                if(!filter.toString().equals("")) {
                    filter.append(" and ");
                }
                filter.append("subject_code like '%" + subjectCode + "%'");
            }

            List<Subject> list = daoSen.getFilterSubject(filter.toString(), (int) request.getAttribute("index"), (int) request.getAttribute("size"));

            request.setAttribute("list", list);
            request.setAttribute("listAuthor", daoSen.AllAuthor());
            request.setAttribute("subjectCode", subjectCode);
            request.setAttribute("authorId", authorId);
            request.setAttribute("status", status);
            request.getRequestDispatcher("views/SubjectList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("views/404.html").forward(request, response);
        }
    }
}
