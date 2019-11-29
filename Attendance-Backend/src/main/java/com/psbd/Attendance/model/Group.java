package com.psbd.Attendance.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Group {
    private Long id;
    private String name;
    private Set<Student> students = new HashSet<Student>();

    public void addStudent(Student student) {
        this.students.add(student);
    }
}
