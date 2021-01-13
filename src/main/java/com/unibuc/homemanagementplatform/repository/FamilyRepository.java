package com.unibuc.homemanagementplatform.repository;

import com.unibuc.homemanagementplatform.model.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class FamilyRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private LogRepository logRepository;

    public Family save(Family family) {
        String saveSql = "INSERT INTO family (name) VALUES (?)";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(saveSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, family.getFamilyName());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        family.setFamilyId(generatedKeyHolder.getKey().longValue());
        logRepository.save(family + " has been inserted in the DB");

        return family;

    }


    public Family getOne(long familyId) {
        String selectSql = "SELECT* from family where family.id = ?";

        RowMapper<Family> rowMapper = (resultSet, rowNo) -> {
            Family family = new Family();
            family.setFamilyId(resultSet.getLong("id"));
            family.setFamilyName(resultSet.getString("name"));

            return family;
        };

        List<Family> familyList = jdbcTemplate.query(selectSql, rowMapper, familyId);

        return familyList.isEmpty() ? new Family() : familyList.get(0);
    }
}