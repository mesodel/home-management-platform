package com.unibuc.homemanagementplatform.repository;

import com.unibuc.homemanagementplatform.model.Status;
import com.unibuc.homemanagementplatform.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StatusRepository statusRepository;

    public List<Task> getTasksOfUser(String email) {
        String getSql = "SELECT * FROM task, user_task WHERE task.id=user_task.task_id AND user_task.user_email=?";

        RowMapper<Task> rowMapper = (resultSet, i) -> {
            Task task = new Task();
            task.setDescription(resultSet.getString("description"));
            task.setName(resultSet.getString("name"));
            task.setTaskId(resultSet.getLong("id"));
            task.setDueBy(resultSet.getDate("due_by"));
            task.setStatus(statusRepository.getById(resultSet.getLong("status_id")));

            return task;
        };

        return jdbcTemplate.query(getSql, rowMapper, email);
    }
}
