package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection con = Util.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS users (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(60),
                        lastName VARCHAR(60),
                        age TINYINT)
                """);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection con = Util.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection con = Util.getConnection();
             PreparedStatement pstmt = con.prepareStatement(
                 "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)"
             )) {
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection con = Util.getConnection();
             PreparedStatement pstmt = con.prepareStatement("DELETE FROM users WHERE id = ?")) {
            pstmt.setLong(1, id);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Connection con = Util.getConnection();
             Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery("SELECT * FROM users")) {

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void cleanUsersTable() {
        try (Connection con = Util.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute("TRUNCATE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
