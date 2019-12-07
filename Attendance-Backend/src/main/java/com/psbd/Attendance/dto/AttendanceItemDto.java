package com.psbd.Attendance.dto;

import lombok.*;

@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceItemDto {
    String nameStudent;
    String identityNumber;
    String attendance;
    Integer grade;
    String details;

}
