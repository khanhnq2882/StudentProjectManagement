package com.management.dao;

import com.management.connectdb.ConnectJDBC;

import com.management.entity.Milestone;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DAOMilestone extends ConnectJDBC {
    Connection conn = ConnectJDBC.getConnection();

    ResultSet rs = null;
    PreparedStatement ps = null;
    public List<Milestone> searchByIteNameAndClassCode(String iteName, String classId) {
        List<Milestone> list = new ArrayList<>();
        String sql = "SELECT ms.milestone_id, iteration_name, class_code,ms.from_date,ms.to_date,ms.status, ms.interation_id,ms.class_id, milestone_name FROM milestone ms\n"
                + "inner join iteration ite\n"
                + "on ms.interation_id = ite.iteration_id\n"
                + "inner join class cl\n"
                + "on ms.class_id = cl.class_id\n"
                + "where iteration_name like '%" + iteName + "%' and cl.class_id =" + classId;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Milestone(rs.getInt(1), rs.getInt(7), rs.getInt(8), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(3), rs.getString(2), rs.getString(9)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public List<Milestone> searchByIteName(String iteName) {
        List<Milestone> list = new ArrayList<>();
        String sql = "SELECT ms.milestone_id, iteration_name, class_code,ms.from_date,ms.to_date,ms.status, ms.interation_id,ms.class_id, milestone_name FROM milestone ms\n"
                + "inner join iteration ite\n"
                + "on ms.interation_id = ite.iteration_id\n"
                + "inner join class cl\n"
                + "on ms.class_id = cl.class_id\n"
                + "where iteration_name like '%" + iteName + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Milestone(rs.getInt(1), rs.getInt(7), rs.getInt(8), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(3), rs.getString(2), rs.getString(9)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public List<Milestone> searchByClassCode(String classId) {
        List<Milestone> list = new ArrayList<>();
        String sql = "SELECT ms.milestone_id, iteration_name, class_code,ms.from_date,ms.to_date,ms.status, ms.interation_id,ms.class_id, milestone_name FROM milestone ms\n"
                + "inner join iteration ite\n"
                + "on ms.interation_id = ite.iteration_id\n"
                + "inner join class cl\n"
                + "on ms.class_id = cl.class_id\n"
                + "where cl.class_id =" + classId;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Milestone(rs.getInt(1), rs.getInt(7), rs.getInt(8), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(3), rs.getString(2), rs.getString(9)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public List<Milestone> viewAllClassCode() {
        List<Milestone> list = new ArrayList<>();
        String sql = "select distinct class_code, cl.class_id from class cl\n"
                + "inner join subject sub\n"
                + "on cl.subject_id = sub.subject_id\n"
                + "inner join milestone ms\n"
                + "on cl.class_id = ms.class_id";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Milestone(rs.getString(1), rs.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMilestone.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<Milestone> viewAllIteName() {
        List<Milestone> list = new ArrayList<>();
        String sql = "SELECT distinct ite.iteration_id, iteration_name FROM iteration ite\n"
                + "inner join subject sub\n"
                + "on ite.subject_id = sub.subject_id\n"
                + "inner join milestone ms\n"
                + "on ite.iteration_id = ms.interation_id\n"
                + "order by iteration_name";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Milestone(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public boolean checkExistMilestone(String name) {
        String sql = " select * from milestone where milestone_name like '" + name + "'";
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
    public int addMilestone(Milestone ms) {
        int n = 0;
        String sql = "insert into milestone(interation_id,class_id,from_date,to_date, status,milestone_name)\n"
                + "                values(?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ms.getInteration_id());
            ps.setInt(2, ms.getClass_id());
            ps.setString(3, ms.getFrom_date());
            ps.setString(4, ms.getTo_date());
            ps.setInt(5, ms.getStatus());
            ps.setString(6, ms.getMilestone_name());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    public int countMilestone() {
        String sql = "SELECT count(*) FROM milestone ";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    public List<Milestone> viewAllMilestone1() {
        List<Milestone> list = new ArrayList<>();
        String sql = "SELECT * FROM milestone ms\n"
                + "inner join iteration ite\n"
                + "on ms.interation_id = ite.iteration_id\n"
                + "inner join class cl\n"
                + "on ms.class_id = cl.class_id\n";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Milestone(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(15), rs.getString(10), rs.getString(7)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
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
    public Milestone viewMilestoneByMilIdAndIteIdAndClassId(String classId, String mileId, String iteId) {
        String sql = "SELECT ms.milestone_id, iteration_name, class_code,ms.from_date,ms.to_date,ms.status,ms.interation_id,ms.class_id FROM milestone ms\n"
                + "inner join iteration ite\n"
                + "on ms.interation_id = ite.iteration_id\n"
                + "inner join class cl\n"
                + "on ms.class_id = cl.class_id\n"
                + "where ms.class_id = " + classId + " and ms.milestone_id = " + mileId + " and ms.interation_id = " + iteId;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return new Milestone(rs.getInt(1), rs.getInt(7), rs.getInt(8), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(3), rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public int updateMilestone(Milestone ms) {
        int n = 0;
        String sql = "update milestone\n"
                + "set from_date = ?, to_date = ?, status =?\n"
                + "where milestone_id = ? and interation_id=? and class_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, ms.getFrom_date());
            ps.setString(2, ms.getTo_date());
            ps.setInt(3, ms.getStatus());
            ps.setInt(4, ms.getMilestone_id());
            ps.setInt(5, ms.getInteration_id());
            ps.setInt(6, ms.getClass_id());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMilestone.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    public int deleteMilestone(String mileId, String iteId, String classId) {
        int n = 0;
        String sql = "delete from milestone \n"
                + "where milestone_id = ? and interation_id = ? and class_id = ? ";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, mileId);
            ps.setString(2, iteId);
            ps.setString(3, classId);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    public int updateStatus(String mileId, int status) {
        int n = 0;
        String sql = "update milestone \n "
                + "set status = ? \n "
                + "where milestone_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setString(2, mileId);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }
    public List<Milestone> ListMilestoneByClass(String classId) {
        List<Milestone> list = new ArrayList<>();
        String sql = "SELECT ms.milestone_id, iteration_name, class_code,ms.from_date,ms.to_date,ms.status, ms.interation_id,ms.class_id FROM milestone ms\n"
                + "inner join iteration ite\n"
                + "on ms.interation_id = ite.iteration_id\n"
                + "inner join class cl\n"
                + "on ms.class_id = cl.class_id\n"
                + "where cl.class_id =" + classId;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                Milestone milestone = new Milestone();

                int i = 0;
                milestone.setMilestone_id(rs.getInt(++i));
                milestone.setIterationName(rs.getString(++i));
                milestone.setClassCode(rs.getString(++i));
                milestone.setFrom_date(rs.getString(++i));
                milestone.setTo_date(rs.getString(++i));
                milestone.setStatus(rs.getInt(++i));
                milestone.setInteration_id(rs.getInt(++i));
                milestone.setClass_id(rs.getInt(++i));

                list.add(milestone);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public Milestone classCodeForMile(String classId) {
        String sql = "SELECT distinct cl.class_code, cl.class_id  FROM `swp391-project`.milestone ms\n"
                + "inner join iteration ite\n"
                + "on ms.interation_id = ite.iteration_id\n"
                + "inner join class cl\n"
                + "on ms.class_id = cl.class_id\n"
                + "where cl.class_Id =" + classId;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new Milestone(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public List<Milestone> viewAllMilestone(String pt) {
        List<Milestone> list = new ArrayList<>();
        String sql = "SELECT ms.milestone_id, iteration_name, class_code,ms.from_date,ms.to_date,ms.status, ms.interation_id,ms.class_id FROM milestone ms\n"
                + "inner join iteration ite\n"
                + "on ms.interation_id = ite.iteration_id\n"
                + "inner join class cl\n"
                + "on ms.class_id = cl.class_id\n"
                + "limit 10 offset " + pt;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                Milestone milestone = new Milestone();

                int i = 0;
                milestone.setMilestone_id(rs.getInt(++i));
                milestone.setIterationName(rs.getString(++i));
                milestone.setClassCode(rs.getString(++i));
                milestone.setFrom_date(rs.getString(++i));
                milestone.setTo_date(rs.getString(++i));
                milestone.setStatus(rs.getInt(++i));
                milestone.setInteration_id(rs.getInt(++i));
                milestone.setClass_id(rs.getInt(++i));

                list.add(milestone);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

}
