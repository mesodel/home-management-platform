package com.unibuc.homemanagementplatform.repository;

import com.unibuc.homemanagementplatform.model.Family;
import com.unibuc.homemanagementplatform.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class User_Task_Repository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getUsers(Long taskId) {
        String getSql = "SELECT * from user_task WHERE task_id = ?";

        RowMapper<User> mapper = (resultSet, rowNum) -> {
            User user = new User();
            user.setUserEmail(resultSet.getString("user_email"));

            return user;
        };

        List<User> users = jdbcTemplate.query(getSql, mapper, taskId);

        return users;
    }
}
