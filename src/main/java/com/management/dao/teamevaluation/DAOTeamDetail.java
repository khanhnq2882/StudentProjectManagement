package com.management.dao.teamevaluation;

import com.management.connectdb.ConnectJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOTeamDetail extends ConnectJDBC {
    Connection conn = ConnectJDBC.getConnection();

    ResultSet rs = null;
    PreparedStatement ps = null;
    public void editTeam (int team_id, String class_id, String topic_code, String topic_name, String gitlab_url, int status) {
        String sql = "update team set class_id = ?, topic_code = ?, topic_name = ?, gitlab_url = ?, status = ? \n"
                + "where team_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, class_id);
            ps.setString(2, topic_code);
            ps.setString(3, topic_name);
            ps.setString(4, gitlab_url);
            ps.setInt(5, status);
            ps.setInt(6, team_id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

