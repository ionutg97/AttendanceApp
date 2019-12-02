package com.psbd.Attendance.persistance.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class JdbcAttendanceItemRepository {
//
//    private final JdbcTemplate jdbcTemplate;
//    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//    private final KeyHolder keyHolder;
//    private final AttendanceItemRowMapper attendanceItemRowMapper;
//
//    @Autowired
//    public JdbcAttendanceItemRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
//                                        KeyHolder keyHolder, AttendanceItemRowMapper attendanceItemRowMapper) {
//        this.jdbcTemplate = jdbcTemplate;
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//        this.keyHolder = keyHolder;
//        this.attendanceItemRowMapper = attendanceItemRowMapper;
//    }
//
//    public AttendanceItem save(AttendanceItem attendanceItem) {
//        log.info("Saving attendance item ");
//        SqlParameterSource parameters = new MapSqlParameterSource()
//                .addValue("classroom", attendanceItem.getClassroom().getId())
//                .addValue("teacher", attendanceItem.getTeacher().getId())
//                .addValue("attendanceList", attendanceItem.getAttendanceList().getId())
//                .addValue("grade", 0)
//                .addValue("details", null);
//        namedParameterJdbcTemplate.update(INSERT_ATTENDANCE_ITEM, parameters, keyHolder);
//        List<Map<String, Object>> keyList = keyHolder.getKeyList();
//        Long id = (Long) keyList.get(0).get("id");
//        attendanceItem.setId(id);
//        return attendanceItem;
//    }
//
//    public Optional<AttendanceItem> findById(Long attendanceItemId) {
//        log.info("Retrieving attendance with id {} ", attendanceItemId);
//        try {
//            return Optional.ofNullable(jdbcTemplate.queryForObject(AttendanceItemQueries.FIND_BY_ID, new Object[]{attendanceItemId}, attendanceItemRowMapper));
//        } catch (IncorrectResultSizeDataAccessException exception) {
//            return Optional.empty();
//        }
//    }


}
