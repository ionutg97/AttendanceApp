package com.psbd.Attendance.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Builder
//@AllArgsConstructor
@NoArgsConstructor
@Data
public class Group {
    private Long id;
    private String name;
    //private Set<Student> students = new HashSet<Student>();

    public Group(String name)
    {
        this.name=name;
    }

    public Group(Long id, String name)
    {
        this.id=id;
        this.name=name;
    }
   // public void addStudent(Student student) {
     //   this.students.add(student);
    //}
}
