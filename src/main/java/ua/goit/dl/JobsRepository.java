package ua.goit.dl;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.JobsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class JobsRepository implements Repository<JobsDao> {

    private final DatabaseManager connector;
    private static final String FIND_BY_ID = "SELECT * FROM jobs j WHERE j.job_id = ? ;";
    private static final String INSERT = "INSERT INTO jobs (job_id, job_title, min_salary, max_salary) VALUES (?, ?, ?, ?)";
    private static final String REMOVE_BY_ID = "DELETE FROM jobs WHERE job_id = ?";
    private static final String UPDATE = "UPDATE jobs SET job_title = ?, min_salary = ?, max_salary = ? WHERE job_id = ?";

    public JobsRepository(DatabaseManager connector) {
        this.connector = connector;
    }

    @Override
    public JobsDao findById(String id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToJobsDao(resultSet).orElseThrow(() -> new IllegalArgumentException(String.format("User with id %s not found", id)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(JobsDao job) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, job.getJobId());
            preparedStatement.setString(2, job.getJobTitle());
            preparedStatement.setInt(3, job.getMinSalary());
            preparedStatement.setInt(4, job.getMaxSalary());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(JobsDao o) {
        try (Connection connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BY_ID)){
            preparedStatement.setString(1, o.getJobId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int update(JobsDao o) {
        int columnsUpdated = 0;
        try(Connection connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, o.getJobTitle());
            preparedStatement.setInt(2, o.getMinSalary());
            preparedStatement.setInt(3, o.getMaxSalary());
            preparedStatement.setString(4, o.getJobId());
            columnsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnsUpdated;
    }

    private Optional<JobsDao> mapToJobsDao(ResultSet resultSet) throws SQLException {
        JobsDao jobsDao = null;
        while (resultSet.next()) {
            jobsDao = new JobsDao();
            jobsDao.setJobId(resultSet.getString("job_id"));
            jobsDao.setJobTitle(resultSet.getString("job_title"));
            jobsDao.setMinSalary(resultSet.getInt("min_salary"));
            jobsDao.setMaxSalary(resultSet.getInt("max_salary"));
        }
        return Optional.ofNullable(jobsDao);
    }
}
