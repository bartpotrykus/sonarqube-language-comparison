import java.sql.*;
import java.io.*;

public class VulnerableApp {

    // Podatność 2: Hardcoded credentials
    private static final String DB_PASSWORD = "admin123";
    private static final String API_KEY = "sk-prod-abc123xyz789secret";
    private static final String DB_URL = "jdbc:mysql://localhost/mydb";

    // Podatność 1: SQL Injection
    public static void getUser(String username) throws Exception {
        Connection conn = DriverManager.getConnection(DB_URL, "root", DB_PASSWORD);
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM users WHERE username = '" + username + "'";
        ResultSet rs = stmt.executeQuery(query);
    }

    // Podatność 3: Weak hashing (MD5)
    public static String hashPassword(String password) throws Exception {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(password.getBytes());
        return new String(hash);
    }

    // Podatność 4: Command injection
    public static void pingHost(String host) throws Exception {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("ping " + host);
    }

    public static void main(String[] args) throws Exception {
        getUser("admin");
        hashPassword("secret");
        pingHost("google.com");
    }
}