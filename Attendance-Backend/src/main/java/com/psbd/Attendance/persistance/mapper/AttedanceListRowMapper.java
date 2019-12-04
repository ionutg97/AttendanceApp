package com.psbd.Attendance.persistance.mapper;

import com.psbd.Attendance.model.AttendanceList;
import com.psbd.Attendance.model.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class AttedanceListRowMapper implements RowMapper<AttendanceList> {
    @Override
    public AttendanceList mapRow(ResultSet resultSet, int i) throws SQLException {
        AttendanceList attendanceList=new AttendanceList();

        Integer idList = resultSet.getInt("id_attendance_list");
        String nameList = resultSet.getString("name_attendance");
        Integer week =resultSet.getInt("week");
        String type =resultSet.getString("type");

        attendanceList.setId((long)idList);
        attendanceList.setName(nameList);
        attendanceList.setWeek(week);
        attendanceList.setCategory(Category.getInstance(type));

        return attendanceList;
    }
}
