package com.unibuc.homemanagementplatform.repository;

import com.unibuc.homemanagementplatform.model.Status;
import com.unibuc.homemanagementplatform.model.StatusValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatusRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Status getById(long id) {
        String selectSql = "SELECT * FROM status where id = ?";

        RowMapper<Status> rowMapper = (resultSet, i) -> {
            StatusValue statusValue = StatusValue.valueOf(resultSet.getString("value"));
            return new Status(
                    resultSet.getLong("id"), statusValue);
        };


        List<Status> statuses = jdbcTemplate.query(selectSql, rowMapper, id);

        return statuses.get(0);
    }

    ;
}
