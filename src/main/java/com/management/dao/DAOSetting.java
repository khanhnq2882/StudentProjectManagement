package com.management.dao;

import com.management.connectdb.ConnectJDBC;
import com.management.entity.Setting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOSetting extends ConnectJDBC {

    Connection conn = ConnectJDBC.getConnection();

    ResultSet rs = null;
    PreparedStatement ps = null;

    public ArrayList<String> viewAllType() {
        ArrayList<String> list = new ArrayList<>();
        String sql = "select distinct type_id from setting";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                String type = rs.getString(1);
                list.add(type);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<Setting> viewType() {
        List<Setting> list = new ArrayList<>();
        String sql = "select distinct type_id from setting\n"
                + "order by type_id";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                list.add(new Setting(rs.getInt(1)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public int addSetting(Setting st) {
        int n = 0;
        String sql = "insert into setting(type_id, setting_title, setting_value, display_order,status,note)\n"
                + "values(?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, st.getType_id());
            ps.setString(2, st.getSetting_title());
            ps.setString(3, st.getSetting_value());
            ps.setString(4, st.getDisplay_order());
            ps.setInt(5, st.getStatus());
            ps.setString(6, st.getNote());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }
    public List<Setting> viewTypeId() {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECt distinct type_id FROM setting order by type_id ";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Setting(rs.getInt(1)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public List<Setting> viewSettingList() {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECt * FROM setting";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Setting(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public List<Setting> viewSettingType(String settingType) { // search by name
        List<Setting> list = new ArrayList<>();
        String sql = "SELECt * FROM setting\n"
                + "where setting_title like '%" + settingType + "%';";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Setting(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public List<Setting> searchSetting(int typeId, String title, int status) {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECt * FROM setting \n"
                + "where type_id=" + typeId + " and setting_title like '%" + title + "%' and status= " + status + ";";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Setting(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public int updateStatus(int status, int setting_id) {
        int n = 0;
        try {

            String sql = "update setting\n"
                    + " set status = ? where setting_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, setting_id);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }
    public int deleteSetting(String settingId, String typeId) {
        int n = 0;
        String sql = "delete from setting \n"
                + "where setting_id = ? and type_id = ? ";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, settingId);
            ps.setString(2, typeId);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

}
