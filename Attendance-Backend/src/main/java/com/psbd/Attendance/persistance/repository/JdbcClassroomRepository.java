package com.psbd.Attendance.persistance.repository;

import com.psbd.Attendance.model.Classroom;
import com.psbd.Attendance.persistance.repositoryUtils.ClassroomQueries;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class JdbcClassroomRepository {


    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private KeyHolder keyHolder = new GeneratedKeyHolder();
    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    public JdbcClassroomRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

    }

    @PostConstruct
    void init() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pack_classrooms")
                .withProcedureName("add_classroom");
    }

    public Optional<Classroom> findById(Long fromClassroomId) {
        log.info("Retrieving classroom with id {} ", fromClassroomId);
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(ClassroomQueries.FIND_BY_ID,
                    new Object[]{fromClassroomId}, (rs, rowNum) ->
                            new Classroom(
                                    rs.getLong("id_classroom"),
                                    rs.getString("name_classroom"))));
        } catch (IncorrectResultSizeDataAccessException exception) {
            return Optional.empty();
        }
    }

    public Classroom saveProcedureWay(Classroom classroom) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("v_name", classroom.getName());
        Map<String, Object> out = simpleJdbcCall.execute(in);
        return classroom;
    }
}
