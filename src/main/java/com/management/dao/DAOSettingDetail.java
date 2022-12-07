package com.management.dao;

import com.management.connectdb.ConnectJDBC;
import com.management.entity.Setting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOSettingDetail extends ConnectJDBC {

    Connection conn = ConnectJDBC.getConnection();

    ResultSet rs = null;
    PreparedStatement ps = null;

    public void editSetting(int setting_id, int type_id, String setting_title, String setting_value, String display_order, int status, String note) {

        String sql = "update setting set type_id = ? , setting_title =?,setting_value=?, display_order=?, status=?, note=?\n"
                + "where setting_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, type_id);
            ps.setString(2, setting_title);
            ps.setString(3, setting_value);
            ps.setString(4, display_order);
            ps.setInt(5, status);
            ps.setString(6, note);
            ps.setInt(7, setting_id);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

    public Setting SearchSetID(String s) {
        List<Setting> list = new ArrayList<>();
        String sql = "select * from setting where setting_id = " + s;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Setting(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        DAOSettingDetail dao = new DAOSettingDetail();
        System.out.print(dao.viewType());
    }

}

