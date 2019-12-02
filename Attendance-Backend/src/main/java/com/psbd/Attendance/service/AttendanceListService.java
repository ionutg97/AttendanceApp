package com.psbd.Attendance.service;

import com.psbd.Attendance.model.AttendanceList;
import com.psbd.Attendance.persistance.repository.JdbcAttendanceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AttendanceListService {


    private JdbcAttendanceListRepository jdbcAttendanceListRepository;

    @Autowired
    public AttendanceListService(JdbcAttendanceListRepository jdbcAttendanceListRepository) {
        this.jdbcAttendanceListRepository = jdbcAttendanceListRepository;
    }

    public AttendanceList save(AttendanceList attendanceList) {
        return jdbcAttendanceListRepository.saveProcedureWay(attendanceList);
    }
}
