package com.psbd.Attendance.mapper;

import com.psbd.Attendance.dto.AttendanceItemDto;
import com.psbd.Attendance.model.AttendanceItem;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AttendanceItemMapper {

    public AttendanceItemDto map(AttendanceItem attendanceItem) {
        return AttendanceItemDto.builder()
                .nameStudent(attendanceItem.getStudents().get(0).getName())
                .identityNumber(attendanceItem.getStudents().get(0).getIdentityNumber())
                .attendance(Arrays.stream(attendanceItem.getAttendanceList().getName().split(" ")).findFirst().get())
                .grade(attendanceItem.getGrade())
                .details(attendanceItem.getDetails())
                .build();
    }

}
