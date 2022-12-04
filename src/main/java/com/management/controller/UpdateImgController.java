package com.management.controller;

import com.management.dao.DAOSen;
import com.management.dao.DAOUpdate;
import com.management.entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "UpdateImgController", value = "/UpdateImg")
public class UpdateImgController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("Loged") == null) {
            request.getRequestDispatcher("Login_sen").forward(request, response);
        }
        if (session.getAttribute("Loged") != null) {
            String filename = null;
            DAOUpdate daoUpdate = new DAOUpdate();
            DAOSen daoSen = new DAOSen();
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletContext servletContext = this.getServletConfig().getServletContext();
                File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
                factory.setRepository(repository);
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> items = upload.parseRequest(request);
                Iterator<FileItem> iter = items.iterator();
                HashMap<String, String> fields = new HashMap<>();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (item.isFormField()) {
                        fields.put(item.getFieldName(), item.getString());
                    } else {
                        filename = item.getName();
                        String userid = fields.get("userid");
                        if (filename == null || filename.equals("")) {
                            filename = daoSen.Loged(userid).getAvatar_link();
                            request.setAttribute("haizz", "Làm ơn hãy chọn một ảnh rồi mới Lưu");
                            request.getRequestDispatcher("views/Update.jsp").forward(request, response);
                        } else {
                            filename = item.getName();
                            Path path = Paths.get(filename);
                            String storePath = servletContext.getRealPath("/uploads");
                            File uploadFile = new File(storePath + "/" + path.getFileName());
                            File deleteFile = new File(storePath + "/" + daoSen.Loged(userid).getAvatar_link());
                            deleteFile.delete();
                            item.write(uploadFile);
                        }
                    }
                }
                String userid = fields.get("userid");
                daoUpdate.UpdateAvatar(userid, filename);
                session.removeAttribute("Loged");
                User Loged = daoSen.Loged(userid);
                session.setAttribute("Loged", Loged);
            } catch (Exception e) {
            }

            request.getRequestDispatcher("views/Update.jsp").forward(request, response);
        }
    }
}
