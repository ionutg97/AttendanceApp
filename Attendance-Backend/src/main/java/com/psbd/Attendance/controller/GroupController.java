package com.psbd.Attendance.controller;

import com.psbd.Attendance.model.Group;
import com.psbd.Attendance.service.GroupService;
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
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public ResponseEntity<Group> saveNewGroup(@RequestBody Group group) {
        Group group1 = groupService.save(group);
        return new ResponseEntity<>(group1, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllGroups() {
        log.info("GET request for all groups ");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        List<String> groups = groupService.findAll();
        return new ResponseEntity<>(groups, responseHeaders, HttpStatus.OK);
    }

}
