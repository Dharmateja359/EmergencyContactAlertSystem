package dao;

import model.User;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection conn;

    public UserDAO() {
        conn = DBConnection.getConnection();
    }

    public boolean addUser(User user) {
        String sql = "INSERT INTO users (name, age, blood_group, heart_rate, blood_pressure, emergency_contact) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setString(3, user.getBloodGroup());
            ps.setInt(4, user.getHeartRate());
            ps.setString(5, user.getBloodPressure());
            ps.setString(6, user.getEmergencyContact());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT id, name, age, blood_group, heart_rate, blood_pressure, emergency_contact, " +
                    "DATE_FORMAT(registration_time, '%Y-%m-%d %H:%i:%s') as registration_time FROM users";
        List<User> users = new ArrayList<>();
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("blood_group"),
                        rs.getInt("heart_rate"),
                        rs.getString("blood_pressure"),
                        rs.getString("emergency_contact"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(int id) {
        String sql = "SELECT id, name, age, blood_group, heart_rate, blood_pressure, emergency_contact, " +
                    "DATE_FORMAT(registration_time, '%Y-%m-%d %H:%i:%s') as registration_time FROM users WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("blood_group"),
                        rs.getInt("heart_rate"),
                        rs.getString("blood_pressure"),
                        rs.getString("emergency_contact"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean clearAllUsers() {
        String sql = "DELETE FROM users";
        try (Statement st = conn.createStatement()) {
            int rows = st.executeUpdate(sql);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
