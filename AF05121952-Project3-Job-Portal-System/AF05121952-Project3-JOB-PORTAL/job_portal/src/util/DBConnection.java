package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String DB_URL   = "jdbc:mysql://localhost:3306/job_db?useSSL=false&serverTimezone=UTC";
    private static final String USER     = "root";
    private static final String PASSWORD = "password";   // ← change this

    private DBConnection() {}

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (Exception e) {
            System.err.println("[DBConnection] Failed to connect: " + e.getMessage());
            return null;
        }
    }
}
