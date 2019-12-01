package com.psbd.Attendance.controller;

import com.psbd.Attendance.model.AttendanceList;
import com.psbd.Attendance.service.AttendanceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/attendance_lists")
public class AttendanceController {

    @Autowired
    private AttendanceListService attendanceListService;

    @PostMapping
    public ResponseEntity<AttendanceList> saveNewAttendanceLists(@RequestBody AttendanceList attendanceList) {
        AttendanceList attendanceList1 = attendanceListService.save(attendanceList);
        return new ResponseEntity<>(attendanceList1, HttpStatus.CREATED);
    }
}
