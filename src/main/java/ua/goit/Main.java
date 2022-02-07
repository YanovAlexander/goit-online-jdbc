package ua.goit;

import ua.goit.config.DatabaseManager;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.config.PropertiesUtil;
import ua.goit.dl.JobsRepository;
import ua.goit.dl.Repository;
import ua.goit.model.converter.JobsConverter;
import ua.goit.model.dao.JobsDao;
import ua.goit.model.dto.JobsDto;
import ua.goit.service.JobsService;

public class Main {
    public static void main(String[] args) {
        PropertiesUtil util = new PropertiesUtil();

        DatabaseManager dbConnector = new PostgresHikariProvider(util.getHostname(), util.getPort(), util.getSchema(),
                util.getUser(), util.getPassword());

        Repository<JobsDao> repository = new JobsRepository(dbConnector);

        JobsConverter converter = new JobsConverter();

        JobsService service = new JobsService(converter, repository);

        JobsDto acAccount = service.findById("AC_ACCOUNT");
        System.out.println(acAccount.getJobId());
        System.out.println(acAccount.getJobTitle());
        System.out.println(acAccount.getMinSalary());
        System.out.println(acAccount.getMaxSalary());

//        JobsDto dto = new JobsDto();
//        dto.setJobId("NEW_JOBs");
//        dto.setJobTitle("NEW JOB TITLE UPDATEDssss");
//        dto.setMinSalary(1000);
//        dto.setMaxSalary(5000);
//
//        service.save(dto);
//        service.remove(dto);
//        final int update = service.update(dto);
//        System.out.println("UPDATED columns count " + update);

        service.findAll()
                .forEach(job -> {
                    System.out.println("--------------------");
                    System.out.println(job.getJobId());
                    System.out.println(job.getJobTitle());
                    System.out.println(job.getMinSalary());
                    System.out.println(job.getMaxSalary());
                    System.out.println("--------------------");
                });
    }
}
