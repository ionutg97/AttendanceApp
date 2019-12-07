package com.psbd.Attendance.controller;

import com.psbd.Attendance.dto.AttendanceItemDto;
import com.psbd.Attendance.model.AttendanceItem;
import com.psbd.Attendance.model.AttendanceList;
import com.psbd.Attendance.model.Category;
import com.psbd.Attendance.model.Student;
import com.psbd.Attendance.service.AttendanceItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/attendance_item")
public class AttendanceItemController {

    private AttendanceItemService attendanceItemService;

    @Autowired
    public AttendanceItemController(AttendanceItemService attendanceItemService)
    {
        this.attendanceItemService=attendanceItemService;
        System.out.println("Constructor AttendanceItemController");
    }


    @PostMapping("/save_student")
    public ResponseEntity<AttendanceItem> addStudentOnAttendance(@RequestParam(name = "attendance_name") String attendanceName,
                                                                 @RequestBody Student student ){
        log.info("/attedance_item/save_student Controller method");
        AttendanceItem attendanceItem=attendanceItemService.saveNewAttendanceItem(attendanceName,student);
        return new ResponseEntity<>(attendanceItem, HttpStatus.CREATED);
    }

    @PutMapping("/save_student_details")
    public ResponseEntity<AttendanceItem> updateStudentOnAttendance(@RequestBody AttendanceItem attendanceItem ){
        log.info("/attedance_item/save_student_details Controller method");
        AttendanceItem attendanceItem1=attendanceItemService.updateAttendanceItem(attendanceItem);
        return new ResponseEntity<>(attendanceItem1,HttpStatus.OK);
    }

    @GetMapping("/all/{attendanceId}")
    public ResponseEntity<List<AttendanceItemDto>> getAllAttendance(@PathVariable Long attendanceId) {
        log.info("GET request for all attendance items ");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        List<AttendanceItemDto> attendanceItemDtos =attendanceItemService.findAllById(attendanceId);
        //List<Student> students=new ArrayList<Student>();
//        Student student=new Student(null,"Jhon","212313",null);
//        students.add(student);
//        AttendanceItem attendanceItem = new AttendanceItem(null,null,null,
//                new AttendanceList(null,"ppp", Category.getInstance("seminary"),10),students,10,"good");
        return new ResponseEntity<>(attendanceItemDtos,responseHeaders,HttpStatus.OK);
    }

}
