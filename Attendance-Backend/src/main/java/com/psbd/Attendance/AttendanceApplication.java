package com.psbd.Attendance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class AttendanceApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(AttendanceApplication.class, args);
	}

}
