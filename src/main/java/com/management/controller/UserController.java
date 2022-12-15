package com.management.controller;

import com.management.dao.DAOSen;
import com.management.dao.UserDAO;
import com.management.entity.User;
import com.management.util.AbstractConstants;
import com.management.util.Alert;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Vector;


@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    UserDAO dao = new UserDAO();
    DAOSen daoSen = new DAOSen();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String service = request.getParameter("go");
        HttpSession session = request.getSession();
        User loged = (User) session.getAttribute("Loged");
        if (loged == null) {
            request.getRequestDispatcher("Login_sen").forward(request, response);
            return;
        }
        if (service == null) {
            service = "listAllUser";
        }
        try (PrintWriter out = response.getWriter()) {
            if (service.equals("listAllUser")) {
                String submit = request.getParameter("submitSearch");
                String data = request.getParameter("nameAndRoll");
                int myID = loged.getUser_id();
                int numberPage;
                int page;
                int startFrom = 0;
                try {
                    page = Integer.parseInt(request.getParameter("page"));
                } catch (Exception e) {
                    page = 1;
                }
                if (page == 0) {
                    page = 1;
                    startFrom = 0;
                }
                if (page == 1) {
                    startFrom = 0;
                }
                if (page != 1) {
                    startFrom = (page - 1) * 10;
                }
                if (dao.countUser() % 10 == 0) {
                    numberPage = dao.countUser(data) / 10;
                } else {
                    numberPage = dao.countUser(data) / 10 + 1;
                }
                if (page > numberPage) {
                    page = 1;
                    startFrom = 0;
                }

                if (data == null) {
                    data = "";
                }
                if (submit == null) {
                    Vector<User> v = dao.listAllUser(startFrom, data, myID);
                    request.setAttribute("countUser", dao.countUser());
                    request.setAttribute("listAll", v);
                    request.setAttribute("search", data);
                    request.setAttribute("page", page);
                    request.setAttribute("numberPage", numberPage);
                    RequestDispatcher di = request.getRequestDispatcher("/views/ListAllUser.jsp");
                    di.forward(request, response);
                } else {
                    if (dao.countUser(data) % 10 == 0) {
                        numberPage = dao.countUser(data) / 10;
                    } else {
                        numberPage = dao.countUser(data) / 10 + 1;
                    }
                    if (page > numberPage) {
                        page = 1;
                        startFrom = 0;
                    }
                    Vector<User> v1 = dao.listAllUser(startFrom, data, myID);
                    request.setAttribute("countUser", dao.countUser(data));
                    request.setAttribute("listAll", v1);
                    request.setAttribute("search", data);
                    request.setAttribute("page", page);
                    request.setAttribute("numberPage", numberPage);
                    RequestDispatcher di = request.getRequestDispatcher("/views/ListAllUser.jsp");
                    di.forward(request, response);
                }
            }
            if (service.equals("listUserByClass")) {
                String id = request.getParameter("ClassId");
                Vector<User> ve = dao.listUserByClass(id);
                request.setAttribute("listByClass", ve);
                request.setAttribute("classid", id);
                RequestDispatcher di = request.getRequestDispatcher("/views/ListUserByClass.jsp");
                di.forward(request, response);
            }

            if (service.equals("searchRole")) {
                String roleID = request.getParameter("ROLEid");
                int myID = loged.getUser_id();
                int numberPage;
                int page;
                int startFrom = 0;

                try {
                    page = Integer.parseInt(request.getParameter("page"));
                } catch (Exception e) {
                    page = 1;
                }
                if (page == 0) {
                    page = 1;
                    startFrom = 0;
                }
                if (page == 1) {
                    startFrom = 0;
                }
                if (page != 1) {
                    startFrom = (page - 1) * 10;
                }
                Vector<User> listRole = new Vector<>();
                if (roleID.equals("5")) {
                    listRole = dao.listAllUser(startFrom, "", myID);
                } else {
                    listRole = dao.searchByRole(roleID, startFrom, myID);
                }
                if (dao.countUser() % 10 == 0) {
                    numberPage = dao.coutUs(listRole) / 10;
                } else {
                    numberPage = dao.coutUs(listRole) / 10 + 1;
                }
                if (page > numberPage) {
                    page = 1;
                    startFrom = 0;
                }
                request.setAttribute("listAll", listRole);
                request.setAttribute("countUser", dao.coutUs(listRole));
                request.setAttribute("role", roleID);
                request.setAttribute("page", page);
                request.setAttribute("numberPage", numberPage);
                RequestDispatcher di = request.getRequestDispatcher("/views/ListAllUser.jsp");
                di.forward(request, response);
            }

            if (service.equals("searchByStatus")) {
                int sta = Integer.parseInt(request.getParameter("statu"));
                int myID = loged.getUser_id();
                int numberPage;
                int page;
                int startFrom = 0;
                Vector<User> listSta = new Vector<>();
                try {
                    page = Integer.parseInt(request.getParameter("page"));
                } catch (Exception e) {
                    page = 1;
                }
                if (page == 0) {
                    page = 1;
                    startFrom = 0;
                }
                if (page == 1) {
                    startFrom = 0;
                }
                if (page != 1) {
                    startFrom = (page - 1) * 10;
                }
                if (sta == 2){
                    listSta = dao.listAllUser(startFrom, "", myID);
                } else {
                    listSta = dao.searchByStatus(sta, startFrom, myID);
                }
                if (dao.countUser() % 10 == 0) {
                    numberPage = dao.coutUs(listSta) / 10;
                } else {
                    numberPage = dao.coutUs(listSta) / 10 + 1;
                }
                if (page > numberPage) {
                    page = 1;
                    startFrom = 0;
                }
                request.setAttribute("listAll", listSta);
                request.setAttribute("countUser", dao.coutUs(listSta));
                request.setAttribute("status", sta);
                request.setAttribute("page", page);
                request.setAttribute("numberPage", numberPage);
                RequestDispatcher di = request.getRequestDispatcher("/views/ListAllUser.jsp");
                di.forward(request, response);
            }

            if (service.equals("sortFullname")){
                int sta = Integer.parseInt(request.getParameter("sort"));
                int myID = loged.getUser_id();
                int numberPage;
                int page;
                int startFrom = 0;
                Vector<User> listSta = new Vector<>();
                try {
                    page = Integer.parseInt(request.getParameter("page"));
                } catch (Exception e) {
                    page = 1;
                }
                if (page == 0) {
                    page = 1;
                    startFrom = 0;
                }
                if (page == 1) {
                    startFrom = 0;
                }
                if (page != 1) {
                    startFrom = (page - 1) * 10;
                }
                if (sta == 1){
                    listSta = dao.sortAsc(startFrom, myID);
                } else {
                    listSta = dao.sortDesc(startFrom, myID);
                }
                if (dao.countUser() % 10 == 0) {
                    numberPage = dao.coutUs(listSta) / 10;
                } else {
                    numberPage = dao.coutUs(listSta) / 10 + 1;
                }
                if (page > numberPage) {
                    page = 1;
                    startFrom = 0;
                }
                request.setAttribute("listAll", listSta);
                request.setAttribute("countUser", dao.coutUs(listSta));
                request.setAttribute("sort", sta);
                request.setAttribute("page", page);
                request.setAttribute("numberPage", numberPage);
                RequestDispatcher di = request.getRequestDispatcher("/views/ListAllUser.jsp");
                di.forward(request, response);
            }

            if (service.equals("changeRole")) {
                int id = Integer.parseInt(request.getParameter("userID"));
                int ro_id = Integer.parseInt(request.getParameter("roleID"));
                int n = dao.changeRole(ro_id, id);
                response.sendRedirect("UserController");
            }
            if (service.equals("changeStatus")) {
                int id = Integer.parseInt(request.getParameter("userID"));
                int sta = Integer.parseInt(request.getParameter("status"));
                int n = dao.changeStatus(id, sta);
                response.sendRedirect("UserController");
            }
            if (service.equals("changeStatusClass")) {
                String classid = request.getParameter("class_ID");
                int id = Integer.parseInt(request.getParameter("userID"));
                int sta = Integer.parseInt(request.getParameter("status"));
                int n = dao.changeStatus(id, sta);
                Vector<User> v1 = dao.listUserByClass(classid);
                request.setAttribute("listByClass", v1);
                response.sendRedirect("UserController");
                request.getRequestDispatcher("/views/ListUserByClass.jsp").forward(request, response);
            }

            if (service.equals("addNewUser")) {
                String submitAd = request.getParameter("submitAd");
                if (submitAd == null) {
                    request.getRequestDispatcher("/views/AddNewUser.jsp").forward(request, response);
                } else {
                    LocalDate da = LocalDate.now();
                    String roll = request.getParameter("rollNumber");
                    String name = request.getParameter("fullName");
                    int gender = Integer.parseInt(request.getParameter("gender"));
                    String dateBirth = request.getParameter("dob");
                    String email = request.getParameter("email");
                    int roleid = Integer.parseInt(request.getParameter("roleID"));
                    String pass = dao.RandomChar(8);
                    String note = request.getParameter("NOTE");

                    request.setAttribute("roll_number", roll);
                    request.setAttribute("FullNAME", name);
                    request.setAttribute("date_of_birth", dateBirth);
                    request.setAttribute("email", email);
                    request.setAttribute("note", note);
                    request.setAttribute("dateNow", da);
                    if (roll.equals("") || name.equals("") || dateBirth.equals("") || email.equals("")) {
                        request.setAttribute("err", "Not allow null!");
                        request.setAttribute("FullNAME", name);
                        request.getRequestDispatcher("/views/AddNewUser.jsp").forward(request, response);
                    }
                    Vector<User> vec1 = dao.listAllUserNoID();
                    for (User uu : vec1) {
                        if (email.equals(uu.getEmail())) {
                            request.setAttribute("FullNAME", name);
                            request.setAttribute("err", "Your email has been used! Please enter the other email!");
                            request.getRequestDispatcher("/views/AddNewUser.jsp").forward(request, response);
                        }
                    }
                    User u2 = new User(roll, name, gender, dateBirth, email, roleid, 1, email, pass, note);
                    int n = dao.addUser(u2);
                    if (n > 0) {
                        String subject = "Register account successfully!";
                        String message = "<!DOCTYPE html>\n"
                                + "<html lang=\"en\">\n"
                                + "\n"
                                + "<head>\n"
                                + "</head>\n"
                                + "\n"
                                + "<body>\n"
                                + "    <h3 style=\"color: blue;\">Welcome to Project Manager System!</h3>\n"
                                + "    <div>Your account name is your email</div>\n"
                                + "    <div>And your password to login is: </div>\n"
                                + "    <h1>" + pass + "</h1>\n"
                                + "\n"
                                + "</body>\n"
                                + "\n"
                                + "</html>";
                        daoSen.send(email, subject, message, AbstractConstants.EMAIL_USERNAME, AbstractConstants.EMAIL_PASSWORD);
                        request.setAttribute("alert", new Alert().alert("", "Add new User successfully!", Alert.SUCCESS));
                        request.getRequestDispatcher("/views/AddNewUser.jsp").forward(request, response);
                        return;
                    } else {
                        request.setAttribute("alert", new Alert().alert("", "Add new User failed!", Alert.ERROR));
                        request.getRequestDispatcher("/views/AddNewUser.jsp").forward(request, response);
                    }
                }

            }

            if (service.equals("editUser")) {
                int id = Integer.parseInt(request.getParameter("userID"));
                User u = dao.listUpdate(id);
                LocalDate da = LocalDate.now();
                request.setAttribute("listUpdate", u);
                request.setAttribute("uid", id);
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.setAttribute("dateNow", da);
                    request.getRequestDispatcher("/views/UpdateUser.jsp").forward(request, response);
                } else {
                    String roll = request.getParameter("rollNumber");
                    String name = request.getParameter("fullName");
                    int gender = Integer.parseInt(request.getParameter("gender"));
                    String dateBirth = request.getParameter("dob");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");
                    int roleid = Integer.parseInt(request.getParameter("roleID"));
                    int status = Integer.parseInt(request.getParameter("status"));
                    String note = request.getParameter("NOTE");
                    if (roll.equals("") || name.equals("") || dateBirth.equals("") || email.equals("") || phone.equals("")) {
                        request.setAttribute("rollNumber", roll);
                        request.setAttribute("fullName", name);
                        request.setAttribute("dob", dateBirth);
                        request.setAttribute("email", email);
                        request.setAttribute("phone", phone);
                        request.setAttribute("roleID", roleid);
                        request.setAttribute("NOTE", note);
                        User u1 = new User(id, roll, name, gender, dateBirth, email, phone, status);
                        request.setAttribute("dateNow", da);
                        request.setAttribute("err", "Not allow null!");
                        request.setAttribute("listUpdate", u1);
                        request.getRequestDispatcher("/views/UpdateUser.jsp").forward(request, response);
                        return;
                    }
                    Vector<User> vec1 = dao.listAllUser(id);
                    for (User uu : vec1) {

                        if (email.equals(uu.getEmail())) {
                            request.setAttribute("rollNumber", roll);
                            request.setAttribute("fullName", name);
                            request.setAttribute("dob", dateBirth);
                            request.setAttribute("email", email);
                            request.setAttribute("phone", phone);
                            request.setAttribute("dateNow", da);
                            request.setAttribute("roleID", roleid);
                            request.setAttribute("NOTE", note);
                            User u1 = new User(id, roll, name, gender, dateBirth, email, phone, roleid, status, note);
                            request.setAttribute("err", "Your email has been used! Please enter the other email!");
                            request.setAttribute("phone", phone);
                            request.setAttribute("listUpdate", u1);
                            request.getRequestDispatcher("/views/UpdateUser.jsp").forward(request, response);
                        }
                    }
                    if (phone.length() == 10 && phone.startsWith("0") && dao.isNumber(phone)) {

                    } else {
                        request.setAttribute("rollNumber", roll);
                        request.setAttribute("fullName", name);
                        request.setAttribute("dob", dateBirth);
                        request.setAttribute("dateNow", da);
                        request.setAttribute("email", email);
                        request.setAttribute("phone", phone);
                        request.setAttribute("roleID", roleid);
                        request.setAttribute("NOTE", note);
                        User u1 = new User(id, roll, name, gender, dateBirth, email, phone, roleid, status, note);
                        request.setAttribute("err", "Your mobile phone must be about 10 digits from 0 to 9 and must be start with 0!");
                        request.setAttribute("phone", phone);
                        request.setAttribute("listUpdate", u1);
                        request.getRequestDispatcher("/views/UpdateUser.jsp").forward(request, response);
                    }

                    User u1 = new User(id, roll, name, gender, dateBirth, email, phone, roleid, status, note);
                    int n = dao.updateUser(u1);
                    if (n > 0) {
                        request.setAttribute("alert", new Alert().alert("", "Update User successfully!", Alert.SUCCESS));
                        request.getRequestDispatcher("/views/UpdateUser.jsp").forward(request, response);
                    }
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
    }

}

