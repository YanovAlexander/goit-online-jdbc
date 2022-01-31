package ua.goit;

import ua.goit.config.DatabaseManagerConnector;
import ua.goit.config.PropertiesUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        PropertiesUtil util = new PropertiesUtil();
        DatabaseManagerConnector dbConnector = new DatabaseManagerConnector(util.getHostname(), util.getPort(), util.getSchema(),
                util.getUser(), util.getPassword());
        Connection connection = dbConnector.getConnection();
    }
}
