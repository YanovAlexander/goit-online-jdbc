package ua.goit.config;

import java.sql.Connection;
import java.sql.SQLException;

public class OracleProvider implements DatabaseManager{
    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }
}
