package com.psbd.Attendance.persistance.repository;

import com.psbd.Attendance.model.Teacher;
import com.psbd.Attendance.persistance.repositoryUtils.TeacherQueries;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class JdbcTeacherRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    // init SimpleJdbcCall
    @PostConstruct
    void init() {
        // o_name and O_NAME, same
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pack_teachers")
                .withProcedureName("add_teacher");
    }


    @Autowired
    public JdbcTeacherRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    //jdbc query
//    public Teacher save(Teacher teacher) {
//        log.info("Saving teacher ");
//        SqlParameterSource parameters = new MapSqlParameterSource()
//                .addValue("name", teacher.getName());
//        namedParameterJdbcTemplate.update(TeacherQueries.INSERT_TEACHER, parameters, keyHolder);
//        List<Map<String, Object>> keyList = keyHolder.getKeyList();
//        Long id = (Long) keyList.get(0).get("id");
//        teacher.setId(id);
//        return teacher;
//    }

    //jdbc call procedure
    public Teacher save(Teacher teacher) {
            SqlParameterSource in = new MapSqlParameterSource().addValue("v_name", teacher.getName());
        Map<String, Object> out = simpleJdbcCall.execute(in);
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
