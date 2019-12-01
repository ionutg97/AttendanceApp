package com.psbd.Attendance.controller;

import com.psbd.Attendance.model.Group;
import com.psbd.Attendance.model.Student;
import com.psbd.Attendance.service.GroupService;
import com.psbd.Attendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public ResponseEntity<Group> saveNewGroup(@RequestBody Group group) {
        Group group1 = groupService.save(group);
        return new ResponseEntity<>(group1, HttpStatus.CREATED);
    }
}
