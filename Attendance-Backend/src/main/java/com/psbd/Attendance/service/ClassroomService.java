package com.psbd.Attendance.service;

import com.psbd.Attendance.model.Classroom;
import com.psbd.Attendance.model.Teacher;
import com.psbd.Attendance.persistance.repository.JdbcClassroomRepository;
import com.psbd.Attendance.persistance.repository.JdbcTeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClassroomService {


    private JdbcClassroomRepository jdbcClassroomRepository;

    @Autowired
    public ClassroomService(JdbcClassroomRepository jdbcClassroomRepository) {
        this.jdbcClassroomRepository = jdbcClassroomRepository;
    }

    public Classroom save(Classroom classroom) {
        return jdbcClassroomRepository.saveProcedureWay(classroom);
    }
}
