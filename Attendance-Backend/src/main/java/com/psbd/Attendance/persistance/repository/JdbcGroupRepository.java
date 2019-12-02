package com.psbd.Attendance.persistance.repository;

import com.psbd.Attendance.model.Group;
import com.psbd.Attendance.persistance.mapper.GroupRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
@Slf4j
public class JdbcGroupRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;
    private SimpleJdbcCall simpleJdbcFunctionGetAll;

    @Autowired
    GroupRowMapper  groupRowMapper;
    // init SimpleJdbcCall
    @PostConstruct
    void init() {
        // o_name and O_NAME, same
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pack_groups")
                .withProcedureName("add_group");

        simpleJdbcFunctionGetAll = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pack_groups")
                .withFunctionName("get_allGroups").returningResultSet("out_groups",groupRowMapper);;
    }
    @Autowired
    public JdbcGroupRepository(JdbcTemplate jdbcTemplate,
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

    public Group save(Group group) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("v_name", group.getName());
        Map<String, Object> out = simpleJdbcCall.execute(in);
        return group;
    }

    public Group findByName(String name)
    {
        return null;
    }

    public Optional<List<Group>> findAll(){
        log.info("get all Groups");
        Set<Group> groups=new TreeSet<Group>();
        Map out=  simpleJdbcFunctionGetAll.execute();
        ArrayList<Group> arrayGroups = (ArrayList<Group>) out.get("out_groups");
        return Optional.of(arrayGroups);
    }
}
