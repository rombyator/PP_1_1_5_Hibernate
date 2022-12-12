package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String user = "admin";
    private static final String password = "123";
    private static final String db = "pre_project_1";
    private static final String host = "localhost";
    private static final int port = 3306;

    public static Connection getConnection() {
        String url = String.format("jdbc:mysql://%s:%d/%s", host, port, db);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error connection to db: " + e.getMessage());
            System.exit(-1);
        }

        if (connection == null) {
            System.out.println("Can't connect to db");
            System.exit(-1);
        }

        return connection;
    }
}
