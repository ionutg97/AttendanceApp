package com.psbd.Attendance.persistance.repository;

import com.psbd.Attendance.model.AttendanceList;
import com.psbd.Attendance.model.Category;
import com.psbd.Attendance.model.Student;
import com.psbd.Attendance.model.Teacher;
import com.psbd.Attendance.persistance.repositoryUtils.AttendanceItemQueries;
import com.psbd.Attendance.persistance.repositoryUtils.AttendanceListQueries;
import com.psbd.Attendance.persistance.repositoryUtils.TeacherQueries;
import lombok.extern.slf4j.Slf4j;
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
public class JdbcAttendanceListRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private KeyHolder keyHolder;

    public JdbcAttendanceListRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, KeyHolder keyHolder) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.keyHolder = keyHolder;
    }

    public AttendanceList save(AttendanceList attendanceList) {
        log.info("Saving attendance list ");
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", attendanceList.getName())
                .addValue("category",attendanceList.getCategory().toString());
        namedParameterJdbcTemplate.update(AttendanceListQueries.INSERT_ATTENDANCE_LIST, parameters, keyHolder);
        List<Map<String, Object>> keyList = keyHolder.getKeyList();
        Long id = (Long) keyList.get(0).get("id");
        attendanceList.setId(id);
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
