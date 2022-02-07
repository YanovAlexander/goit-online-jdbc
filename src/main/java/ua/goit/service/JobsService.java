package ua.goit.service;

import ua.goit.dl.Repository;
import ua.goit.model.converter.JobsConverter;
import ua.goit.model.dao.JobsDao;
import ua.goit.model.dto.JobsDto;

import java.util.List;
import java.util.stream.Collectors;

public class JobsService {
    private final JobsConverter converter;
    private final Repository<JobsDao> repository;

    public JobsService(JobsConverter converter, Repository<JobsDao> repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public JobsDto findById(String id) {
        return converter.convert(repository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("User with id %s not found", id))));
    }

    public List<JobsDto> findAll() {
        return repository.findAll().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public void save(JobsDto dto) {
        repository.findById(dto.getJobId())
                .ifPresent(job -> {
                    throw new IllegalArgumentException(String.format("Job with id %s already exists", job.getJobId()));
                });

        repository.save(converter.convert(dto));
    }

    public void remove(JobsDto dto) {
        repository.remove(converter.convert(dto));
    }

    public int update(JobsDto dto) {
        return repository.update(converter.convert(dto));
    }
}
