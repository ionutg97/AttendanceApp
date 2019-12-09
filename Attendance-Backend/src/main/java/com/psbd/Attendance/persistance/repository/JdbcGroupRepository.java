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
import java.math.BigDecimal;
import java.util.*;

@Repository
@Slf4j
public class JdbcGroupRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    GroupRowMapper groupRowMapper;
    private SimpleJdbcCall simpleJdbcCall;
    private SimpleJdbcCall simpleJdbcFunctionGetAll;
    private SimpleJdbcCall simpleJdbcProcGetGroup;
    private SimpleJdbcCall getGroupById;

    @Autowired
    public JdbcGroupRepository(JdbcTemplate jdbcTemplate,
                               NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

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
                .withFunctionName("get_allGroups").returningResultSet("out_groups", groupRowMapper);

        simpleJdbcProcGetGroup = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pack_groups")
                .withProcedureName("get_group");

        getGroupById = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pack_groups")
                .withProcedureName("get_groupById");

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

    public Optional<Group> findByName(String name) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("v_name", name);
        Map out = simpleJdbcProcGetGroup.execute(in);
        BigDecimal bigDecimalResult = (BigDecimal) out.get("out_id");
        return Optional.of(new Group(bigDecimalResult.longValue(), name));
    }

    public Optional<List<Group>> findAll() {
        log.info("get all Groups");
        Set<Group> groups = new TreeSet<Group>();
        Map out = simpleJdbcFunctionGetAll.execute();
        ArrayList<Group> arrayGroups = (ArrayList<Group>) out.get("out_groups");
        return Optional.of(arrayGroups);
    }

    public Optional<Group> findById(Long id) {
        log.info("Repository get Group by Id {}", id);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("v_id", id);
        Map out = getGroupById.execute(in);

        Group group = new Group();
        if (!out.isEmpty()) {
            group.setId(id);
            group.setName((String) out.get("out_name_group"));
        }
        return Optional.of(group);
    }
}
