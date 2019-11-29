package com.psbd.Attendance.model;

import lombok.*;

@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    private Long id;
    private String name;
    private String identityNumber;
    private Group group;
}
