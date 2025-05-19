package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.File;

public class DBConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            try {
                if (connection.isValid(1)) {
                    return connection;
                }
            } catch (SQLException e) {
                System.out.println("Existing connection is invalid. Creating new connection...");
            }
        }

        try {
            File propertiesFile = new File("src/resources/db.properties");
            if (!propertiesFile.exists()) {
                throw new RuntimeException("Database properties file not found at: " + propertiesFile.getAbsolutePath());
            }

            FileInputStream input = new FileInputStream(propertiesFile);
            Properties props = new Properties();
            props.load(input);
            input.close();

            String driver = props.getProperty("jdbc.driver");
            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");

            if (driver == null || url == null || username == null || password == null) {
                throw new RuntimeException("Missing database configuration properties");
            }

            System.out.println("Connecting to database: " + url);
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection successful!");

        } catch (ClassNotFoundException e) {
            System.err.println("Error: MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: Failed to connect to database!");
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}
