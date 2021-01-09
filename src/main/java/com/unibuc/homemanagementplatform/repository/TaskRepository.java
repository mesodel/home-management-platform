package com.unibuc.homemanagementplatform.repository;

import com.unibuc.homemanagementplatform.dto.UserRequestTaskCreate;
import com.unibuc.homemanagementplatform.model.Status;
import com.unibuc.homemanagementplatform.model.StatusValue;
import com.unibuc.homemanagementplatform.model.Task;
import com.unibuc.homemanagementplatform.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

    public Task getLastTaskCreated() {
        String getLastTaskSql = "SELECT * from task order by id desc";

        RowMapper<Task> rowMapper = (resultSet, rowNo) -> {
            Task task = new Task();
            task.setTaskId(resultSet.getLong("id"));
            return  task;
        };
        return jdbcTemplate.query(getLastTaskSql,rowMapper).get(0);
    }

    public Task save(Task t) {
        String saveSql = "INSERT INTO task (id, name, description, due_by, status_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(saveSql,t.getTaskId(), t.getName(),t.getDescription(),t.getDueBy(), 1);


        for(UserRequestTaskCreate u : t.getUsers()) {
            String saveAssignedUsers = "INSERT INTO user_task (created_on, user_email, task_id) VALUES (?, ?, ?)";
            jdbcTemplate.update(saveAssignedUsers, LocalDateTime.now(),u.getUserEmail(),getLastTaskCreated().getTaskId());
        }

        return t;

    }
}
