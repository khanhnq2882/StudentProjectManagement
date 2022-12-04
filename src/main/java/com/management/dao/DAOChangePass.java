package com.management.dao;

import com.management.connectdb.ConnectJDBC;
import com.management.entity.Class_s;

import java.sql.ResultSet;
import java.util.Vector;

public class DAOChangePass extends ConnectJDBC {

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
}
