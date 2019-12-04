package com.psbd.Attendance.persistance.mapper;

import com.psbd.Attendance.model.GroupRef;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Service
public class GroupRefRowMapper implements RowMapper<GroupRef> {

@Override
public GroupRef mapRow(ResultSet resultSet, int i) throws SQLException {

        Integer idAttendanceList = resultSet.getInt("id_attendance_list");
        Integer idGroup = resultSet.getInt("id_group");

        GroupRef groupRef=new GroupRef();
        groupRef.setAttendaceListId((long)idAttendanceList);
        groupRef.setGroupId((long)idGroup);
        return groupRef;
        }
}