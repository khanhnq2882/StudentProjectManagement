package com.management.connectdb;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConnectJDBC {

    Connection connection = getConnection();

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try {
            Statement stm = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public static Connection getConnection() {
        Connection connection = null;
        Properties properties = new Properties();
        try {
            properties.load(ConnectJDBC.class.getResourceAsStream("/properties/DBConfig.properties"));

            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String userName = properties.getProperty("userName");
            String password = properties.getProperty("password");

            Class.forName(driver);

            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        getConnection();
    }
}
