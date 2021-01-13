package com.unibuc.homemanagementplatform.repository;

import com.unibuc.homemanagementplatform.model.Status;
import com.unibuc.homemanagementplatform.model.StatusValue;
import com.unibuc.homemanagementplatform.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatusRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private LogRepository logRepository;

    public Status getById(long id) {
        String selectSql = "SELECT * FROM status where id = ?";

        RowMapper<Status> rowMapper = (resultSet, i) -> {
            StatusValue statusValue = StatusValue.valueOf(resultSet.getString("value"));
            return new Status(
                    resultSet.getLong("id"), statusValue);
        };


        List<Status> statuses = jdbcTemplate.query(selectSql, rowMapper, id);

        return statuses.isEmpty() ? new Status() : statuses.get(0);
    }

    public Status getByValue(StatusValue value) {
        String selectSql = "SELECT * from status where value = ?";
        RowMapper<Status> rowMapper = (resultSet, i) -> {
            Status status = new Status();
            status.setStatusId(resultSet.getLong("id"));
            status.setStatusValue(value);
            return status;
        };
        List<Status> statuses = jdbcTemplate.query(selectSql, rowMapper, value.toString());
        return statuses.isEmpty() ? null : statuses.get(0);
    }

    public Status getOrInsert(StatusValue statusValue) {
        Status status = getByValue(statusValue);
        if (status != null) {
            return status;
        } else {
            String saveSql = "INSERT INTO status (value) VALUES (?)";
            jdbcTemplate.update(saveSql, statusValue.toString());

            Status insertedStatus = getByValue(statusValue);

            logRepository.save(insertedStatus.toString() + " has been inserted into DB");

            return insertedStatus;
        }
    }


}
