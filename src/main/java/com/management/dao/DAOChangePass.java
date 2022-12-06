package com.management.dao;

import com.management.connectdb.ConnectJDBC;
import com.management.entity.Class_s;
import com.management.entity.User;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.sql.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.Stack;
import com.management.entity.User;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.sql.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.Stack;
import java.sql.*;
import java.util.Stack;
import java.util.Vector;

public class DAOChangePass extends ConnectJDBC {
    Connection conn = ConnectJDBC.getConnection();
    ResultSet rs = null;
    PreparedStatement ps = null;


    public Vector<Class_s> viewClassByStudent(String s) {
        Vector<Class_s> vect = new Vector<>();
        String sql = "select * from class a inner join classuser b "
                + "on a.class_id = b.class_id where b.user_id = " + s;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String ClassCode = rs.getString(2);
                String trainerId = rs.getString(3);
                String SubjectId = rs.getString(4);
                String classID = rs.getString(5);
                String picT = rs.getString(3);
                String Block5Class = rs.getString(7);
                int status = rs.getInt(8);
                Class_s u = new Class_s(id, ClassCode, ShowTeacher(trainerId), ShowSubject(SubjectId), classID, picT, Block5Class, status);
                vect.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vect;
    }

    public String ShowTeacher(String id) {
        String sql = "select distinct a.fullname, a.user_id from user a\n"
                + "inner join class c on a.user_id = c.trainer_id where a.user_id = " + id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String ShowSubject(String id) {
        String sql = "select distinct b.subject_name from class a"
                + " inner join subject b on a.subject_id "
                + "= b.subject_id where b.subject_id =  " + id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
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

    public int UpdatePass(String pass, int id) {
        int n = 0;
        String sql = "update user set pass = '" + encrypt(pass) + "'"
                + " where user_id = '" + id + "'";
        try {
            Statement state = getConnection().createStatement();
            n = state.executeUpdate(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Class_s> ViewAllClassName(String name, String startFrom, String where) {
        Vector<Class_s> list = new Vector<>();
        String sql = "select * from class where class_code like '%" + name + "%'"
                + where + " LIMIT 20 offset " + startFrom;
        //  System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String ClassCode = rs.getString(2);
                String trainerId = rs.getString(3);
                String SubjectId = rs.getString(4);
                String ClassYear = rs.getString(5);
                String ClassTerm = rs.getString(6);
                String Block5Class = rs.getString(7);
                int status = rs.getInt(8);
                Class_s u = new Class_s(id, ClassCode, ShowTeacher(trainerId), ShowSubject(SubjectId), ClassYear, ClassTerm, Block5Class, status);
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Vector<User> allTrainer() {
        Vector<User> v = new Stack<>();
        String sql = "select * from user where (role_id = 3 or \n"
                + "role_id = 2) and status = 1";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                User u = new User(rs.getInt(1), rs.getString(3), "", "");
                v.add(u);
            }
        } catch (Exception e) {
        }
        return v;
    }

    public int CountClass(String name, String where) {
        String sql = "select count(*) from class"
                + " where class_code like '%" + name + "%' "
                + where;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String chuannHoa(String st) {
        try {
            st = st.trim();
            st = st.replaceAll("\\s+", " ");
            String[] temp = st.split(" ");
            st = "";
            for (int i = 0; i < temp.length; i++) {
                st += String.valueOf(temp[i].charAt(0)) + temp[i].substring(1);
                if (i < temp.length - 1) {
                    st += " ";
                }
            }
            return st;
        } catch (Exception e) {
        }
        return "";
    }

    public boolean checkClassCode(String name) {
        String sql = " select * from class where class_code like '" + name + "'";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public int addClass(Class_s st) {
        int n = 0;
        String sql = "insert into class(class_code, trainer_id, subject_id, "
                + "class_year, class_term, block5_class, status) \n"
                + "values(?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, st.getClassCode());
            ps.setString(2, st.getTrainerId());
            ps.setString(3, st.getSubjectId());
            ps.setString(4, st.getClassYear());
            ps.setString(5, st.getClassTerm());
            ps.setString(6, st.getBlock5Class());
            ps.setInt(7, st.getStatus());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<User> showAllTeacher() {
        Vector<User> v = new Vector<>();
        String sql = "select * from user where role_id = 2 or role_id = 3";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                v.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    public int countClass(String name, String where) {
        String sql = "select count(*) from class"
                + " where class_code like '%" + name + "%' "
                + where;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String standardization(String st) {
        try {
            st = st.trim();
            st = st.replaceAll("\\s+", " ");
            String[] temp = st.split(" ");
            st = "";
            for (int i = 0; i < temp.length; i++) {
                st += String.valueOf(temp[i].charAt(0)) + temp[i].substring(1);
                if (i < temp.length - 1) {
                    st += " ";
                }
            }
            return st;
        } catch (Exception e) {
        }
        return "";
    }

    public Class_s viewClassById(String name) {
        Vector<Class_s> list = new Vector<>();

        String sql = "select * from class where class_id like '%" + name + "%'  ";
        //   System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String ClassCode = rs.getString(2);
                String trainerId = rs.getString(3);
                String SubjectId = rs.getString(4);
                String ClassYear = rs.getString(5);
                String ClassTerm = rs.getString(6);
                String Block5Class = rs.getString(7);
                int status = rs.getInt(8);
                Class_s u = new Class_s(id, ClassCode, trainerId,
                        SubjectId, ClassYear, ClassTerm, Block5Class, status);

                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAllClass(Class_s c) {
        String sql_update = "update class set class_code = '" + c.getClassCode() + "', "
                + "trainer_id= " + c.getTrainerId() + ", "
                + "subject_id= " + c.getSubjectId() + ", "
                + "class_year = '" + c.getClassYear() + "',\n"
                + " class_term = " + c.getClassTerm() + ", "
                + "block5_class = " + c.getBlock5Class() + ","
                + " status = " + c.getStatus() + " where class_id = " + c.getId();
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql_update);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Vector<Class_s> viewALlClassBySubject(String s) {
        Vector<Class_s> vect = new Vector<>();
        String sql = "select * from class where subject_id = " + s;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String ClassCode = rs.getString(2);
                String trainerId = rs.getString(3);
                String SubjectId = rs.getString(4);
                String ClassYear = rs.getString(5);
                String ClassTerm = rs.getString(6);
                String Block5Class = rs.getString(7);
                int status = rs.getInt(8);
                Class_s u = new Class_s(id, ClassCode, ShowTeacher(trainerId), ShowSubject(SubjectId), ClassYear, ClassTerm, Block5Class, status);
                vect.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vect;
    }

}
