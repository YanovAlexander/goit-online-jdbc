package ua.goit.dl;

import ua.goit.model.dao.JobsDao;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    Optional<T> findById (String id);

    List<JobsDao> findAll();

    void save(T t);

    void remove(T t);

    int update(T t);
}
