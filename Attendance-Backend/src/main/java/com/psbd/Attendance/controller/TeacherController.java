package com.psbd.Attendance.controller;


import com.psbd.Attendance.model.Teacher;
import com.psbd.Attendance.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @PostMapping
    public ResponseEntity<Teacher> saveNewTeacher(@RequestBody Teacher teacher) {
        Teacher teacherResult = teacherService.save(teacher);
        return new ResponseEntity<>(teacherResult, HttpStatus.CREATED);
    }
}
