package com.psbd.Attendance.controller;

import com.psbd.Attendance.dto.LoginDto;
import com.psbd.Attendance.model.Student;
import com.psbd.Attendance.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @PostMapping
    public ResponseEntity<Student> saveNewStudent(@RequestBody Student student) {
        Student studentResult = studentService.save(student);
        return new ResponseEntity<>(studentResult, HttpStatus.CREATED);
    }


    @GetMapping("/login")
    public ResponseEntity<LoginDto> login(@RequestParam String username, @RequestParam String password) {
        log.info("GET request for login");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        LoginDto resultLogin = studentService.login(username, password);
        return new ResponseEntity<>(resultLogin, responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllList(@RequestParam String group) {
        log.info("GET request for all students from group ");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        List<String> lists = studentService.getAllStudentsByGroup(group);
        return new ResponseEntity<>(lists, responseHeaders, HttpStatus.OK);
    }

}

