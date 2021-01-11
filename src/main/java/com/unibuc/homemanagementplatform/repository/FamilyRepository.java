package com.unibuc.homemanagementplatform.repository;

import com.unibuc.homemanagementplatform.model.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FamilyRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private LogRepository logRepository;

    public Family save(Family family) {
        String saveSql = "INSERT INTO family (name) VALUES (?)";
        jdbcTemplate.update(saveSql, family.getFamilyName());
        
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

        return familyList.get(0);
    }

    /*public boolean delete(Long id) {
        String deleteSql = "DELETE from family where family.id = ?";
        jdbcTemplate.update(deleteSql,id);
    }*/
}