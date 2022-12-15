package com.management.controller.criteria;

import com.management.dao.DAOCriteria;
import com.management.entity.Criteria;
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

@WebServlet(name = "CriteriaDetailController", urlPatterns = {"/CriteriaDetail"})
public class CriteriaDetailController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");
            if (Loged == null) {
                request.getRequestDispatcher("views/Login.jsp").forward(request, response);
            }
            String service = request.getParameter("go");
            DAOCriteria dao = new DAOCriteria();
            String submit = request.getParameter("submit");


            if (service.equals("Update")) {
                int cid = Integer.parseInt(request.getParameter("cid"));
                String iterID = request.getParameter("Iter");
                Criteria c = dao.getCriteria(cid);
                List<Criteria> listIterId = dao.viewIterName();
                request.setAttribute("IterList", listIterId);
                request.setAttribute("ct", c);
                request.setAttribute("iter", iterID);
                request.getRequestDispatcher("/views/criteria/CriteriaDetails.jsp").forward(request, response);
                return;
            }

            if (service.equals("updateCriteria")) {
                int criteria_id = Integer.parseInt(request.getParameter("criteria_id"));
                int iteration_id = Integer.parseInt(request.getParameter("iteration"));
                Boolean evaluation = Boolean.parseBoolean(request.getParameter("evaluation"));
                String title = request.getParameter("title");
                String weight = request.getParameter("weight");
                String order = request.getParameter("order");
                String loc = request.getParameter("loc");
                int status = Integer.parseInt(request.getParameter("status"));
                String des = request.getParameter("description");
                int loc1 = Integer.parseInt(loc);
                double weight1 = Double.parseDouble(weight);
                dao.updateCriteria(criteria_id, iteration_id, title, weight1, evaluation, order, loc1, status, des);

                request.setAttribute("alert", new Alert().alert("", "Update criteria successfully!", Alert.SUCCESS));
                List<Criteria> list = dao.viewCriteriaList();
                List<Criteria> listCri = dao.viewSubjectId();
                request.setAttribute("subjectList", listCri);
                request.setAttribute("CriteriaList", list);
                request.getRequestDispatcher("/views/criteria/CriteriaList.jsp").forward(request, response);
                return;
            }

            if (service.equals("Delete")) {
                int cid = Integer.parseInt(request.getParameter("cid"));
                Criteria c = dao.getDelete(cid);
                response.sendRedirect("CriteriaList");
                return;
            }
            if (service.equals("add")) {
                List<Criteria> listIterId = dao.viewIterName();
                request.setAttribute("IterList", listIterId);
                request.getRequestDispatcher("/views/criteria/AddCriteria.jsp").forward(request, response);
                return;
            }
            if (service.equals("addCriteria")) {
                int iteration_id = Integer.parseInt(request.getParameter("iteration"));
                Boolean evaluation = Boolean.parseBoolean(request.getParameter("evaluation"));
                String weight = request.getParameter("weight");
                String order = request.getParameter("order");
                String title = request.getParameter("title");
                String loc = request.getParameter("loc");
                String des = request.getParameter("description");
                int status = Integer.parseInt(request.getParameter("status"));
                if (loc.matches("^\\d+$") && weight.matches("^(([1-9]\\d*)|0)(\\.\\d+)?") && Double.parseDouble(weight) <= 100 && order.matches("^\\d+$")) {
                    int loc1 = Integer.parseInt(loc);
                    double weight1 = Double.parseDouble(weight);
                    Criteria c = new Criteria(iteration_id, weight1, evaluation, order, loc1, status, title, des);
                    int n = dao.addCriteria(c);
                    if (n > 0) {
                        request.setAttribute("alert", new Alert().alert("", "Add criteria successfully!", Alert.SUCCESS));
                        request.getRequestDispatcher("CriteriaList").forward(request, response);
                        return;
                    }
                } else {
                    if (!loc.matches("^\\d+$")) {
                        request.setAttribute("txtTitle", title);
                        request.setAttribute("txtWeight", weight);
                        request.setAttribute("txtLoc", loc);
                        request.setAttribute("txtOrder", order);
                        request.setAttribute("errL", "Input number");
                    }

                    if (!order.matches("^\\d+$")) {
                        request.setAttribute("txtTitle", title);
                        request.setAttribute("txtWeight", weight);
                        request.setAttribute("txtLoc", loc);
                        request.setAttribute("txtOrder", order);
                        request.setAttribute("errO", "Input number");
                    }

                    if (!weight.matches("^(([1-9]\\d*)|0)(\\.\\d+)?") || Double.parseDouble(weight) > 100) {
                        request.setAttribute("txtTitle", title);
                        request.setAttribute("txtWeight", weight);
                        request.setAttribute("txtLoc", loc);
                        request.setAttribute("txtOrder", order);
                        request.setAttribute("errW", "Input number under 100%");
                    }

                    List<Criteria> listIterId = dao.viewIterName();
                    request.setAttribute("IterList", listIterId);
                    request.getRequestDispatcher("/views/criteria/AddCriteria.jsp").forward(request, response);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("views/404.html").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

