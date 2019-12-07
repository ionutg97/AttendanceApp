package com.psbd.Attendance.service;

import com.psbd.Attendance.dto.AttendanceItemDto;
import com.psbd.Attendance.exception.ResourceNotFoundException;
import com.psbd.Attendance.mapper.AttendanceItemMapper;
import com.psbd.Attendance.model.AttendanceItem;
import com.psbd.Attendance.model.AttendanceList;
import com.psbd.Attendance.model.Student;
import com.psbd.Attendance.persistance.repository.JdbcAttendanceItemRepository;
import com.psbd.Attendance.persistance.repository.JdbcStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceItemService {


    private JdbcAttendanceItemRepository attendanceItemRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private JdbcStudentRepository  jdbcStudentRepository;

    @Autowired
    private AttendanceItemMapper attendanceItemMapper;

    @Autowired
    private AttendanceListService attendanceListService;

    @Autowired
    public AttendanceItemService(JdbcAttendanceItemRepository attendanceItemRepository) {
        this.attendanceItemRepository = attendanceItemRepository;
        System.out.println("Constructor AttendanceItemService");
    }

    public AttendanceItem saveNewAttendanceItem(String attendanceName,Student student) {
        AttendanceList attendanceList=new AttendanceList(null,attendanceName,null,null);

        AttendanceItem attendanceItem=new AttendanceItem();
        attendanceItem.addStudent(student);
        attendanceItem.setDetails("p");
        attendanceItem.setGrade(0);
        attendanceItem.setTeacher(null);
        attendanceItem.setClassroom(null);
        attendanceItem.setAttendanceList(attendanceList);

       return attendanceItemRepository.saveOneStudent(attendanceItem);
    }

    public AttendanceItem updateAttendanceItem(AttendanceItem attendanceItem) {

        attendanceItem = isDetailsNull(attendanceItem);
        attendanceItem=isGradeNull(attendanceItem);
        //if students exists
        if(studentService.findStudentOnAttendance(attendanceItem.getAttendanceList().getId(),attendanceItem.getStudents().get(0)))
            return this.attendanceItemRepository.update(attendanceItem)
                    .orElseThrow(() -> new ResourceNotFoundException(AttendanceItem.class.getSimpleName()));
        else
            return this.attendanceItemRepository.saveOneStudent(attendanceItem);
    }



    public List<AttendanceItemDto> findAllById(Long attendanceId) {
        List<AttendanceItem> attendanceItems =this.attendanceItemRepository.findAllById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException(AttendanceItem.class.getSimpleName()));

        AttendanceList attendanceList=attendanceListService.findById(attendanceId);
        for (AttendanceItem attendanceItem: attendanceItems)
        {
            Student student= jdbcStudentRepository.findById(attendanceItem.getStudents().get(0).getId())
                    .orElseThrow(() -> new ResourceNotFoundException(JdbcStudentRepository.class.getSimpleName(),attendanceId));
            attendanceItem.getStudents().remove(0);
            attendanceItem.addStudent(student);
            attendanceItem.setAttendanceList(attendanceList);
        }

        return attendanceItems.stream()
                .map(item -> attendanceItemMapper.map(item))
                .sorted(Comparator.comparing(AttendanceItemDto::getNameStudent, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    private AttendanceItem isGradeNull(AttendanceItem attendanceItem){
        if(attendanceItem.getGrade()== null )
            attendanceItem.setGrade(0);
        return attendanceItem;
    }

    private AttendanceItem isDetailsNull(AttendanceItem attendanceItem){
        if(attendanceItem.getDetails()==null || attendanceItem.getDetails()=="")
            attendanceItem.setDetails("p");
        return attendanceItem;

    }
}
