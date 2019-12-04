package com.psbd.Attendance.model;

import lombok.*;

@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupRef {
    private Long groupId;
    private Long attendaceListId;
}
