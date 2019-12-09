package com.psbd.Attendance.controller;

import com.psbd.Attendance.model.Classroom;
import com.psbd.Attendance.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<Classroom> saveNewTeacher(@RequestBody Classroom classroom) {
        Classroom classroom1 = classroomService.save(classroom);
        return new ResponseEntity<>(classroom1, HttpStatus.CREATED);
    }
}
