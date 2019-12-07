package com.psbd.Attendance.model;

import lombok.*;

@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceList {
    private Long id;
    private String name;
    private Category category;
    private Integer week;

}


