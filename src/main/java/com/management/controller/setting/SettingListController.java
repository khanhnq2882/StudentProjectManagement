package com.management.controller.setting;

import com.management.controller.labelsync.LabelSync;
import com.management.dao.DAOSen;
import com.management.dao.DAOSetting;
import com.management.entity.Setting;
import com.management.entity.User;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SettingListController", urlPatterns = {"/SettingList"})
public class SettingListController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");


        HttpSession session = request.getSession();
        User Loged = (User) session.getAttribute("Loged");
        if (Loged == null) {
            request.getRequestDispatcher("Login_sen").forward(request, response);
        }
        DAOSetting dao = new DAOSetting();

        ArrayList<String> listAllIdType = dao.viewAllType();
        ArrayList<String> listIdType = new ArrayList<>();
        String nameType = "";
        for (int i = 1; i < 6; i++) {
            nameType = getServletConfig().getInitParameter(String.valueOf(i));
            if (nameType != null) {
                listIdType.add(nameType);
            }
        }
        session.setAttribute("typeValue", listIdType);

        String service = request.getParameter("go");
        if (service == null) {
            service = "listAllSetting";
        }

        try (PrintWriter out = response.getWriter()) {

            if (service.equals("addSetting")) {

                String submit = request.getParameter("submit");
                if (submit == null) {

                    List<Setting> listType = dao.viewType();
                    request.setAttribute("listType", listType);
                    request.getRequestDispatcher("/views/addSetting.jsp").forward(request, response);
                } else {
                    int settingType = Integer.parseInt(request.getParameter("settingType"));
                    String name = request.getParameter("name");
                    String order = request.getParameter("order");
                    String value = request.getParameter("value");
                    int status = Integer.parseInt(request.getParameter("status"));
                    String note = request.getParameter("note");
                    Setting obj = new Setting(settingType, name, order, value, status, note);
                    int n = dao.addSetting(obj);
                    response.sendRedirect("SettingList");
                }
            }

            if (service.equals("listAllSetting")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    DAOSen daos = new DAOSen();
                    int index = 0;
                    List<Setting> listType = dao.viewTypeId();
                    String type = request.getParameter("type");
                    List<Setting> list = dao.viewSettingList();
                    List<Setting> viewSettingType = dao.viewSettingType(type);

                    request.setAttribute("viewSettingType", viewSettingType);
                    request.setAttribute("SettingList", list);
                    request.setAttribute("typeList", listType);
                    request.getRequestDispatcher("/views/ListSetting.jsp").forward(request, response);
                } else {

                    int typeId = Integer.parseInt(request.getParameter("typeId"));
                    int status = Integer.parseInt(request.getParameter("status"));
                    String title = request.getParameter("title");

                    List<Setting> listSearch = dao.searchSetting(status, title, status);
                    List<Setting> listType = dao.viewTypeId();
                    request.setAttribute("SettingList", listSearch);
                    request.setAttribute("typeList", listType);
                    request.getRequestDispatcher("/views/ListSetting.jsp").forward(request, response);
                }

            }
            if (service.equals("updateStatus")) {
                int status = Integer.parseInt(request.getParameter("status"));
                int settingId = Integer.parseInt(request.getParameter("settingId"));
                int n = dao.updateStatus(status, settingId);
                response.sendRedirect("SettingList");

            }
            if (service.equals("syncLabel")) {
                HttpClient client = HttpClientBuilder.create().build();
                HttpGet get = new HttpGet("https://gitlab.com/api/v4/projects/36006741/labels?title=sth");
                get.setHeader(HttpHeaders.AUTHORIZATION, "Bearer" + "glpat-7k9p3T6bzzqTHr7ZDoVG");
                try {
                    HttpResponse responses = client.execute(get);
                    System.out.println(responses.getStatusLine().getStatusCode());
                    JSONArray json = new JSONArray(IOUtils.toString(responses.getEntity().getContent()));
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonObj = json.getJSONObject(i);
                        System.out.println(jsonObj.get("title"));
                    }
                    request.setAttribute("title", "Successfully");
                    request.setAttribute("message", "Sync Successfully!");
                    request.setAttribute("theme", "Success");
                    request.getRequestDispatcher("/views/IssueListByTeam.jsp").forward(request, response);
                } catch (IOException ex) {
                    Logger.getLogger(LabelSync.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(LabelSync.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if(service.equals("DeleteSetting")){
                String settingId = request.getParameter("setting_id");
                String typeId = request.getParameter("Type");
                int n = dao.deleteSetting(settingId, typeId);
                response.sendRedirect("SettingList");
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
