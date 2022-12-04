package com.management.controller;

import com.management.dao.DAOSen;
import com.management.dao.DAOUpdate;
import com.management.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateController", value = "/UpdateProfile")
public class UpdateController extends HttpServlet {

    static DAOSen daos = new DAOSen();
    static DAOUpdate dao = new DAOUpdate();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        User Log = (User) session.getAttribute("Loged");
//        if (Log == null) {
//            request.getRequestDispatcher("Login_sen").forward(request, response);
//            return;
//        }
        request.getRequestDispatcher("jsp/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String userid = request.getParameter("userid");
        String fullname = request.getParameter("fullname");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dateofbirth");
        if (dob.equals("") || dob == null) {
            dob = "0000-00-00";
        }
        String email = request.getParameter("email");
        if (email.endsWith("@gmail.com") || email.endsWith("@fpt.edu.vn")) {
            System.out.println("ko van de");
        } else {
            request.setAttribute("updated", "Your mail is not accepted");
            RequestDispatcher di = request.getRequestDispatcher("jsp/update.jsp");
            di.forward(request, response);
            return;
        }

        String mobile = request.getParameter("mobile");
        // 10 so, so 0 dau tien
        if (mobile.equals("") || mobile == null) {
            mobile = "0";
        }
        if (mobile.length() != 10 || !mobile.startsWith("0")) {
            request.setAttribute("updated", "Your mobile phone must be about 10 digits from 0 to 9 and must be start with 0!");
            RequestDispatcher di = request.getRequestDispatcher("Views/Update.jsp");
            di.forward(request, response);
            return;
        }
        try {
            int checkM = Integer.parseInt(mobile);
        } catch (Exception e) {
            request.setAttribute("updated", "Your mobile phone must be about 10 digits from 0 to 9 and must be start with 0!");
            RequestDispatcher di = request.getRequestDispatcher("Views/Update.jsp");
            di.forward(request, response);
            return;
        }
        String fblink = request.getParameter("fblink");
        // https://facebook.com/
        if (fblink.equals("") || fblink == null) {
            fblink = "0";
        }
        if (!fblink.startsWith("https://www.facebook.com/") && !fblink.endsWith("/")) {
            request.setAttribute("updated", "It's not a facebook link");
            RequestDispatcher di = request.getRequestDispatcher("Views/Update.jsp");
            di.forward(request, response);
        }
        int n = dao.Update(userid, fullname, gender, dob, email, mobile, fblink);
        if (n > 0) {
            request.setAttribute("updated", "Your profile has been updated!");
        } else {
            request.setAttribute("updated", "Your Email is not accepted");
        }
        session.removeAttribute("Loged");
        User Loged = daos.Loged(userid);
        session.setAttribute("Loged", Loged);

        RequestDispatcher di = request.getRequestDispatcher("Views/Update.jsp");
        di.forward(request, response);
    }
}
