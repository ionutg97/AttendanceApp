package com.psbd.Attendance.persistance.repositoryUtils;

public class StudentQueries {

    public static final String INSERT_STUDENT="insert into students " +
            "(" +
            "name_attendance, " +
            "type"+
            ") "+

            "values " +
            "(" +
            ":name," +
            ":category "+
            ") ";
}
