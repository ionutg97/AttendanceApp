package com.psbd.Attendance.persistance.repository;

import com.psbd.Attendance.model.Classroom;
import com.psbd.Attendance.model.Teacher;
import com.psbd.Attendance.persistance.repositoryUtils.ClassroomQueries;
import com.psbd.Attendance.persistance.repositoryUtils.TeacherQueries;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class JdbcTeacherRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private KeyHolder keyHolder;

    @Autowired
    public JdbcTeacherRepository(JdbcTemplate jdbcTemplate,
                                 NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                 KeyHolder keyHolder) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.keyHolder=keyHolder;
    }

    public Teacher save(Teacher teacher) {
        log.info("Saving teacher ");
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", teacher.getName());
        namedParameterJdbcTemplate.update(TeacherQueries.INSERT_TEACHER, parameters, keyHolder);
        List<Map<String, Object>> keyList = keyHolder.getKeyList();
        Long id = (Long) keyList.get(0).get("id");
        teacher.setId(id);
        return teacher;
    }

    public Optional<Teacher> findById(Long fromTeacherId) {
        log.info("Retrieving teacher with id {} ", fromTeacherId);
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(TeacherQueries.FIND_BY_ID,
                    new Object[]{fromTeacherId}, (rs, rowNum) ->
                            new Teacher(
                                    rs.getLong("id_teacher"),
                                    rs.getString("name_teacher"))));
        } catch (IncorrectResultSizeDataAccessException exception) {
            return Optional.empty();
        }
    }
}
