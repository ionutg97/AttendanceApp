package com.psbd.Attendance.service;

import com.psbd.Attendance.exception.ResourceNotFoundException;
import com.psbd.Attendance.model.AttendanceItem;
import com.psbd.Attendance.model.Student;
import com.psbd.Attendance.persistance.repository.JdbcAttendanceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceItemService {

    @Autowired
    private JdbcAttendanceItemRepository attendanceItemRepository;

//    public void saveNewAttendanceItem(AttendanceItem attendanceItem) {
//        attendanceItemRepository.save(attendanceItem);
//    }
//
//    public void addNewStudentOnAttendanceItem(AttendanceItem attendanceItem, Student student) {
//        //  attendanceItemRepository.saveNewStudent(this.findById(attendanceItem.getId()),student);
//    }
//
//    public AttendanceItem findById(Long attendanceItemId) {
//        return attendanceItemRepository
//                .findById(attendanceItemId)
//                .orElseThrow(() -> new ResourceNotFoundException(AttendanceItem.class.getSimpleName(), attendanceItemId));
//    }

}
