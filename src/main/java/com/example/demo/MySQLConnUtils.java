package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {
    public static Connection getMySQL() throws SQLException,
            ClassNotFoundException {
        String hostName = "127.0.0.1";

        String dbName = "mydb";
        String userName = "root";
        String password = "";

        return getMySQLConnection(hostName, dbName, userName, password);
    }
    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException,
            ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");


        String connectionString = "jdbc:mysql://localhost:3306/mydb";

        Connection conn = DriverManager.getConnection(connectionString, userName, password);
        return conn;
    }
}
