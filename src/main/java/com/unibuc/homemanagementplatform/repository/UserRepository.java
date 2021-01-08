package com.unibuc.homemanagementplatform.repository;

import com.unibuc.homemanagementplatform.mapper.UserMapperGet;
import com.unibuc.homemanagementplatform.model.Family;
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

        List<User> users = jdbcTemplate.query(getSql,mapper,familyId);

        return users;
    }

    public User save(User user) {
        String createSql = "INSERT INTO user (family_id,email, name, password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(createSql,user.getFamily().getFamilyId(),user.getUserEmail(),user.getName(),user.getPassword());

        return user;
    }
}
