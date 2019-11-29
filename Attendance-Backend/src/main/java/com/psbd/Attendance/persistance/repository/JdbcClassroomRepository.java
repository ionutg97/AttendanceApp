package com.psbd.Attendance.persistance.repository;

import com.psbd.Attendance.model.Classroom;
import com.psbd.Attendance.persistance.repositoryUtils.ClassroomQueries;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
public class JdbcClassroomRepository {

//
//    private final JdbcTemplate jdbcTemplate;
//    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//    private final KeyHolder keyHolder;
//
//    @Autowired
//    public JdbcClassroomRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
//                                   KeyHolder keyHolder) {
//        this.jdbcTemplate = jdbcTemplate;
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//        this.keyHolder = keyHolder;
//    }
//
//    public Optional<Classroom> findById(Long fromClassroomId) {
//        log.info("Retrieving classroom with id {} ", fromClassroomId);
//        try {
//            return Optional.ofNullable(jdbcTemplate.queryForObject(ClassroomQueries.FIND_BY_ID,
//                    new Object[]{fromClassroomId}, (rs, rowNum) ->
//                            new Classroom(
//                                    rs.getLong("id_classroom"),
//                                    rs.getString("name_classroom"))));
//        } catch (IncorrectResultSizeDataAccessException exception) {
//            return Optional.empty();
//        }
//    }
}
