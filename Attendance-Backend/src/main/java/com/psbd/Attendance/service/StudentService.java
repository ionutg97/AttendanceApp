package com.psbd.Attendance.service;

import com.psbd.Attendance.persistance.repository.JdbcStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private JdbcStudentRepository jdbcStudentRepository;

    public void test() {
        jdbcStudentRepository.createTableTest();
    }
}