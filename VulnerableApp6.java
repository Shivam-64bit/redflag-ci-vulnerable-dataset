import java.sql.*;

public class VulnerableApp6 {

    // 🔴 VULN 1: Hardcoded secret
    static String DB_PASS = "root123";

    public static void main(String[] args) {
        try {
            String username = args[0];

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "root", DB_PASS
            );

            Statement stmt = conn.createStatement();

            // 🔴 VULN 2: SQL Injection
            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("User: " + rs.getString("username"));
            }

            // 🔴 VULN 3: Missing Authentication
            deleteAllData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void deleteAllData() {
        System.out.println("All data deleted without auth!");
    }
}