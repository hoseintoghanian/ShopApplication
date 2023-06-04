package com.example.shopapplication;

import java.sql.*;

public class Database {
    public static Connection getDBC() {//get database connection
        Connection connection = null;

        String URL = "jdbc:mysql://localhost:3306/shopapplicationdb";
        String username = "root";
        String password = "5581";

        try {
            connection = DriverManager.getConnection(URL, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}