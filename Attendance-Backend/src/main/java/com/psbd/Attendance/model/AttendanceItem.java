package com.psbd.Attendance.model;

import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceItem {
    private Long id;

    private Classroom classroom;

    private Teacher teacher;

    private AttendanceList attendanceList;

    private List<Student> students = new ArrayList<>();

    private Integer grade;

    private String details;

    // storage a list of students id for one attendanceItem id
//    public void addStudent(Student student) {
//        this.students.add(new StudentRef(student.getId(), this.getId()));
//    }

        public void addStudent(Student student) {
        this.students.add(student);
    }

//    public Set<Long> getStudentsId() {
//        return this.students.stream()
//                .map(StudentRef::getStudentId)
//                .collect(Collectors.toSet());
//    }

}
