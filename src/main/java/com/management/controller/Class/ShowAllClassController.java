package com.management.controller.Class;

import com.management.dao.DAOChangePass;
import com.management.dao.DAOSen;
import com.management.entity.Class_s;
import com.management.entity.Subject;
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
import java.util.Vector;

@WebServlet(name = "ShowAllClass", urlPatterns = {"/ShowAllClass"})
public class ShowAllClassController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("go");
            HttpSession session = request.getSession();
            User loged = (User) session.getAttribute("Loged");
            DAOChangePass dao = new DAOChangePass();
            DAOSen daoS = new DAOSen();
            if (loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
                return;
            }
//            try {
//                if (loged.getRole_id() != 4) {
//                    request.setAttribute("messE", "Seems like you don't have permission to do this");
//                    request.getRequestDispatcher("/jsp/Class/Error.jsp").forward(request, response);
//                    return;
//                }
//            } catch (Exception e) {
//            }
            if (service == null) {
                service = "ViewAllClass";
            }
            if (service.equals("ViewAllClass")) {
                String search = request.getParameter("searchClass");
                String startFrom = request.getParameter("startFrom");
                String page = request.getParameter("page");

                if (startFrom == null || startFrom.equalsIgnoreCase("")) {
                    startFrom = "0";
                }
                if (search == null) {
                    search = "";
                }

                if (page == null) {
                    page = "1";
                }
                if (page.equals("1")) {
                    startFrom = "0";
                }
                if (!page.equals("1")) {
                    int a = Integer.parseInt(page);
                    startFrom = (a - 1) * 20 + "";
                }

                request.setAttribute("page", page);

                Vector<User> vectT = dao.showAllTeacher();
                Vector<Class_s> vectClass = dao.ViewAllClassName(search, startFrom, "");

                //=================================================================cai nay lam filter
                String where = "";
                String sta = request.getParameter("ClaSta");
                if (sta == null || sta.equals("")) {
                    sta = "2";
                }
                if (!sta.equals("2")) {
                    int a = Integer.parseInt(sta);
                    where += " and status = " + a;
                }

                String trainer = request.getParameter("trainerName");
                if (trainer == null || trainer.equals("")) {

                } else {
                    where += " and trainer_id = " + trainer;

                }
                request.setAttribute("trainerName", trainer);
                Vector<User> allTra = dao.allTrainer();
                request.setAttribute("allTra", allTra);
                request.setAttribute("sta", sta);
                vectClass = dao.ViewAllClassName(search, startFrom, where);
                //==================================================================filter

                int soLuongTrang;
                if (dao.CountClass(search, where) % 20 == 0) {
                    soLuongTrang = dao.CountClass(search, where) / 20;
                } else {
                    soLuongTrang = dao.CountClass(search, where) / 20 + 1;
                }

                request.setAttribute("totalResult", dao.CountClass(search, where));

                request.setAttribute("soLuongTrang", soLuongTrang);
                //   request.setAttribute("tongSoTrang", dao.CountClass());
                request.setAttribute("search", search);

                request.setAttribute("startFrom", startFrom);
                request.setAttribute("vect", vectClass);
                request.setAttribute("vectT", vectT);
                request.getRequestDispatcher("/jsp/Class/ShowAllClass.jsp").forward(request, response);
            }
            if (service.equals("AddClass")) {
                String submit = request.getParameter("submit");
                Vector<User> vectT = dao.showAllTeacher();
                List<com.management.entity.Subject> listSub = daoS.AllSubjecta();
                request.setAttribute("vectT", vectT);
                request.setAttribute("listSub", listSub);
                if (submit == null) {
                    request.getRequestDispatcher("/jsp/Class/AddClass.jsp").forward(request, response);
                } else {
                    String class_code = dao.chuannHoa(request.getParameter("class_code"));
                    String trainer = request.getParameter("trainer");
                    String subject = request.getParameter("subject");
                    String class_Year = dao.chuannHoa(request.getParameter("class_Year"));
                    String class_term = request.getParameter("class_term");
                    String block5 = request.getParameter("block5");
                    String status = request.getParameter("status");

                    if (class_code.equals("") || trainer.equals("") || class_Year.equals("")
                            || subject.equals("")
                            || block5.equals("") || class_term.equals("")) {
                        request.setAttribute("class_code", class_code);
                        request.setAttribute("trainer", trainer);
                        request.setAttribute("subject", subject);
                        request.setAttribute("class_Year", class_Year);
                        request.setAttribute("class_term", class_term);
                        request.setAttribute("block5", block5);
                        request.setAttribute("status", status);

                        request.setAttribute("vectT", vectT);
                        request.setAttribute("listSub", listSub);

                        request.setAttribute("mess", "Not allow null");
                        request.getRequestDispatcher("/jsp/Class/AddClass.jsp").forward(request, response);
                        return;
                    }
                    if (dao.checkClassCode(class_code)) {
                        request.setAttribute("class_code", class_code);
                        request.setAttribute("trainer", trainer);
                        request.setAttribute("subject", subject);
                        request.setAttribute("class_Year", class_Year);
                        request.setAttribute("class_term", class_term);
                        request.setAttribute("block5", block5);
                        request.setAttribute("status", status);

                        request.setAttribute("vectT", vectT);
                        request.setAttribute("listSub", listSub);

                        request.setAttribute("mess", "This class is already exist");
                        request.getRequestDispatcher("/jsp/Class/AddClass.jsp").forward(request, response);
                        return;
                    }

                    Class_s clas = new Class_s(0, class_code, trainer, subject, class_Year, class_term, block5, Integer.parseInt(status));
                    dao.addClass(clas);

                    request.setAttribute("link", "ShowAllClass");
                    request.getRequestDispatcher("/jsp/Class/Success.jsp").forward(request, response);
                }
            }
            if (service.equals("updateClass")) {
                String submit = request.getParameter("submit");
                String classId = request.getParameter("ClassId");
                request.setAttribute("cid", classId);

                Vector<User> vectT = dao.showAllTeacher();
                List<Subject> listSub = daoS.AllSubjecta();
                request.setAttribute("vectT", vectT);
                request.setAttribute("listSub", listSub);

                Class_s cla = dao.viewClassById(classId);
                if (submit == null) {
                    String class_code = cla.getClassCode();
                    String trainer = cla.getTrainerId();
                    String subject = cla.getSubjectId();
                    String class_Year = cla.getClassYear();
                    String class_term = cla.getClassTerm();
                    String block5 = cla.getBlock5Class();
                    int status = cla.getStatus();

                    request.setAttribute("class_code", class_code);
                    request.setAttribute("trainer", trainer);
                    request.setAttribute("subject", subject);
                    request.setAttribute("class_Year", class_Year);
                    request.setAttribute("class_term", class_term);
                    request.setAttribute("block5", block5);
                    request.setAttribute("status", status);

                    request.setAttribute("update", "updateClass");

                    request.getRequestDispatcher("/jsp/Class/AddClass.jsp").forward(request, response);
                } else {
                    String class_code = dao.chuannHoa(request.getParameter("class_code"));
                    String trainer = request.getParameter("trainer");
                    String subject = request.getParameter("subject");
                    String class_Year = dao.chuannHoa(request.getParameter("class_Year"));
                    String class_term = request.getParameter("class_term");
                    String block5 = request.getParameter("block5");
                    String status = request.getParameter("status");

                    if (class_code.equals("") || trainer.equals("") || class_Year.equals("")
                            || subject.equals("")
                            || block5.equals("") || class_term.equals("")) {
                        request.setAttribute("class_code", class_code);
                        request.setAttribute("trainer", trainer);
                        request.setAttribute("subject", subject);
                        request.setAttribute("class_Year", class_Year);
                        request.setAttribute("class_term", class_term);
                        request.setAttribute("block5", block5);
                        request.setAttribute("status", status);

                        request.setAttribute("update", "updateClass");

                        request.setAttribute("mess", "Not allow null");
                        request.getRequestDispatcher("/jsp/Class/AddClass.jsp").forward(request, response);
                        return;
                    }
//                    else if (dao.checkClassCode(class_code)) {
//                        request.setAttribute("class_code", class_code);
//                        request.setAttribute("trainer", trainer);
//                        request.setAttribute("subject", subject);
//                        request.setAttribute("class_Year", class_Year);
//                        request.setAttribute("class_term", class_term);
//                        request.setAttribute("block5", block5);
//                        request.setAttribute("status", status);
//
//                        request.setAttribute("vectT", vectT);
//                        request.setAttribute("listSub", listSub);
//
//                        request.setAttribute("update", "updateClass");
//                        request.setAttribute("mess", "This class is already exist");
//                        request.getRequestDispatcher("/jsp/Class/AddClass.jsp").forward(request, response);
//                        return;
//                    }
                    else {
                        Class_s s = new Class_s(Integer.parseInt(classId), class_code, trainer, subject, class_Year, class_term, block5, Integer.parseInt(status));
                        dao.updateAllClass(s);
                        request.setAttribute("link", "ShowAllClass");
                        request.getRequestDispatcher("/jsp/Class/Success.jsp").forward(request, response);
                    }

                }

            }
            if (service.equals("showBySubject")) {
                //ShowAllClass?go=showBySubject&subjectId=
                String sid = request.getParameter("subjectId");
                Vector<Class_s> v = dao.viewALlClassBySubject(sid);
                Vector<User> vT = dao.showAllTeacher();
                request.setAttribute("vect", v);
                request.setAttribute("vectT", vT);
                request.getRequestDispatcher("/jsp/Class/ShowAllClassBySubjectID.jsp").forward(request, response);
            }
            if (service.equals("test")) {
                request.getRequestDispatcher("/jsp/Class/HomeExample.jsp").forward(request, response);
            }
            if (service.equals("testForm")) {
                String a = request.getParameter("aaa");
                out.print(a);
            }
        }
    }
    }
