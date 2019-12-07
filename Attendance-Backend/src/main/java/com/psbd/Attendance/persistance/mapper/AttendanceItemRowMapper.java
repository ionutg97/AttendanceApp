package com.psbd.Attendance.persistance.mapper;

import com.psbd.Attendance.model.AttendanceItem;
import com.psbd.Attendance.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Slf4j
@Service
public class AttendanceItemRowMapper implements RowMapper<AttendanceItem> {
    @Override
    public AttendanceItem mapRow(ResultSet resultSet, int i) throws SQLException {
        AttendanceItem attendanceItem=new AttendanceItem();


        Integer idStudent= resultSet.getInt("id_student");
        Integer grade = resultSet.getInt("grade");
        String detail =resultSet.getString("detail");
Student student=new Student((long)idStudent,null,null,null);

        attendanceItem.addStudent(student);
        attendanceItem.setGrade(grade);
        attendanceItem.setDetails(detail);

        return attendanceItem;
    }
}
