package com.psbd.Attendance.service;

import com.psbd.Attendance.model.Group;
import com.psbd.Attendance.model.Teacher;
import com.psbd.Attendance.persistance.repository.JdbcGroupRepository;
import com.psbd.Attendance.persistance.repository.JdbcTeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GroupService {


    private JdbcGroupRepository jdbcGroupRepository;

    @Autowired
    public GroupService(JdbcGroupRepository jdbcGroupRepository) {
        this.jdbcGroupRepository = jdbcGroupRepository;
    }

    public Group save(Group group) {
        return jdbcGroupRepository.save(group);
    }
}
