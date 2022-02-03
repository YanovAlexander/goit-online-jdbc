package ua.goit.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresProvider implements DatabaseManager {
    private final String url;
    private final Properties properties;

    public PostgresProvider(String hostname, int port, String databaseName, String username, String password) {
        url = String.format("jdbc:postgresql://%s:%d/%s", hostname, port, databaseName);
        properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, properties);
    }
}
