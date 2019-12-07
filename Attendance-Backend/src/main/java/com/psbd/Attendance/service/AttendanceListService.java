package com.psbd.Attendance.service;

import com.psbd.Attendance.exception.ResourceNotFoundException;
import com.psbd.Attendance.model.AttendanceList;
import com.psbd.Attendance.model.Group;
import com.psbd.Attendance.model.GroupRef;
import com.psbd.Attendance.persistance.repository.JdbcAttendanceListRepository;
import com.psbd.Attendance.persistance.repository.JdbcGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service
public class AttendanceListService {

    private JdbcAttendanceListRepository jdbcAttendanceListRepository;

    @Autowired
    private JdbcGroupRepository jdbcGroupRepository;

    @Autowired
    public AttendanceListService(JdbcAttendanceListRepository jdbcAttendanceListRepository) {
        this.jdbcAttendanceListRepository = jdbcAttendanceListRepository;
    }

    public AttendanceList save(AttendanceList attendanceList) {
        String attendanceName=attendanceList.getName();
        List<String> groups= this.parseName(attendanceName);
        AttendanceList attendanceListResult=jdbcAttendanceListRepository.saveProcedureWay(attendanceList);
        for (String groupName: groups) {
            Group findGroup=jdbcGroupRepository.findByName(groupName)
                    .orElseThrow(() -> new ResourceNotFoundException(Group.class.getSimpleName(), groupName));

            GroupRef groupRef = jdbcAttendanceListRepository.saveGroupAttendanceList(new GroupRef(findGroup.getId(), attendanceList.getId()));
        }
        return attendanceListResult;
    }

    public List<AttendanceList> getAllListsByWeekAndType(Integer week, String type)
    {
        List<AttendanceList> attendanceLists=jdbcAttendanceListRepository.getAllListsByWeekAndType(week,type)
            .orElseThrow(() -> new ResourceNotFoundException(AttendanceList.class.getSimpleName()));

        return attendanceLists.stream()
                .sorted(Comparator.comparingInt(AttendanceList::getWeek))
                .collect(Collectors.toList());
    }

    public AttendanceList findById(Long id)
    {
        return jdbcAttendanceListRepository.findByIdJquery(id)
                .orElseThrow(() -> new ResourceNotFoundException(AttendanceList.class.getSimpleName(),id));
    }

    public List<String> getAllGroupsByNameAttendanceList(String name,Integer week, String type){
        Long idAttendance=null;
        List<AttendanceList> attendanceLists=jdbcAttendanceListRepository.getAllListsByWeekAndType(week,type)
                .orElseThrow(() -> new ResourceNotFoundException(AttendanceList.class.getSimpleName()));

        for (AttendanceList attendanceList: attendanceLists) {
            if(attendanceList.getName().equals(name) &&
                    attendanceList.getWeek()==week &&
                    attendanceList.getCategory().toString().equals(type))

                idAttendance=attendanceList.getId();
        }

        List<GroupRef> groupRefs=jdbcAttendanceListRepository.getAllGroupsIdByIdAttendanceList(idAttendance)
                .orElseThrow(() -> new ResourceNotFoundException(GroupRef.class.getSimpleName()));

        return groupRefs.stream()
                .map(groupRef -> jdbcGroupRepository.findById(groupRef.getGroupId())
                        .orElseThrow(() -> new ResourceNotFoundException(GroupRef.class.getSimpleName()))
                        .getName())
                .sorted()
                .collect(Collectors.toList());

    }

    private List<String> parseName(String name)
    {
        return Arrays.stream(name.split(" "))
                .filter(Pattern.compile("[0-9]{4}[A-Z]$").asPredicate())
                .collect(Collectors.toList());
    }
}
