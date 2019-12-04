package com.psbd.Attendance.persistance.repository;

import com.psbd.Attendance.model.AttendanceList;
import com.psbd.Attendance.model.Category;
import com.psbd.Attendance.model.GroupRef;
import com.psbd.Attendance.persistance.mapper.AttedanceListRowMapper;
import com.psbd.Attendance.persistance.mapper.GroupRefRowMapper;
import com.psbd.Attendance.persistance.repositoryUtils.AttendanceListQueries;
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
import java.math.BigDecimal;
import java.util.ArrayList;
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
    private SimpleJdbcCall saveGroupsAttendanceLists,getAllGroupsAttendanceListsByIdAttendance;
    private SimpleJdbcCall getAllListsByWeekAndType;

    @Autowired
    private AttedanceListRowMapper attedanceListRowMapper;

    @Autowired
    private GroupRefRowMapper groupRefRowMapper;

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

        saveGroupsAttendanceLists =new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pack_groups_attend_lists")
                .withProcedureName("add_groups_attend_list");

        getAllGroupsAttendanceListsByIdAttendance = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pack_groups_attend_lists")
                .withProcedureName("get_groups_attend_list_by_id").returningResultSet("out_lists",groupRefRowMapper);

        getAllListsByWeekAndType=new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pack_attendance_lists")
                .withProcedureName("get_attendance_lists").returningResultSet("out_lists",attedanceListRowMapper);

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
        log.info("Repository Save new attendance list");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("v_name", attendanceList.getName())
                .addValue("v_type", attendanceList.getCategory())
                .addValue("v_week", attendanceList.getWeek());
        Map out = simpleJdbcCall.execute(in);
        BigDecimal bigDecimalNumber= (BigDecimal) out.get("v_out_id");
        attendanceList.setId( bigDecimalNumber.longValue());
        return attendanceList;
    }

    public GroupRef saveGroupAttendanceList(GroupRef groupRef)
    {
        log.info("Repository save new Group Attendance lists value ");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("v_id_group", groupRef.getGroupId())
                .addValue("v_id_attendance_list", groupRef.getAttendaceListId());
        Map out = saveGroupsAttendanceLists.execute(in);
        return groupRef;
    }

    public Optional<AttendanceList> findById(Long fromAttendanceListId) {
        log.info("Retrieving attendance list with id {} ", fromAttendanceListId);
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(AttendanceListQueries.FIND_BY_ID,
                    new Object[]{fromAttendanceListId}, (rs, rowNum) ->
                            new AttendanceList(
                                    rs.getLong("id_attendance_lits"),
                                    rs.getString("name_attendance"),
                                    Category.valueOf(rs.getString("type")),
                                    rs.getInt("week"))));
        } catch (IncorrectResultSizeDataAccessException exception) {
            return Optional.empty();
        }
    }

    public Optional<List<AttendanceList>> getAllListsByWeekAndType(Integer week, String type)
    {
        log.info("Repository retrieving attendance lists by week and type ");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("v_week", week)
                .addValue("v_type",type);
        Map out=  getAllListsByWeekAndType.execute(in);
        ArrayList<AttendanceList> attendanceLists = (ArrayList<AttendanceList>) out.get("out_lists");
        return Optional.of(attendanceLists);
    }

    public Optional<List<GroupRef>> getAllGroupsIdByIdAttendanceList(Long idAttedanceList)
    {
        log.info("Repository retrieving all groups which are on one list of attendance ");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("v_id", idAttedanceList);
        Map out=  getAllGroupsAttendanceListsByIdAttendance.execute(in);
        ArrayList<GroupRef> groupRefs = (ArrayList<GroupRef>) out.get("out_lists");
        return Optional.of(groupRefs);
    }
}
