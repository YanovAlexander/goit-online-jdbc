package ua.goit;

import ua.goit.config.DatabaseManager;
import ua.goit.config.PostgresHikariProvider;
import ua.goit.config.PropertiesUtil;
import ua.goit.dl.DateExampleRepository;
import ua.goit.dl.JobsRepository;
import ua.goit.dl.Repository;
import ua.goit.model.converter.JobsConverter;
import ua.goit.model.dao.DateExampleDao;
import ua.goit.model.dao.JobsDao;
import ua.goit.model.dto.JobsDto;
import ua.goit.service.JobsService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        PropertiesUtil util = new PropertiesUtil();

        DatabaseManager dbConnector = new PostgresHikariProvider(util.getHostname(), util.getPort(), util.getSchema(),
                util.getUser(), util.getPassword());

        Repository<JobsDao> repository = new JobsRepository(dbConnector);

        JobsConverter converter = new JobsConverter();

        JobsService service = new JobsService(converter, repository);

        DateExampleRepository dateExampleRepository = new DateExampleRepository(dbConnector);

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

//        service.findAll()
//                .forEach(job -> {
//                    System.out.println("--------------------");
//                    System.out.println(job.getJobId());
//                    System.out.println(job.getJobTitle());
//                    System.out.println(job.getMinSalary());
//                    System.out.println(job.getMaxSalary());
//                    System.out.println("--------------------");
//                });


        DateExampleDao dao = new DateExampleDao();
        dao.setDateExample(ZonedDateTime.now().toInstant());
        dateExampleRepository.save(dao);
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Kiev"));

        Optional<DateExampleDao> byId = dateExampleRepository.findById(1);

        final DateExampleDao dateExampleDao = byId.get();

        final LocalDateTime localDateTime = LocalDateTime.ofInstant(dateExampleDao.getDateExample(), ZoneId.of("Europe/Kiev"));

        System.out.println("DATE EXAMPLE");
        System.out.println(dateExampleDao.getId());
        System.out.println(dateExampleDao.getDateExample());
        System.out.println("LOCAL TIME " + localDateTime);
    }
}
