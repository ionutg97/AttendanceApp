package com.psbd.Attendance.persistance.repositoryUtils;

public class TeacherQueries {
    public static final String FIND_BY_ID = "select * from teachers where id = ?";
    public static String INSERT_TEACHER = "insert into teachers " +
            "(" +
            "name_teacher" +
            ") "+

            "values " +
            "(" +
            ":name" +
            ") ";
}
