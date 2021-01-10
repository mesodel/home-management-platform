package com.unibuc.homemanagementplatform.repository;

import com.unibuc.homemanagementplatform.mapper.UserMapperGet;
import com.unibuc.homemanagementplatform.model.Family;
import com.unibuc.homemanagementplatform.model.Task;
import com.unibuc.homemanagementplatform.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserMapperGet userMapperGet;

    @Autowired
    private TaskRepository taskRepository;

    public List<User> getUsers(Long familyId) {
        String getSql = "SELECT * from user, family WHERE family.id = user.family_id and user.family_id = ?";

        RowMapper<User> mapper = (resultSet, rowNum) -> {
            User user = new User();
            user.setName(resultSet.getString("user.name"));
            user.setUserEmail(resultSet.getString("user.email"));

            Family family = new Family();
            family.setFamilyName(resultSet.getString("family.name"));

            user.setFamily(family);
            return user;
        };

        List<User> users = jdbcTemplate.query(getSql, mapper, familyId);

        return users;
    }



    public User save(User user) {
        String createSql = "INSERT INTO user (family_id,email, name, password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(createSql, user.getFamily().getFamilyId(), user.getUserEmail(), user.getName(), user.getPassword());

        return user;
    }

    public User getOne(String email) {
        // get the tasks of an user
        String getSql = "SELECT * FROM user, user_task WHERE user.email=user_task.user_email AND user_task.user_email=?";

        RowMapper<User> rowMapper = (resultSet, i) -> {
            User user = new User();
            user.setUserEmail(resultSet.getString("email"));
            user.setName(resultSet.getString("name"));

            Family family = new Family();
            family.setFamilyId(resultSet.getLong("family_id"));
            user.setFamily(family);

            return user;
        };

        return jdbcTemplate.query(getSql, rowMapper, email).get(0);
    }

    public boolean delete(String email) {
        String selectTaskSql = "SELECT * from user_task where user_task.user_email = ?";

        RowMapper<Task> rowMapper = (resultSet, rowNo) -> {
            Task task = new Task();
            task.setTaskId(resultSet.getLong("task_id"));
            return task;
        };
        List<Task> tasks = jdbcTemplate.query(selectTaskSql,rowMapper,email);

        String deleteUserTaskSql = "DELETE from user_task where user_task.user_email = ?";
        jdbcTemplate.update(deleteUserTaskSql,email);
        String deleteUserSql = "DELETE from user where email = ?";
        int noRowsAffected = jdbcTemplate.update(deleteUserSql,email);

        for(Task t : tasks) {
            String deleteTaskSql = "DELETE from task where id = ?";
            jdbcTemplate.update(deleteTaskSql,t.getTaskId());
        }
        return noRowsAffected == 1;
    }
}
