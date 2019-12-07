package com.psbd.Attendance.persistance.repository;

import com.psbd.Attendance.model.AttendanceItem;
import com.psbd.Attendance.model.Student;
import com.psbd.Attendance.persistance.mapper.AttedanceListRowMapper;
import com.psbd.Attendance.persistance.mapper.AttendanceItemRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JdbcAttendanceItemRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall,simpleJdbcCallUpdate,simpleJdbcCallGetAllById;

    @Autowired
    AttendanceItemRowMapper attendanceItemRowMapper;

    @Autowired
    public JdbcAttendanceItemRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        System.out.println("Constructor JdbcAttendanceItemRepository");
    }

    @PostConstruct
    void init() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        System.out.println("Post constructor JdbcAttendanceItemRepository");

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pack_attendances_students")
                .withProcedureName("add_attendances_students");
        simpleJdbcCallUpdate=new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pack_attendances_students")
                .withProcedureName("update_attendances_students");
        simpleJdbcCallGetAllById=new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("pack_attendances_students")
                .withProcedureName("get_attendances_studentss").returningResultSet("out_lists",attendanceItemRowMapper);
    }


    // Procedure created in Data base
    public AttendanceItem saveOneStudent(AttendanceItem attendanceItem){
        log.info("Repository Save new attendance student");
        String nameAttedance=attendanceItem.getAttendanceList().getName();
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("v_identity_number", attendanceItem.getStudents().get(0).getIdentityNumber())
                    .addValue("v_attendance_list_name",nameAttedance )
                    .addValue("v_grade",attendanceItem.getGrade())
                    .addValue("v_detail",attendanceItem.getDetails());
            Map out = simpleJdbcCall.execute(in);
        return attendanceItem;
    }


    public Optional<AttendanceItem> update(AttendanceItem attendanceItem) {
        log.info("Repository update Student from Attendance Item repository");
        Long idAttedanceList=attendanceItem.getAttendanceList().getId();
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("v_attendance_list_id",idAttedanceList)
                .addValue("v_identity_number", attendanceItem.getStudents().get(0).getIdentityNumber())
                .addValue("v_grade",attendanceItem.getGrade())
                .addValue("v_detail",attendanceItem.getDetails());

        Map out = simpleJdbcCallUpdate.execute(in);
        return Optional.of(attendanceItem);
    }

    public Optional<List<AttendanceItem>> findAllById(Long attendanceId)
    {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("v_id_attendance_list",attendanceId);
        Map out=  simpleJdbcCallGetAllById.execute(in);
        ArrayList<AttendanceItem> attendanceItems = (ArrayList<AttendanceItem>) out.get("out_lists");
        return Optional.of(attendanceItems);
    }
}
