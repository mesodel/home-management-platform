package com.unibuc.homemanagementplatform.repository;

import com.unibuc.homemanagementplatform.dto.UserRequestTaskCreate;
import com.unibuc.homemanagementplatform.model.Status;
import com.unibuc.homemanagementplatform.model.Task;
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

    public Task getTask(Long id) {
        String selectSql = "SELECT * from task where id = ?";
        RowMapper<Task> rowMapper = (resultSet, i) -> {
          Task task = new Task();
          task.setTaskId(resultSet.getLong("id"));
          task.setName(resultSet.getString("name"));
          task.setDescription(resultSet.getString("description"));
          task.setDueBy(resultSet.getDate("due_by"));
          Status status = statusRepository.getById(resultSet.getLong("status_id"));
          task.setStatus(status);

          return task;
        };
       List<Task> tasks = jdbcTemplate.query(selectSql,rowMapper,id);
       return tasks.get(0);
    }

    public Task update(Long id, String description) {
        String updateSql = "UPDATE task set description = ? where id = ?";
        jdbcTemplate.update(updateSql,description,id);

        return getTask(id);
    }

    public boolean remove(Long id) {
        String deleteUserTaskSql = "DELETE from user_task where user_task.task_id = ?";
        jdbcTemplate.update(deleteUserTaskSql,id);
        String deleteTaskSql = "DELETE from task where task.id = ?";
        int noRowsAffected = jdbcTemplate.update(deleteTaskSql,id);
        return noRowsAffected == 1;


    }

    public Task updateStatus(Long id, Status status) {
        String updateSql = "UPDATE task set status_id = ? where id = ?";
        jdbcTemplate.update(updateSql,statusRepository.getOrInsert(status.getStatusValue()).getStatusId(),id);

        return getTask(id);
    }
}
