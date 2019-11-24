package com.psbd.Attendance.persistance.repository;

import com.psbd.Attendance.AttendanceApplication;
import com.psbd.Attendance.model.Student;
import com.psbd.Attendance.model.Teacher;
import com.psbd.Attendance.persistance.repositoryUtils.StudentQueries;
import com.psbd.Attendance.persistance.repositoryUtils.TeacherQueries;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class JdbcStudentRepository {


    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private KeyHolder keyHolder;

    @Autowired
    public JdbcStudentRepository(JdbcTemplate jdbcTemplate,
                                 NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                 KeyHolder keyHolder) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.keyHolder=keyHolder;
    }


    public void createTableTest() {
        log.info("create table books");
        jdbcTemplate.execute("CREATE TABLE books(" +
                "idb numeric, name VARCHAR(255), price NUMERIC(15, 2))");
    }

    public Student save(Student student) {
        log.info("Saving student ");
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", student.getName())
                .addValue("identityNumber",student.getIdentityNumber())
                .addValue("group",student.getGroup().getId());
        namedParameterJdbcTemplate.update(StudentQueries.INSERT_STUDENT, parameters, keyHolder);
        List<Map<String, Object>> keyList = keyHolder.getKeyList();
        Long id = (Long) keyList.get(0).get("id");
        student.setId(id);
        return student;
    }

}

