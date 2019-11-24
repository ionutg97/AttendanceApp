package com.psbd.Attendance.model;

import lombok.*;

@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentRef {

    private Long id;
    private Long studentId;
    private Long attendaceItemId;

    public StudentRef(Long studentId)
    {
        this.studentId=studentId;
    }


}
