import java.sql.*;
import java.io.*;

public class VulnerableApp1 {

    // 🔴 VULN 1: Hardcoded credentials
    static String DB_URL = "jdbc:mysql://localhost:3306/testdb";
    static String USER = "admin";
    static String PASS = "password123"; // Hardcoded secret

    public static void main(String[] args) {
        try {
            String username = args[0]; // user input
            String fileName = args[1]; // user input

            // 🔴 VULN 2: SQL Injection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("User found: " + rs.getString("username"));
            }

            // 🔴 VULN 3: Command Injection
            Runtime.getRuntime().exec("notepad " + fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

