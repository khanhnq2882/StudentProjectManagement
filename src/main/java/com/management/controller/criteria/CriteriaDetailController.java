package com.management.controller.criteria;

import com.management.dao.DAOCriteria;
import com.management.entity.Criteria;
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

@WebServlet(name = "CriteriaDetail", urlPatterns = {"/CriteriaDetail"})
public class CriteriaDetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");
            if (Loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
            }
            String service = request.getParameter("go");
            DAOCriteria dao = new DAOCriteria();
            String submit = request.getParameter("submit");


            if (service.equals("Update")) {
                int cid = Integer.parseInt(request.getParameter("cid"));
                String iterID = request.getParameter("Iter");
                //String subID = request.getParameter("Sub");
                Criteria c = dao.getCriteria(cid);
                List<Criteria> listIterId = dao.viewIterName();
                //     List<Criteria> listSubId = dao.viewSubjectCode();
                request.setAttribute("IterList", listIterId);
                //     request.setAttribute("SubList", listSubId);
                request.setAttribute("ct", c);
                request.setAttribute("iter", iterID);
                //request.setAttribute("sub", subID);
//              out.print(iterID);
                request.getRequestDispatcher("/jsp/Criteria/CriteriaDetails.jsp").forward(request, response);
            }

            if (service.equals("updateCriteria")) {
                int criteria_id = Integer.parseInt(request.getParameter("criteria_id"));
                int iteration_id = Integer.parseInt(request.getParameter("iteration"));
                Boolean evaluation = Boolean.parseBoolean(request.getParameter("evaluation"));
                //Double weight = Double.parseDouble(request.getParameter("weight"));
                String title = request.getParameter("title");
                String weight = request.getParameter("weight");
                String order = request.getParameter("order");
                String loc = request.getParameter("loc");
                int status = Integer.parseInt(request.getParameter("status"));
                String des = request.getParameter("description");
                // String iteration_name = request.getParameter("iteration_name");
                // if (loc.matches("^\\d+$") && weight.matches("^(([1-9]\\d*)|0)(\\.\\d+)?") && order.matches("^\\d+$")) {
                int loc1 = Integer.parseInt(loc);
                double weight1 = Double.parseDouble(weight);
                dao.updateCriteria(criteria_id, iteration_id, title, weight1, evaluation, order, loc1, status, des);
                request.setAttribute("title", "Update thành công");
                request.setAttribute("message", "Vua update duoc roi day!");
                request.setAttribute("theme", "Success");
                request.getRequestDispatcher("Criteria").forward(request, response);

            }

            if (service.equals("Delete")) {
                int cid = Integer.parseInt(request.getParameter("cid"));
                Criteria c = dao.getDelete(cid);
                response.sendRedirect("Criteria");
            }
            if (service.equals("add")) {
                //   List<Criteria> listSubId = dao.viewSubjectCode();

                //   request.setAttribute("SubList", listSubId);
                List<Criteria> listIterId = dao.viewIterName();
                request.setAttribute("IterList", listIterId);
//                out.print(listIterId);
                request.getRequestDispatcher("/jsp/Criteria/AddCriteria.jsp").forward(request, response);
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
                        request.setAttribute("title", "Add thành công");
                        request.setAttribute("message", "Vua add duoc roi day!");
                        request.setAttribute("theme", "Success");
                        request.getRequestDispatcher("Criteria").forward(request, response);
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
//                out.print(listIterId);
                    request.getRequestDispatcher("/jsp/Criteria/AddCriteria.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            request.getRequestDispatcher("404.html").forward(request, response);
        }
    }
}
