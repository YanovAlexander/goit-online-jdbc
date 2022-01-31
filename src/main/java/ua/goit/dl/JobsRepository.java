package ua.goit.dl;

import ua.goit.model.dao.JobsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobsRepository implements Repository<JobsDao> {

    private final Connection connection;

    private static final String FIND_BY_ID = "SELECT * FROM jobs j WHERE j.job_id = ? ;";

    public JobsRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public JobsDao findById(String id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToJobsDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(JobsDao o) {

    }

    @Override
    public void remove(JobsDao o) {

    }

    @Override
    public JobsDao update(JobsDao o) {
        return null;
    }

    private JobsDao mapToJobsDao(ResultSet resultSet) throws SQLException {
        JobsDao jobsDao = new JobsDao();
        while (resultSet.next()) {
            jobsDao.setJobId(resultSet.getString("job_id"));
            jobsDao.setJobTitle(resultSet.getString("job_title"));
            jobsDao.setMinSalary(resultSet.getInt("min_salary"));
            jobsDao.setMaxSalary(resultSet.getInt("max_salary"));
        }
        return jobsDao;
    }
}
