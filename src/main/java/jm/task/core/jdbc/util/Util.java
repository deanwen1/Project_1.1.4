package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private final String URL = "jdbc:mysql://localhost:3306/jm?useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "yfcnhjqrb1";

    private Connection connection;

    public Util() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
