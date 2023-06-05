package fr.eni.projetenchere.dal;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvider {
    private static DataSource ds;

    static {
        try {
            // Initialize JNDI context
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");

            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ENCHERE_DB");
        } catch (NamingException e) {
            System.out.println("Vérifiez la table sur laquelle vous agissez !");
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}

