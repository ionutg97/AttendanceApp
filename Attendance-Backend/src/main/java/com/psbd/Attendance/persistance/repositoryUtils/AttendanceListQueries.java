package com.psbd.Attendance.persistance.repositoryUtils;

public class AttendanceListQueries {

    public static final String INSERT_ATTENDANCE_LIST = "insert into students " +
            "(" +
            "name_student, " +
            "identity_number, " +
            "id_group " +
            ") " +

            "values " +
            "(" +
            ":name," +
            ":identityNumber, " +
            ":group" +
            ") ";
    public static final String FIND_BY_ID = "select * from attendance_lists where id_attendance_list= ?";
}
