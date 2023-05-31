package fr.eni.projetenchere.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName(Settings.getProperty("driver"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(
                    Settings.getProperty("url"),
                    Settings.getProperty("user"),
                    Settings.getProperty("password")
            );
        }
        return connection;
    }
}

