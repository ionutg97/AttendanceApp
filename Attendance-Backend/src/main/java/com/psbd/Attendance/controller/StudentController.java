package com.psbd.Attendance.controller;

import com.psbd.Attendance.model.Student;
import com.psbd.Attendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> saveNewTeacher(@RequestBody Student student) {
        Student studentResult = studentService.save(student);
        return new ResponseEntity<>(studentResult, HttpStatus.CREATED);
    }
}

