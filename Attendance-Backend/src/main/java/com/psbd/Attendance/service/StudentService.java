package com.psbd.Attendance.service;

import com.psbd.Attendance.dto.LoginDto;
import com.psbd.Attendance.exception.ResourceNotFoundException;
import com.psbd.Attendance.model.Group;
import com.psbd.Attendance.model.Student;
import com.psbd.Attendance.persistance.repository.JdbcGroupRepository;
import com.psbd.Attendance.persistance.repository.JdbcStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> getAllStudentsByGroup(String group) {
        Group resultGroup = jdbcGroupRepository.findByName(group)
                .orElseThrow(() -> new ResourceNotFoundException(Group.class.getSimpleName()));

        return jdbcStudentRepository.findAllByGroup(resultGroup.getId())
                .orElseThrow(() -> new ResourceNotFoundException(Student.class.getSimpleName()))
                .stream()
                .sorted(Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER))
                .map(student -> new String().concat(student.getName()).concat(" ").concat(student.getIdentityNumber()))
                .collect(Collectors.toList());
    }

    public Boolean findStudentOnAttendance(Long idAttendance, Student student) {
        return jdbcStudentRepository.findStudentOnAttendance(idAttendance, student);
    }

    public LoginDto login(String name, String password) {
        String studentName = "user";
        String studentPassword = "04F8996DA763B7A969B1028EE3007569EAF3A635486DDAB211D512C85B9DF8FB";

        String teacherName = "admin";
        String teacherPassword = "8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918";

        if (name.equals(studentName) && password.toUpperCase().equals(studentPassword))
            return new LoginDto("user");
        else if (name.equals(teacherName) && password.toUpperCase().equals(teacherPassword))
            return new LoginDto("admin");
        else
            return new LoginDto("login fail");
    }
}
