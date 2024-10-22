package com.example.mysqlapplication.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {

    private static final Logger logger = Logger.getLogger(DatabaseManager.class.getName());
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/mobile_fpt_data?autoReconnect=true&useSSL=false&logger=com.mysql.cj.log.StandardLogger&profileSQL=true";
    private static final String USER = "user_demo";
    private static final String PASSWORD = "abc123admin";

    // Kết nối tới cơ sở dữ liệu
    private Connection connect() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            logger.info("Driver loaded successfully");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "MySQL Driver not found", e);
            throw e; // Try catch để xử lý ở các phương thức khác (nếu cần xử lý)
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error connecting to the database", e);
            throw e; // Try catch để xử lý ở các phương thức khác (nếu cần xử lý)
        }
    }

    // Đăng nhập người dùng
    public boolean login(String id, String password) {
        String query = "SELECT * FROM KH_tbl WHERE ID_kh = ? AND pass_kh = ?";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, id); // ID_kh
            statement.setString(2, password); // pass_kh

            logger.info("Trying to login with ID: " + id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("User found: " + id);
                    return true;
                } else {
                    logger.warning("Login failed: User not found for ID: " + id);
                    return false;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during login for ID: " + id, e);
            return false;
        }
    }

    // Đăng ký người dùng
    public boolean register(String id, String name, String password) {
        String query = "INSERT INTO KH_tbl (ID_kh, ten_kh, pass_kh) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, password);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("User registered successfully with ID: " + id);
                return true;
            } else {
                logger.warning("User registration failed for ID: " + id);
                return false;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during registration for ID: " + id, e);
            return false;
        }
    }

    // Kiểm tra người dùng có tồn tại
    public boolean isUserExists(String id) {
        String query = "SELECT * FROM KH_tbl WHERE ID_kh = ?";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean userExists = resultSet.next();
                logger.info("User existence check for ID: " + id + " - Exists: " + userExists);
                return userExists;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error checking user existence for ID: " + id, e);
            return false;
        }
    }
}
