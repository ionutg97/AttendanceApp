package com.psbd.Attendance.persistance.repository;

import com.psbd.Attendance.model.AttendanceList;
import com.psbd.Attendance.model.Category;
import com.psbd.Attendance.persistance.repositoryUtils.AttendanceListQueries;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScans;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class JdbcAttendanceListRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private KeyHolder keyHolder=new GeneratedKeyHolder();

    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    public JdbcAttendanceListRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @PostConstruct
    void init() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pack_attendance_lists")
                .withProcedureName("add_attendance_list");
    }

    //Jquery save new Element
    public AttendanceList saveJQueryWay(AttendanceList attendanceList) {
        log.info("Saving attendance list ");
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", attendanceList.getName())
                .addValue("category", attendanceList.getCategory().toString());
        namedParameterJdbcTemplate.update(AttendanceListQueries.INSERT_ATTENDANCE_LIST, parameters, keyHolder);
        List<Map<String, Object>> keyList = keyHolder.getKeyList();
        Long id = (Long) keyList.get(0).get("id");
        attendanceList.setId(id);
        return attendanceList;
    }

    // Procedure created in Data base
    public AttendanceList saveProcedureWay(AttendanceList attendanceList){
        log.info("Save attendance list");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("v_name", attendanceList.getName())
                .addValue("v_type", attendanceList.getCategory());
        Map<String, Object> out = simpleJdbcCall.execute(in);
        return attendanceList;
    }

    public Optional<AttendanceList> findById(Long fromAttendanceListId) {
        log.info("Retrieving attendance list with id {} ", fromAttendanceListId);
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(AttendanceListQueries.FIND_BY_ID,
                    new Object[]{fromAttendanceListId}, (rs, rowNum) ->
                            new AttendanceList(
                                    rs.getLong("id_attendance_lits"),
                                    rs.getString("name_attendance"),
                                    Category.valueOf(rs.getString("type")))));
        } catch (IncorrectResultSizeDataAccessException exception) {
            return Optional.empty();
        }
    }
}
