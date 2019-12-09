package com.psbd.Attendance.service;

import com.psbd.Attendance.exception.ResourceNotFoundException;
import com.psbd.Attendance.model.Group;
import com.psbd.Attendance.persistance.repository.JdbcGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class GroupService {


    private JdbcGroupRepository jdbcGroupRepository;

    @Autowired
    public GroupService(JdbcGroupRepository jdbcGroupRepository) {
        this.jdbcGroupRepository = jdbcGroupRepository;
    }

    public Group save(Group group) {
        return jdbcGroupRepository.save(group);
    }

    public List<String> findAll() {
        List<Group> groups = jdbcGroupRepository
                .findAll()
                .orElseThrow(() -> new ResourceNotFoundException(Group.class.getSimpleName()));

        return groups.stream()
                .map(group -> group.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

    public Group findById(Long id) {
        return jdbcGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException((Group.class.getSimpleName()), id));
    }
}
