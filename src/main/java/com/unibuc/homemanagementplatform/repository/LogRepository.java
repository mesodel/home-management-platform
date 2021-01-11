package com.unibuc.homemanagementplatform.repository;

import com.unibuc.homemanagementplatform.model.LogMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class LogRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(String message) {
        String saveSql = "INSERT INTO log_message(timestamp, message) VALUES (?,?)";
        jdbcTemplate.update(saveSql, LocalDateTime.now(), message);
    }
}
