package fr.eni.projetenchere.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProviderDebug {
    private static final String DB_URL = "jdbc:sqlserver://pixdev.ovh:1433;databaseName=ENCHERE_DB;encrypt=false;trustServerCertificate=true";
    private static final String DB_USERNAME = "PLANB";
    private static final String DB_PASSWORD = "PLANBPLANB";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}
