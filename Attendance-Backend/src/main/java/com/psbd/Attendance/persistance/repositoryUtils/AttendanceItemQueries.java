package com.psbd.Attendance.persistance.repositoryUtils;

public class AttendanceItemQueries {

    public static final String INSERT_ATTENDANCE_ITEM = "insert into attendances " +
            "(" +
            "id_classroom, " +
            "id_teacher, " +
            "id_attendance_list, " +
            "grade, " +
            "detail " +
            ") " +

            "values " +
            "(" +
            ":classroom," +
            ":teacher, " +
            ":attendanceList, " +
            ":grade ," +
            ":details " +
            ") ";

    public static final String FIND_BY_ID = " select * from attendaces where id_attendance = ?";
}
