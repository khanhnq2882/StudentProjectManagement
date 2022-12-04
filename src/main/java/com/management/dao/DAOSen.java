package com.management.dao;

import com.management.connectdb.ConnectJDBC;
import com.management.entity.ClassUser;
import com.management.entity.User;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

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
    public List<ClassUser> AllClassUser(int userid) {
        List<ClassUser> list = new ArrayList<>();
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
                list.add(new ClassUser(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
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
}
