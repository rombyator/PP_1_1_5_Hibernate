package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final String table = "users";
    private final Connection con;

    public UserDaoJDBCImpl() {
        this.con = Util.getConnection();
    }


    public void createUsersTable() {
        String sql = String.format("""
            CREATE TABLE IF NOT EXISTS %s (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(60),
                lastName VARCHAR(60),
                age TINYINT
            )""", table);

        try (Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            logError(e);
        }
    }

    public void dropUsersTable() {
        String sql = String.format("DROP TABLE IF EXISTS %s", table);

        try (Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            logError(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = String.format(
            "INSERT INTO %s (name, lastName, age) VALUES (?, ?, ?)",
            table
        );

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);
            pstmt.execute();
        } catch (SQLException e) {
            logError(e);
        }
    }

    public void removeUserById(long id) {
        String sql = String.format("DELETE FROM %s WHERE id = ?", table);

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.execute();
        } catch (SQLException e) {
            logError(e);
        }
    }

    public List<User> getAllUsers() {
        String sql = String.format("SELECT * FROM %s", table);

        try (Statement stmt = con.createStatement()) {
            try (ResultSet resultSet = stmt.executeQuery(sql)) {
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
            }
        } catch (SQLException e) {
            logError(e);
        }

        return null;
    }

    public void cleanUsersTable() {
        String sql = String.format("TRUNCATE %s", table);

        try (Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            logError(e);
        }
    }

    private void logError(Exception e) {
        System.out.println("Error occurred while executing sql: " + e.getMessage());
    }
}
