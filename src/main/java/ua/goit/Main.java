package ua.goit;

import ua.goit.config.DatabaseManagerConnector;
import ua.goit.config.PropertiesUtil;
import ua.goit.dl.JobsRepository;
import ua.goit.dl.Repository;
import ua.goit.model.dao.JobsDao;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        PropertiesUtil util = new PropertiesUtil();
        DatabaseManagerConnector dbConnector = new DatabaseManagerConnector(util.getHostname(), util.getPort(), util.getSchema(),
                util.getUser(), util.getPassword());
        Connection connection = dbConnector.getConnection();
        Repository<JobsDao> repository = new JobsRepository(connection);

        JobsDao acAccount = repository.findById("AC_ACCOUNT");

        System.out.println(acAccount.getJobId());
        System.out.println(acAccount.getJobTitle());
        System.out.println(acAccount.getMinSalary());
        System.out.println(acAccount.getMaxSalary());
    }
}
