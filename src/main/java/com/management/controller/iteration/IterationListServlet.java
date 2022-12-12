package com.management.controller.iteration;

import com.management.connectdb.ConnectJDBC;
import com.management.dao.DAOIteration;
import com.management.entity.Iteration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "IterationListServlet", urlPatterns = {"/IterationListServlet"})
public class IterationListServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ConnectJDBC con = new ConnectJDBC();
        DAOIteration dao = new DAOIteration();
        String service = request.getParameter("go");
        if (service == null) {
            service = "listAllIteration";
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if (service.equals("addIteration")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    List<Iteration> listSubCode = dao.viewSubCode();

                    request.setAttribute("listSubCode", listSubCode);
                    request.getRequestDispatcher("/views/AddIteration.jsp").forward(request, response);
                } else {
                    int subId = Integer.parseInt(request.getParameter("subjectId"));
                    String iteName = request.getParameter("name");
                    String duration = request.getParameter("duration");
                    int status = Integer.parseInt(request.getParameter("status"));
                    String note = request.getParameter("note");
                    boolean checkName = dao.checkExistIte(iteName);
                    if (checkName) {
                        List<Iteration> listSubCode = dao.viewSubCode();

                        request.setAttribute("listSubCode", listSubCode);
                        request.setAttribute("thongbao", "Iteration name already exists");
                        request.getRequestDispatcher("/views/AddIteration.jsp").forward(request, response);
                    } else {
                        Iteration obj = new Iteration(subId, iteName, duration, status,note);
                        int n = dao.addIteration(obj);
                        response.sendRedirect("IterationListServlet");
                    }

                }
            }
            if (service.equals("listAllIteration")) {
                String submit = request.getParameter("submit");
                String page = request.getParameter("page");
                int countPage;
                if (dao.countIte() % 10 == 0) {
                    countPage = dao.countIte() / 10;
                } else {
                    countPage = dao.countIte() / 10 + 1;
                }

                String startFrom = "";
                request.setAttribute("countPage", countPage);

                if (page == null || page.equals("")) {
                    page = "1";
                }
                try {
                    int a = Integer.parseInt(page);
                    startFrom = (a - 1) * 10 + "";
                } catch (Exception e) {
                }
                request.setAttribute("page", page);
                if (startFrom == null || startFrom.equals("")) {
                    startFrom = "0";
                }
                if (submit == null) {
                    List<Iteration> list = dao.viewIteration(startFrom);
                    List<Iteration> listSubId = dao.searchByID();

                    request.setAttribute("listSubId", listSubId);
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("/views/ListIteration.jsp").forward(request, response);
                } else {
                    String name = request.getParameter("IteName");
                    String subId = request.getParameter("subjectId");

                    if (subId.equals("all") && name.equals("")) {
                        List<Iteration> list = dao.viewIteration(startFrom);
                        List<Iteration> listSubId = dao.searchByID();

                        request.setAttribute("list", list);
                        request.setAttribute("listSubId", listSubId);
                        request.getRequestDispatcher("/views/ListIteration.jsp").forward(request, response);
                    }
                    if (!name.equals("") && !subId.equals("all")) {
                        List<Iteration> searchByName = dao.searchByIteName(name);
                        List<Iteration> listSearch = dao.searchIteration(subId, name);
                        List<Iteration> listSubId = dao.searchByID();

                        request.setAttribute("listSubId", listSubId);
                        request.setAttribute("nono", "hihi");
                        request.setAttribute("list", searchByName);
                        request.setAttribute("list", listSearch);
                        request.getRequestDispatcher("/views/ListIteration.jsp").forward(request, response);
                    }

                    if (!name.equals("") && subId.equals("all")) {
                        List<Iteration> searchByName = dao.searchByIteName(name);

                        List<Iteration> listSubId = dao.searchByID();

                        request.setAttribute("listSubId", listSubId);

                        request.setAttribute("list", searchByName);
                        request.getRequestDispatcher("/views/ListIteration.jsp").forward(request, response);
                    }

                    List<Iteration> listSubId = dao.searchByID();

                    List<Iteration> listIteName = dao.searchByIteName(name);
                    List<Iteration> listSearch = dao.searchIteration(subId, name);
                    request.setAttribute("subId", subId);
                    request.setAttribute("list", listSearch);
                    request.setAttribute("listSubId", listSubId);
                    request.setAttribute("listIteName", listIteName);

                    request.setAttribute("page", page);
                    request.getRequestDispatcher("/views/ListIteration.jsp").forward(request, response);

                }

            }

            if (service.equals("updateIteration")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String iteId1 = request.getParameter("iteId");
                    String subId1 = request.getParameter("subjectId");
                    Iteration ite = dao.updateByIteIdAndSubId(iteId1, subId1);
                    
                    request.setAttribute("iteUpdate", ite);
                    request.getRequestDispatcher("/views/UpdateIteration.jsp").forward(request, response);
                } else {
                    int iteId = Integer.parseInt(request.getParameter("iteId"));
                    int subId = Integer.parseInt(request.getParameter("subjectId"));
                    String iteName = request.getParameter("name");
                    String duration = request.getParameter("duration");
                    int status = Integer.parseInt(request.getParameter("status"));
                    String note = request.getParameter("note");

                    boolean checkName = dao.checkExistIte(iteName);
                    if (checkName) {
                        String iteId1 = request.getParameter("iteId");
                        String subId1 = request.getParameter("subjectId");
                        Iteration ite = dao.updateByIteIdAndSubId(iteId1, subId1);

                        request.setAttribute("thongbao", "Iteration name already exists");
                        request.setAttribute("iteUpdate", ite);
                        request.getRequestDispatcher("/views/UpdateIteration.jsp").forward(request, response);
                    } else {
                        Iteration obj = new Iteration(iteId, subId, iteName, duration, status, note);
                        int n = dao.updateIteration(obj);

                        response.sendRedirect("IterationListServlet");
                    }

                }

            }
            if (service.equals("deleteIteration")) {
                String iteId = request.getParameter("iteId");
                String subId = request.getParameter("subjectId");
                int n = dao.deleteIteration(iteId, subId);
                response.sendRedirect("IterationListServlet");
            }

            if (service.equals("updateStatus")) {
                String iteId = request.getParameter("iteId");
                String status = request.getParameter("status");
                int n = dao.updateStatus(iteId, status);
                response.sendRedirect("IterationListServlet");
            }

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
