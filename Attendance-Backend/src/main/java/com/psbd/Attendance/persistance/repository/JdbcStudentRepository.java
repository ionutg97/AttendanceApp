package com.psbd.Attendance.persistance.repository;

import com.psbd.Attendance.model.Group;
import com.psbd.Attendance.model.Student;
import com.psbd.Attendance.persistance.mapper.StudentRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class JdbcStudentRepository {


    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;
    private SimpleJdbcCall simpleJdbcCallGetAllStudentsByGroup;

    @Autowired
    private StudentRowMapper studentRowMapper;


    // init SimpleJdbcCall
    @PostConstruct
    void init() {
        // o_name and O_NAME, same
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pack_students")
                .withProcedureName("add_student");
        simpleJdbcCallGetAllStudentsByGroup=new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pack_students")
                .withProcedureName("get_all_students_by_group").returningResultSet("out_lists",studentRowMapper);
    }
    @Autowired
    public JdbcStudentRepository(JdbcTemplate jdbcTemplate,
                                 NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    //Save new Student throught jquery

//    public Student save(Student student) {
//        log.info("Saving student ");
//        SqlParameterSource parameters = new MapSqlParameterSource()
//                .addValue("name", student.getName())
//                .addValue("identityNumber",student.getIdentityNumber())
//                .addValue("group",student.getGroup().getId());
//        namedParameterJdbcTemplate.update(StudentQueries.INSERT_STUDENT, parameters,this.keyHolder);
//        List<Map<String, Object>> keyList = keyHolder.getKeyList();
//        Long id = (Long) keyList.get(0).get("id");
//        student.setId(id);
//        return student;
//    }

    public Student save(Student student) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("v_name", student.getName())
                .addValue("v_indentity_number",student.getIdentityNumber())
                .addValue("v_group_name", student.getGroup().getName());
        Map<String, Object> out = simpleJdbcCall.execute(in);
        return student;
    }

    public Optional<List<Student>> findAllByGroup(Long idGroup)
    {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("v_id_group", idGroup);
        Map out=  simpleJdbcCallGetAllStudentsByGroup.execute(in);
        ArrayList<Student> students = (ArrayList<Student>) out.get("out_lists");
        return Optional.of(students);
    }

}

