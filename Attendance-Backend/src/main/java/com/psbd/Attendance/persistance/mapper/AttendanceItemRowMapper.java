package com.psbd.Attendance.persistance.mapper;

import com.psbd.Attendance.exception.ResourceNotFoundException;
import com.psbd.Attendance.model.AttendanceItem;
import com.psbd.Attendance.model.AttendanceList;
import com.psbd.Attendance.model.Classroom;
import com.psbd.Attendance.model.Teacher;
import com.psbd.Attendance.persistance.repository.JdbcAttendanceListRepository;
import com.psbd.Attendance.persistance.repository.JdbcClassroomRepository;
import com.psbd.Attendance.persistance.repository.JdbcStudentRepository;
import com.psbd.Attendance.persistance.repository.JdbcTeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Service
public class AttendanceItemRowMapper {//implements RowMapper<AttendanceItem> {

//    private JdbcClassroomRepository classroomRepository;
//    private JdbcTeacherRepository jdbcTeacherRepository;
//    private JdbcAttendanceListRepository jdbcAttendanceListRepository;
//    private JdbcStudentRepository jdbcStudentRepository;
//
//    @Autowired
//    public AttendanceItemRowMapper(JdbcClassroomRepository classroomRepository,
//                                   JdbcTeacherRepository jdbcTeacherRepository,
//                                   JdbcAttendanceListRepository jdbcAttendanceListRepository,
//                                   JdbcStudentRepository jdbcStudentRepository) {
//        this.classroomRepository = classroomRepository;
//        this.jdbcTeacherRepository = jdbcTeacherRepository;
//        this.jdbcAttendanceListRepository = jdbcAttendanceListRepository;
//        this.jdbcStudentRepository = jdbcStudentRepository;
//    }
//
//    @Override
//    public AttendanceItem mapRow(ResultSet resultSet, int i) throws SQLException {
//
//        //log.info("Mapping resultSet to feedback and fetching the user that sent the feedback");
//        Long fromAttendanceListId = resultSet.getLong("id_attendance_list");
//        Long fromTeacherId = resultSet.getLong("id_teacher");
//        Long fromClassroomId = resultSet.getLong("id_classroom");
//
//
//        Teacher teacher = jdbcTeacherRepository
//                .findById(fromTeacherId)
//                .orElseThrow(() -> new ResourceNotFoundException(Teacher.class.getSimpleName(), fromTeacherId));
//
//        Classroom classroom = classroomRepository
//                .findById(fromClassroomId)
//                .orElseThrow(() -> new ResourceNotFoundException(Classroom.class.getSimpleName(), fromClassroomId));
//
//        AttendanceList attendanceList = jdbcAttendanceListRepository
//                .findById(fromAttendanceListId)
//                .orElseThrow(() -> new ResourceNotFoundException(AttendanceList.class.getSimpleName(), fromAttendanceListId));
//
//
//        AttendanceItem attendanceItem = new AttendanceItem();
//        attendanceItem.setId(resultSet.getLong("id_attendance"));
//        attendanceItem.setClassroom(classroom);
//        attendanceItem.setTeacher(teacher);
//        attendanceItem.setAttendanceList(attendanceList);
//        attendanceItem.setGrade(resultSet.getInt("grade"));
//        attendanceItem.setDetails(resultSet.getString("detail"));
//        return attendanceItem;
//
//    }
}