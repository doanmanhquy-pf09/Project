package dev.project.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static Connection connection;
    private static String url = "jdbc:mysql://localhost:3306/comic";
    private static String user = "root";
    private static String password = "24112001";

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
}