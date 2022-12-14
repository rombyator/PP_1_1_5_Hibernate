package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/pre_project_1",
                "admin",
                "123"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
