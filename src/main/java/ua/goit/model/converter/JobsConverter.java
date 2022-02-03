package ua.goit.model.converter;

import ua.goit.model.dao.JobsDao;
import ua.goit.model.dto.JobsDto;

public class JobsConverter {

    public JobsDto convert(JobsDao jobsDao) {
        JobsDto dto = new JobsDto();
        dto.setJobId(jobsDao.getJobId());
        dto.setJobTitle(jobsDao.getJobTitle());
        dto.setMinSalary(jobsDao.getMinSalary());
        dto.setMaxSalary(jobsDao.getMaxSalary());
        return dto;
    }

    public JobsDao convert(JobsDto jobsDto) {
        JobsDao dao = new JobsDao();
        dao.setJobId(jobsDto.getJobId());
        dao.setJobTitle(jobsDto.getJobTitle());
        dao.setMinSalary(jobsDto.getMinSalary());
        dao.setMaxSalary(jobsDto.getMaxSalary());
        return dao;
    }
}
