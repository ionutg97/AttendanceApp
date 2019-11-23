package com.psbd.Attendance.repository;

import com.psbd.Attendance.AttendanceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcStudentRepository {

    private static final Logger log = LoggerFactory.getLogger(AttendanceApplication.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createTableTest() {
        log.info("create table books");
        jdbcTemplate.execute("CREATE TABLE books(" +
                "idb numeric, name VARCHAR(255), price NUMERIC(15, 2))");
    }
}
