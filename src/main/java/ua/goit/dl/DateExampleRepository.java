package ua.goit.dl;

import ua.goit.config.DatabaseManager;
import ua.goit.model.dao.DateExampleDao;
import ua.goit.model.dao.JobsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class DateExampleRepository implements Repository<DateExampleDao>, DateExampleCustomRepository<DateExampleDao> {

    private static final String INSERT = "INSERT INTO date_example (date_example) VALUES (?)";
    private static final String FIND_BY_ID = "SELECT * FROM date_example de WHERE de.id = ? ;";



    private final DatabaseManager connector;

    public DateExampleRepository(DatabaseManager connector) {
        this.connector = connector;
    }


    @Override
    public Optional<DateExampleDao> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<DateExampleDao> findById(Integer id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToJobsDao(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<JobsDao> findAll() {
        return null;
    }

    @Override
    public void save(DateExampleDao dateExampleDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setLong(1, dateExampleDao.getDateExample().toEpochMilli());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(DateExampleDao dateExampleDao) {

    }

    @Override
    public int update(DateExampleDao dateExampleDao) {
        return 0;
    }

    private Optional<DateExampleDao> mapToJobsDao(ResultSet resultSet) throws SQLException {
        DateExampleDao dateExampleDao = null;
        while (resultSet.next()) {
            dateExampleDao = new DateExampleDao();
            dateExampleDao.setId(resultSet.getInt("id"));
            dateExampleDao.setDateExample(Instant.ofEpochMilli(resultSet.getLong("date_example")));
        }
        return Optional.ofNullable(dateExampleDao);
    }
}
