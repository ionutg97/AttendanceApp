package com.psbd.Attendance.persistance.mapper;

import com.psbd.Attendance.model.Group;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Service
public class GroupRowMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet resultSet, int i) throws SQLException {
        Integer idGroup = resultSet.getInt("id_group");
        String nameGroup = resultSet.getString("name_group");

        Group group=new Group();
        group.setId((long)idGroup);
        group.setName(nameGroup);
        return group;
    }
}
