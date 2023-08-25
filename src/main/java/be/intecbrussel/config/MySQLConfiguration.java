package be.intecbrussel.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConfiguration {
    public static Connection getConnection() {
        String user = "Walid";
        String passw = "password";
        String url = "localhost";
        String port = "3306";
        String database = "accountapp";

        String connectionString = String.format("jdbc:mysql://%s:%s/%s",
                url, port, database);

        Properties connectionProperties = new Properties();
        connectionProperties.put("user", user);
        connectionProperties.put("password", passw);

        Connection connection;

        try {
            connection = DriverManager.getConnection(connectionString, connectionProperties);
        } catch (SQLException e) {
            System.err.println("Could not connect to DB");
            throw new RuntimeException(e);
        }

        return connection;
    }
}
