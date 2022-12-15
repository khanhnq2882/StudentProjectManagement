package com.management.dao;

import com.management.connectdb.ConnectJDBC;
import com.management.entity.Iteration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DAOIteration extends ConnectJDBC {

    Connection conn = ConnectJDBC.getConnection();

    ResultSet rs = null;
    PreparedStatement ps = null;

    public int addIteration(Iteration it) {
        int n = 0;
        String sql = "insert into iteration(subject_id, iteration_name, duration, status, notes)\n"
                + "values(?, ?, ?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, it.getSubject_id());
            ps.setString(2, it.getIteration_name());
            ps.setString(3, it.getDuration());
            ps.setInt(4, it.getStatus());
            ps.setString(5, it.getNote());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

        public List<Iteration> viewIteration(String pt) {
            List<Iteration> list = new ArrayList<>();
            String sql = "SELECT * FROM iteration it inner join subject sub \n"
                    + "on it.subject_id = sub.subject_id\n"
                    + "order by it.iteration_id\n"
                    + "LIMIT 10 OFFSET " + pt;
            ResultSet rs = getData(sql);
            try {
                while (rs.next()) {
                    list.add(new Iteration(rs.getInt(1), rs.getInt(2), rs.getString(8), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return list;
        }

    public int updateIteration(Iteration it) {
        int n = 0;
        String sql = "update iteration \n"
                + "set iteration_name = ?, duration = ?, status = ?, notes = ?\n"
                + "where iteration_id = ? and subject_id = ?";
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, it.getIteration_name());
            ps.setString(2, it.getDuration());
            ps.setInt(3, it.getStatus());
            ps.setString(4, it.getNote());
            ps.setInt(5, it.getIteration_id());
            ps.setInt(6, it.getSubject_id());

            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public List<Iteration> searchByID() {
        List<Iteration> list = new ArrayList<>();
        String sql = "SELECT distinct it.subject_id, sub.subject_code FROM iteration it\n"
                + "inner join subject sub \n"
                + "on it.subject_id = sub.subject_id\n"
                + "order by subject_id ";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Iteration(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Iteration> searchByID1(String id) {
        List<Iteration> list = new ArrayList<>();
        String sql = "SELECT * FROM iteration it inner join subject sub\n"
                + "on it.subject_id = sub.subject_id\n"
                + "where it.subject_id = " + id + "\n"
                + "order by it.subject_id";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Iteration(rs.getInt(1), rs.getInt(2), rs.getString(8), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Iteration> searchByIteName(String name) {
        List<Iteration> list = new ArrayList<>();
        String sql = "SELECT * FROM iteration it inner join subject sub \n"
                + "on it.subject_id = sub.subject_id\n"
                + "where iteration_name like '%" + name + "%'"
                + "order by it.iteration_id\n";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Iteration(rs.getInt(1), rs.getInt(2), rs.getString(8), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Iteration> viewSubCode() {
        List<Iteration> list = new ArrayList<>();
        String sql = "SELECT distinct it.subject_id, subject_code FROM subject sub inner join iteration it on sub.subject_id = it.subject_id";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Iteration(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Iteration> searchIteration(String subId, String name) {
        List<Iteration> list = new ArrayList<>();
        String sql = "SELECT * FROM iteration it inner join subject sub \n"
                + "on it.subject_id = sub.subject_id\n"
                + "where it.subject_id = " + subId + " and it.iteration_name like '%" + name + "%'"
                + "order by it.iteration_id\n";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Iteration(rs.getInt(1), rs.getInt(2), rs.getString(8), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public Iteration updateByIteIdAndSubId(String iteId, String subId) {
        String sql = "SELECT  * FROM iteration i inner join subject s on i.subject_id = s.subject_id\n"
                + "where i.iteration_id = " + iteId + " and s.subject_id = " + subId;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return new Iteration(rs.getInt(1), rs.getInt(2), rs.getString(8), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int deleteIteration(String iteId, String subId) {
        int n = 0;
        String sql = "delete from `swp391-project`.iteration \n"
                + "where iteration_id = ? and subject_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, iteId);
            ps.setString(2, subId);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateStatus(String iteId, String status) {
        int n = 0;
        String sql = "update iteration\n"
                + "set status = ? where iteration_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,status);
            ps.setString(2, iteId);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }       
        return n;
    }

    public int countIte() {
        String sql = "SELECT count(*) FROM iteration";
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
    
    public boolean checkExistIte(String name) {
        String sql = " select * from Iteration where iteration_name like '" + name + "'";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        DAOIteration dao = new DAOIteration();
//        Iteration ite = dao.updateByIteIdAndSubId("3", "1");
//        System.out.println(ite);
//        int n = dao.updateIteration(new Iteration(1, 1, "11", "11", 2));
//        if (n > 0) {
//            System.out.println("chay dc");
//        }
        List<Iteration> list = dao.searchIteration("1", "a");
        for (Iteration temp : list) {
            System.out.println(temp);
        }
    }
}
