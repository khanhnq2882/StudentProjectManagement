package com.management.dao;

import com.management.connectdb.ConnectJDBC;
import com.management.entity.User;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.MessageDigest;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class UserDAO extends ConnectJDBC {

    PreparedStatement ps = null;
    Connection conn = ConnectJDBC.getConnection();

    public Vector<User> listAllUser(int sartFrom, String roll, int myID) {
        Vector<User> ve = new Vector<>();
        String sql = "select * from user where roll_number like '%" + roll + "%' or fullname like '%" + roll + "%' LIMIT 10 OFFSET " + sartFrom + " ";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                ve.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
            for (int i = 0; i < ve.size(); i++) {
                if (ve.get(i).getUser_id() == myID) {
                    ve.remove(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ve;
    }

    public int coutSearchRole(int roleID) {
        int n = 0;

        String sql;
        if (roleID != 5) {
            sql = "select COUNT(*) from user where role_id = " + roleID + "";
        } else {
            sql = "select COUNT(*) from user";
        }
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public String ConvertDateFormat(String s) {
        //neu can this hay sua thang nay
        final String temp = "yyyy-MM-dd";
        Date date1 = new Date();
        if (s.equals("") || s == null) {
            s = "not yet";
        }
        try {
            date1 = new SimpleDateFormat(temp).parse(s);
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String strDate = dateFormat.format(date1);
            return strDate;
        } catch (java.text.ParseException ex) {
            return s;
        }
    }

    public int coutSearchStatus(int roleID) {
        int n = 0;
        String sql;
        if (roleID != 2) {
            sql = "select COUNT(*) from user where status = " + roleID + "";
        } else {
            sql = "select COUNT(*) from user";
        }
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public Vector<User> listAll(int sartFrom) {
        Vector<User> ve = new Vector<>();
        String sql = "select * from user LIMIT 10 OFFSET " + sartFrom + " ";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                ve.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ve;
    }

    public int coutUs(Vector<User> lstus) {
        return lstus.size();
    }

    public Vector<User> searchByRole(String id, int start, int uid) {
        Vector<User> veU = new Vector<>();
        String sql = "select * from user where role_id = " + id + " LIMIT 10 OFFSET " + start + "";
//        String sql = "select * from user where role_id = " + id;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                veU.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
            for (int i = 0; i < veU.size(); i++) {
                if (veU.get(i).getUser_id() == uid) {
                    veU.remove(i);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veU;
    }

    public Vector<User> sortAsc(int sart, int uid) {
        Vector<User> veS = new Vector<>();
        String sql = "select * from user order by fullname asc LIMIT 10 OFFSET " + sart + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                veS.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
            for (int i = 0; i < veS.size(); i++) {
                if (veS.get(i).getUser_id() == uid) {
                    veS.remove(i);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veS;
    }

    public Vector<User> sortDesc(int sart, int uid) {
        Vector<User> veS = new Vector<>();
        String sql = "select * from user order by fullname desc LIMIT 10 OFFSET " + sart + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                veS.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
            for (int i = 0; i < veS.size(); i++) {
                if (veS.get(i).getUser_id() == uid) {
                    veS.remove(i);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veS;
    }

    public Vector<User> searchByStatus(int sta, int startform, int uid) {
        Vector<User> veS = new Vector<>();
        String sql = "select * from user where status = " + sta + " LIMIT 10 OFFSET " + startform + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                veS.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
            for (int i = 0; i < veS.size(); i++) {
                if (veS.get(i).getUser_id() == uid) {
                    veS.remove(i);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veS;
    }

    public Vector<User> listAllUserNoID() {
        Vector<User> ve = new Vector<>();
        String sql = "select * from user";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                ve.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ve;
    }

    public Vector<User> listAllUser(int id) {
        Vector<User> ve = new Vector<>();
        String sql = "select * from user where user_id <> " + id + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                ve.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ve;
    }

    public int countUser(String roll) {
        String sql = "select COUNT(*) from user where roll_number like '%" + roll + "%' or fullname like '%" + roll + "%'";
        ResultSet rs = getData(sql);
        int n = 0;
        try {
            if (rs.next()) {
                n = rs.getInt(1) - 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (n <= 0) {
            return 0;
        } else {
            return n;
        }
    }

    public Vector<User> listUserByClass(String id) {
        Vector<User> v = new Vector<>();
        String sql = "select * from user a inner join classuser b on a.user_id = b.user_id\n"
                + "inner join class c on b.class_id = c.class_id where b.class_id = " + id + " and a.role_id = 1";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13));
                v.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    public int addUser(User u) {
        int n = 0;
        String sql = "INSERT INTO user(roll_number, fullname, gender, date_of__birth, email, role_id, status, username, pass, note)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setString(1, u.getRoll_number());
            pre.setString(2, u.getFullname());
            pre.setInt(3, u.getGender());
            pre.setString(4, u.getDate_of_birth());
            pre.setString(5, u.getEmail());
            pre.setInt(6, u.getRole_id());
            pre.setInt(7, u.getStatus());
            pre.setString(8, u.getUser());
            pre.setString(9, u.getPass());
            pre.setString(10, u.getNote());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public void send(String to, String sub,
                     String msg, final String user, final String pass) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
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
            message.setContent(msg, "text/html");
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

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

    public User listUpdate(int user_id) {
        String sql = "select * from user where user_id = '" + user_id + "'";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getString(14));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updateUser(User u) {
        int n = 0;
        String sql = "update user set roll_number = ?, fullname = ?, gender = ?, date_of__birth = ?, email = ?, mobile = ?, role_id = ?, status= ?, note = ? where user_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, u.getRoll_number());
            ps.setString(2, u.getFullname());
            ps.setInt(3, u.getGender());
            ps.setString(4, u.getDate_of_birth());
            ps.setString(5, u.getEmail());
            ps.setString(6, u.getMobile());
            ps.setInt(7, u.getRole_id());
            ps.setInt(8, u.getStatus());
            ps.setString(9, u.getNote());
            ps.setInt(10, u.getUser_id());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(sql);
        }
        return n;
    }

    public int removeUser(int id) {
        int n = 0;
        String sql = "delete from user where user_id = " + id + "";
        try {
            Statement state = getConnection().createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //    public Vector<User> detailUser(int id) {
//        User u1;
//        Vector<User> ve = new Vector<>();
//        String sql = "select * from user where user_id = " + id + "";
//        ResultSet rs = getData(sql);
//        try {
//            if (rs.next()) {
//                ve.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
//                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
//                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13)));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return ve;
//    }
    public int changeRole(int roleid, int id) {
        int n = 0;
        String sql = "update user set role_id = " + roleid + " where user_id = " + id + "";
        try {
            Statement state = getConnection().createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int changeStatus(int userid, int status) {
        int n = 0;
        String sql = "update user set status = " + status + " where user_id = " + userid + "";
        try {
            Statement state = getConnection().createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<User> searchUser(String roll) {
        Vector<User> ve = new Vector<>();
        String sql = "select * from user where roll_number like '%" + roll + "%' or fullname like '%" + roll + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                ve.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ve;
    }

    public int countUser() {
        int n = 0;

        String sql = "select COUNT(*) from user";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (n <= 0) {
            return 0;
        } else {
            return n;
        }
    }

    public boolean isNumber(String s) {
        return s.matches("^\\d+$");
    }

    public String RandomChar(int a) {
        Random random = new Random();
        String rs = "";
        for (int i = 0; i < a; i++) {
            char randomizedCharacter = (char) (random.nextInt(26) + 'a');
            rs += randomizedCharacter;
        }
        return rs;
    }

    /*
    "SELECT * FROM subject a left join user b\n"
                + "on a.author_id = b.user_id where subject_code\n"
                + "like '%" + code + "%' or subject_name like '%" + code + "%'\n"
                + "order by b.user_id"
     */
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
//        System.out.println(dao.coutSearchRole(2));
//        System.out.println(dao.searchByStatus(0, 0, 1).size());

        int a = dao.countUser("1");
        System.out.println(a);

//        System.out.println(dao.listAllUser(0, "", 1));
//        Vector<User> v = dao.listAllUser(0, "", 1);
//        for (User o : v) {
//            o.setDate_of_birth(dao.ConvertDateFormat(o.getDate_of_birth()));
//            System.out.println(o);
//        }
//        System.out.println(dao.searchByStatus(0, 0, 1));
//        int n = dao.updateUser(new User(1, "123", "Dang Tat", 0, "2022-01-02", "tienanh@gmail.com", "0123456789", 0));
//        if (n > 0) {
//            System.out.println("oke detsune!");
//        } else {
//            System.out.println("ngu vcl");
////        }
//        Vector<User> ve = dao.listAllUser("0");
//        for (User u : ve) {
//            System.out.println(ve.size());
//        }
//        int n = dao.changeStatus(2, 0);
//        System.out.println(dao.countUser());
//        int n = dao.countUser("he");
//        System.out.println(n);
//        System.out.println(dao.searchByStatus(0));
//        User u = new User("Demo Roll", "Demo Name", 1, "2001/2/5", "capuchino2709@gmail.com", 4, 1, "tiencap", dao.encrypt("abcde"), "demo note");
//        int n = dao.addUser(u);
//        if (n > 0){
//            System.out.println("success!");
//        } else {
//            System.out.println("failed!");
//        }
    }
}
