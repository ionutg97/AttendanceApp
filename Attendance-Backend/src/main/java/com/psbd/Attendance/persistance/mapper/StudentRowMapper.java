package com.psbd.Attendance.persistance.mapper;

import com.psbd.Attendance.model.Student;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class StudentRowMapper  implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student=new Student();

        Integer id = resultSet.getInt("id_student");
        String name = resultSet.getString("name_student");
        String identity =resultSet.getString("identity_number");

        student.setId((long)id);
        student.setName(name);
        student.setIdentityNumber(identity);

        return student;
    }
}