package com.management.dao;

import com.management.connectdb.ConnectJDBC;
import com.management.entity.classUser;
import com.management.entity.Class_s;
import com.management.entity.Subject;
import com.management.entity.User;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DAOSen extends ConnectJDBC {

    private final static String secretKey = "g1swp";

    public String encrypt(String strToEncrypt) { // mah oa . cai dau tien la mat khau truyen vao
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = secretKey.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public String decrypt(String strToDecrypt) { // giai ma
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = secretKey.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public String RandomBullSh() {
        String result = "";
        Random rand = new Random();

        for (int i = 0; i < 6; i++) {
            int a = rand.nextInt(10);
            result = result + a;
        }
        return result;
    }

    public List<classUser> AllClassUser(int userid) {
        List<classUser> list = new ArrayList<>();
        String sql = "SELECT * FROM classuser a\n"
                + "join class b on a.class_id = b.class_id\n"
                + "join user c on a.user_id = c.user_id\n"
                + "join subject d on b.subject_id = d.subject_id \n"
                + "join user e on d.author_id = e.user_id\n"
                + "join user f on b.trainer_id = f.user_id\n"
                + "where a.user_id = " + userid + ";";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new classUser(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
                        rs.getInt(11), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(23),
                        rs.getString(36), rs.getString(37), rs.getString(43), rs.getString(57), rs.getString(60), rs.getString(16)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public User Loged(String user_id) {
        String sql = "select * from user where user_id = '" + user_id + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User EmailExist(String mail) {
        String sql = "select * from user where email = '" + mail + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User Login(String username, String password) {
        String sql = "select * from user where username = '" + username + "'  and pass = '" + password + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User Login1(String mail, String password) {
        String sql = "select * from user where email = '" + mail + "'  and pass = '" + password + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> AllUser() {
        List<User> list = new ArrayList<>();
        String sql = "select * from user";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void send(String to, String sub,
                            String msg, final String user, final String pass) throws MessagingException {
        Properties prop = new Properties();

        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.starttls.required", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setContent(msg, "text/html; charset=UTF-8");

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new MessagingException(e.getMessage());
        }
    }

    public void AddUser(String mail, String user, String pass, String name) {
        String sql = "INSERT INTO user (email, username, pass, fullname) VALUES ('" + mail + "', '" + user + "', '" + pass + "', '" + name + "');";
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void ChangePassbyEmail(String mail, String repass) {
        String sql = "UPDATE user\n"
                + " SET pass = '" + repass + "'\n"
                + " WHERE email = '" + mail + "';";
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Subject> AllSubjecta() {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject a left join user b on a.author_id = b.user_id;";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(8), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean checkExistedSubject(String subjectCode, String subjectName) {
        boolean result = false;
        String sql = "select * from subject where subject_code = '" + subjectCode + "' or subject_name = '" + subjectName + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Subject> getAllSubject() {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject a left join user b on a.author_id = b.user_id;";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(8), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Subject> AllSubjects() {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject a left join user b on a.author_id = b.user_id;";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(8), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<User> AllAuthor() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user where role_id = 3;";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Subject> AllSubjecta2() {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject a left join user b on a.author_id = b.user_id where a.status = 1;";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(8), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Subject> AllSubject(int index, String order) {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject a left join user b on a.author_id = b.user_id order by " + order + " limit 12 offset " + index + ";";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(8), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Subject> AllSubject2(int index, String order) {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject a left join user b on a.author_id = b.user_id where a.status = 1 order by " + order + " limit 12 offset " + index + ";";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(8), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addSubject(String subject_code, String subject_name, String author_id, String status) {
        String sql = "INSERT INTO subject (subject_code, subject_name, author_id, status)"
                + " VALUES ('" + subject_code + "', '" + subject_name + "', '" + author_id + "', '" + status + "');";
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Subject> getFilterSubject(String filter, int index, int size) {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject";
        if(!filter.equals("")) {
            sql += "\nwhere " + filter;
        }
        sql += "\nlimit " + size + " offset " + (index - 1) * size;

        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countSubject(String filter) {
        int count = 0;
        String sql = "select count(*) from subject";
        if(!filter.equals("")) {
            sql += "\nwhere " + filter;
        }
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public User getUserById(int userId) {
        String sql = "select * from user where user_id = " + userId;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Class_s> getAllClass() {
        List<Class_s> list = new ArrayList<>();
        String sql = "SELECT * FROM class;";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int i = 0;
                Class_s c = new Class_s();
                c.setId(rs.getInt(++i));
                c.setClassCode(rs.getString(++i));
                c.setTrainerId(rs.getString(++i));
                c.setSubjectId(rs.getString(++i));
                c.setClassYear(rs.getString(++i));
                c.setClassTerm(rs.getString(++i));
                c.setBlock5Class(rs.getString(++i));
                c.setStatus(rs.getInt(++i));
                c.setNote(rs.getString(++i));

                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Class_s getClassById(String id) {
        String sql = "SELECT * FROM class where class_id = " + id;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int i = 0;
                Class_s c = new Class_s();
                c.setId(rs.getInt(++i));
                c.setClassCode(rs.getString(++i));
                c.setTrainerId(rs.getString(++i));
                c.setSubjectId(rs.getString(++i));
                c.setClassYear(rs.getString(++i));
                c.setClassTerm(rs.getString(++i));
                c.setBlock5Class(rs.getString(++i));
                c.setStatus(rs.getInt(++i));
                c.setNote(rs.getString(++i));
                return c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
