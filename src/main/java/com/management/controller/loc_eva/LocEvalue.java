package com.management.controller.loc_eva;

import com.management.dao.DAOChangePass;
import com.management.dao.DAOSen;
import com.management.entity.Setting;
import com.management.entity.Tracking;
import com.management.entity.User;
import com.management.entity.loc_evaluation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

@WebServlet(name = "LocEvalue", urlPatterns = {"/LocEvalue"})
public class LocEvalue extends HttpServlet {
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
        try (PrintWriter out = response.getWriter()) {

            String service = request.getParameter("go");
            HttpSession session = request.getSession();
            User loged = (User) session.getAttribute("Loged");
            DAOChangePass dao = new DAOChangePass();
            DAOSen daoS = new DAOSen();
//            if (loged == null) {
//                request.getRequestDispatcher("Login_sen").forward(request, response);
//                return;
//            }
//            try {
//                if (loged.getRole_id() != 4) {
//                    request.setAttribute("messE", "Seems like you don't have permission to do this");
//                    request.getRequestDispatcher("/jsp/Class/Error.jsp").forward(request, response);
//                    return;
//                }
//            } catch (Exception e) {
//            }
            if (service == null) {
                service = "show";
            }
            if (service.equals("show")) {
                Vector<Tracking> v = dao.AllTracking();
                request.setAttribute("vect", v);
                request.getRequestDispatcher("/views/ViewLoc.jsp").forward(request, response);
            }
            if (service.equals("evaluation")) {
                String trID = request.getParameter("trID");
                if (trID == null || trID.equals("")) {
                    trID = "1";
                }
                if (dao.allTrackingGotMark(trID)) {
                    service = "updateLoc";
                } else {
                    service = "AddLoc";
                }
                session.setAttribute("tracking", trID);
            }
            if (service.equals("AddLoc")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate = formatter.format(date);

                    Vector<Setting> v = dao.ViewComplexity();
                    Vector<Setting> v1 = dao.ViewQuality();
                    request.setAttribute("complex", v);
                    request.setAttribute("quality", v1);

                    String tid = request.getParameter("tid");
                    request.setAttribute("tracking", tid);
                    request.setAttribute("today", strDate);

                    request.getRequestDispatcher("/views/AddLoc.jsp").forward(request, response);
                }
                if (submit != null) {
                    String date = request.getParameter("dateCreate");
                    String note = request.getParameter("note");
                    String comp = request.getParameter("comp");
                    String qual = request.getParameter("qual");
                    String track = request.getParameter("tracking");

                    loc_evaluation loc = new loc_evaluation("", date, note, comp, qual, track);
                    int n = dao.insertLOC(loc);
                    if (n > 0) {
                        request.setAttribute("title", "Add thành công");
                        request.setAttribute("message", "Đã add thành công LOC mới!");
                        request.setAttribute("theme", "Success");
                        session.removeAttribute("tracking");

                        Vector<Tracking> v = dao.AllTracking();
                        request.setAttribute("vect", v);
                        request.getRequestDispatcher("/Tracking").forward(request, response);
                    } else {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String strDate = formatter.format(date);
                        Vector<Setting> v = dao.ViewComplexity();
                        Vector<Setting> v1 = dao.ViewQuality();
                        request.setAttribute("today", strDate);
                        request.setAttribute("complex", v);
                        request.setAttribute("quality", v1);

                        request.getRequestDispatcher("/jsp/LocE/AddLoc.jsp").forward(request, response);
                        return;
                    }
                }
            }
            if (service.equals("updateLoc")) {
                String eid = (String) session.getAttribute("tracking"); //sai cai nay
                String locid = dao.getLocID(eid);
                String submit = request.getParameter("submit");
                if (submit == null) {
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate = formatter.format(date);

                    loc_evaluation loc = dao.getLOCEva(eid);
                    request.setAttribute("locU", loc);
                    request.setAttribute("eid", eid);
                    request.setAttribute("upd", "1234");

                    Vector<Setting> v = dao.ViewComplexity();
                    Vector<Setting> v1 = dao.ViewQuality();
                    request.setAttribute("today", strDate);
                    request.setAttribute("complex", v);
                    request.setAttribute("quality", v1);
                    request.getRequestDispatcher("/jsp/LocE/AddLoc.jsp").forward(request, response);
                }
                if (submit != null) {
                    eid = request.getParameter("eid");
                    String trID = request.getParameter("trID");
                    String date = request.getParameter("dateCreate");
                    String note = request.getParameter("note");
                    String comp = request.getParameter("comp");
                    String qual = request.getParameter("qual");
                    String track = request.getParameter("tracking");

                    loc_evaluation loc = new loc_evaluation(locid, date, note, comp, qual, track);
                    int n = dao.updateLOC(loc);

                    if(n > 0){
                        //Vector<loc_evaluation> vect = dao.viewAllLoc(trID);
                        // request.setAttribute("vectLoc", vect);
                        request.setAttribute("title", "Add thành công");
                        request.setAttribute("message", "Đã update thành công LOC mới!");
                        session.removeAttribute("tracking");
                        request.setAttribute("theme", "Success");

                        Vector<Tracking> v = dao.AllTracking();
                        request.setAttribute("vect", v);
                        request.getRequestDispatcher("/Tracking").forward(request, response);
                    }
                    else{
                        out.print(loc.toString());
                    }
                }
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

