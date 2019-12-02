package com.psbd.Attendance.service;

import com.psbd.Attendance.model.Student;
import com.psbd.Attendance.persistance.repository.JdbcGroupRepository;
import com.psbd.Attendance.persistance.repository.JdbcStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private JdbcStudentRepository jdbcStudentRepository;

    @Autowired
    private JdbcGroupRepository jdbcGroupRepository;

    public Student save(Student student) {
       // Group group=jdbcGroupRepository.findByName(student.getGroup().getName());
        //student.setGroup(group);
        return jdbcStudentRepository.save(student);
    }
}
