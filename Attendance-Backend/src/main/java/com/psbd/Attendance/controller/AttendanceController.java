package com.psbd.Attendance.controller;

import com.psbd.Attendance.model.AttendanceList;
import com.psbd.Attendance.service.AttendanceListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
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

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllList(@RequestParam Integer week, @RequestParam String type){
        log.info("GET request for all attendance lists ");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        List<String> lists = attendanceListService.getAllListsByWeekAndType(week,type);
        return new ResponseEntity<>(lists,responseHeaders,HttpStatus.OK);
    }

    @GetMapping("/groups")
    public ResponseEntity<List<String>> getAllList(@RequestParam String name,@RequestParam Integer week,@RequestParam String type) {
        log.info("GET request for all groups by attendance list name ");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        List<String> lists = attendanceListService.getAllGroupsByNameAttendanceList(name, week, type);
        return new ResponseEntity<>(lists, responseHeaders, HttpStatus.OK);
    }
}
