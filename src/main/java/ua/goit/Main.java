package ua.goit;

import ua.goit.config.DatabaseManager;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.config.PropertiesUtil;
import ua.goit.dl.JobsRepository;
import ua.goit.dl.Repository;
import ua.goit.model.dao.JobsDao;

public class Main {
    public static void main(String[] args) {
        PropertiesUtil util = new PropertiesUtil();

        DatabaseManager dbConnector = new PostgresHikariProvider(util.getHostname(), util.getPort(), util.getSchema(),
                util.getUser(), util.getPassword());

        Repository<JobsDao> repository = new JobsRepository(dbConnector);

        JobsDao acAccount = repository.findById("AC_ACCOUNT");
        System.out.println(acAccount.getJobId());
        System.out.println(acAccount.getJobTitle());
        System.out.println(acAccount.getMinSalary());
        System.out.println(acAccount.getMaxSalary());

        JobsDao jobsDao = new JobsDao();
        jobsDao.setJobId("NEW_JOB");
        jobsDao.setJobTitle("NEW JOB TITLE UPDATED");
        jobsDao.setMinSalary(1000);
        jobsDao.setMaxSalary(5000);

//        repository.save(jobsDao);
//        repository.remove(jobsDao);
        final int update = repository.update(jobsDao);
        System.out.println("UPDATED columns count " + update);

    }
}
