package com.management.dao.teamevaluation;

import com.management.connectdb.ConnectJDBC;
import com.management.entity.Class_s;
import com.management.entity.Team;
import com.management.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOTeam extends ConnectJDBC {
    Connection conn = ConnectJDBC.getConnection();

    ResultSet rs = null;
    PreparedStatement ps = null;
    public Team SearchSetID(String s) {
        List<Team> list = new ArrayList<>();
        String sql = "select * from team where team_id = " + s;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new Team(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public List<Team> viewClassId() {
        List<Team> list = new ArrayList<>();
        String sql = "select distinct class_id from team order by class_id";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Team(rs.getString(1)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public List<Class_s> viewClass() {
        List<Class_s> list = new ArrayList<>();
        String sql = "SELECT class_id, class_code FROM `swp391-project`.class";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Class_s(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public List<Team> viewLeader() {
        List<Team> list = new ArrayList<>();
        String sql = "select fullname from user where role_id = 1";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Team(rs.getString(1)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public int addTeam(Team t) {
        int n = 0;
        String sql = "insert into team (class_id, topic_code, topic_name, gitlab_url, status, team_name) \n"
                + " values (?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, t.getClass_id());
            ps.setString(2, t.getTopic_code());
            ps.setString(3, t.getTopic_name());
            ps.setString(4, t.getGitlab_url());
            ps.setInt(5, t.getStatus());
            ps.setString(6, t.getTeam_name());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    public List<Team> getClassId(String cid) {
        List<Team> list = new ArrayList<>();
        String sql = "select * from team where class_id = " + cid;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
//    public Team(int team_id, String class_id, String topic_code, String topic_name, String gitlab_url, int status, String team_name) {
                int id = rs.getInt(1);
                String class_id =rs.getString(2);
                String topic_code=rs.getString(3);
                String topic_name=rs.getString(4);
                String gitlab=rs.getString(5);
                int status = rs.getInt(6);
                String team_name=rs.getString(7);
                list.add(new Team(id, class_id, topic_code, topic_name, gitlab, status,team_name));
//                list.add(new Team(rs.getInt(1), rs.getString(2),
//                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public List<Team> viewTeamList(String i) {
        List<Team> list = new ArrayList<>();
        String sql = "SELECT * FROM team a inner join class b on a.class_id = b.class_id where a.class_id ="+ i +"";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Team(rs.getInt(1), getClassName2(rs.getString(2)), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getInt(6), getLeaderName2(rs.getString(7))));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public String getClassName2(String classes) {
        String sql = "select distinct b.class_code from team a inner join class b on a.class_id = b.class_id where a.class_id = " + classes;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        return "";
    }
    public String getLeaderName2(String s) {
        String sql = "select distinct b.fullname from team a inner join user b on a.team_name = b.user_id where a.team_name = " + s;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        return "";
    }
    public List<Team> viewTeamTopic(String teamTopic) {
        List<Team> list = new ArrayList<>();
        String sql = "select * from team \n"
                + "where topic_name like '%" + teamTopic + "%';";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Team(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public String getClassName(String s) {
        String sql = "select class_code from class";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        return "";
    }
    public List<Team> searchTeamByTopic(int classId, String topicName, int status) {
        List<Team> list = new ArrayList<>();
        String sql = "select * from team \n"
                + "where class_id = " + classId + " and topic_name like '%" + topicName
                + "%' and status = " + status + ";";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Team(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public int updateStatus(int status, int team_id) {
        int n = 0;
        try {
            String sql = "update team \n"
                    + " set status = ? where team_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, team_id);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    public List<User> viewMemberOfTeam(String s) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user a inner join classuser b on a.user_id = b.user_id where a.user_id and b.team_id = " + s;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10),
                        rs.getInt(11), rs.getString(12), rs.getString(13)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public List<Team> getTeamId(String team_id) {
        List<Team> list = new ArrayList<>();
        String sql = "select * from team where team_id = " + team_id + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Team(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
