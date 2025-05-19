package util;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    
    public static void initializeDatabase() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            
            // Create database if not exists
            stmt.execute("CREATE DATABASE IF NOT EXISTS EmergencyAlertDB");
            stmt.execute("USE EmergencyAlertDB");
            
            // Create users table if not exists
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "age INT NOT NULL," +
                "blood_group VARCHAR(3) NOT NULL," +
                "heart_rate INT NOT NULL," +
                "blood_pressure VARCHAR(10) NOT NULL," +
                "emergency_contact VARCHAR(10) NOT NULL," +
                "registration_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
            
            stmt.execute(createTableSQL);
            stmt.close();
            
            System.out.println("Database and table initialized successfully!");
        } catch (Exception e) {
            System.out.println("Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 