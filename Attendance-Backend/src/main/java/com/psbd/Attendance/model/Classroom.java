package com.psbd.Attendance.model;

import lombok.*;

@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Classroom {
    private Long id;
    private String name;

}
