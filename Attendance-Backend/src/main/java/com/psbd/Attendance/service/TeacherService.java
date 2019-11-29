package com.psbd.Attendance.service;

import com.psbd.Attendance.model.Teacher;
import com.psbd.Attendance.persistance.repository.JdbcTeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {


    private JdbcTeacherRepository jdbcTeacherRepository;

    @Autowired
    public TeacherService(JdbcTeacherRepository jdbcTeacherRepository) {
        this.jdbcTeacherRepository = jdbcTeacherRepository;
    }

    public Teacher save(Teacher teacher) {
        return jdbcTeacherRepository.save1(teacher);
    }
}
