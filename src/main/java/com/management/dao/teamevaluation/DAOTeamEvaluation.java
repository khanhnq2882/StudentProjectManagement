package com.management.dao.teamevaluation;

import com.management.connectdb.ConnectJDBC;
import com.management.entity.User;

import java.sql.ResultSet;

public class DAOTeamEvaluation extends ConnectJDBC {
    public User getUserByID(String user_id) {
        String sql = "select * from user where user_id = " + user_id + "";
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
}
