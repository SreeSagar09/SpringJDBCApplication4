package com.app.test;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.app.Employee;
import com.app.EmployeeRowMapper;
import com.app.config.AppConfig;
import com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type;

public class Test {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		
		JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
		
		String sql1 = "select e.employee_name from employee e where e.employee_id=1";
		String employeeName1 = jdbcTemplate.queryForObject(sql1, String.class);
		System.out.println(employeeName1);
		
		EmployeeRowMapper employeeRowMapper = new EmployeeRowMapper();
		
		String sql2 = "select * from employee e where e.employee_id=2";
		Employee employee1 = jdbcTemplate.queryForObject(sql2, employeeRowMapper);
		System.out.println(employee1);
		
		String sql3 = "select e.employee_code from employee e where e.employee_id=?";
		String employeeCode1 = jdbcTemplate.queryForObject(sql3, String.class, 3);
		System.out.println(employeeCode1);
		
		String sql4 = "select * from employee e where e.employee_name=?";
		Employee employee2 = jdbcTemplate.queryForObject(sql4, employeeRowMapper, "Aravind");
		System.out.println(employee2);
		
		
		String sql5 = "select e.employee_id from employee e where e.employee_id=? and e.employee_name=?";
		Object[] arguments1 = {5, "Pavan"};
		int[] argumenTypes1 = {Types.INTEGER, Types.VARCHAR};
		Integer employeeId1 = jdbcTemplate.queryForObject(sql5, arguments1, argumenTypes1, Integer.class);
		System.out.println(employeeId1);
		
		String sql6 = "select * from employee e where e.employee_id=? and e.employee_name=?";
		Object[] arguments2 = {5, "Pavan"};
		int[] argumenTypes2 = {Types.INTEGER, Types.VARCHAR};
		Employee employee3 = jdbcTemplate.queryForObject(sql6, arguments2, argumenTypes2, employeeRowMapper);
		System.out.println(employee3);
		
		String sql7 = "select e.employee_name from employee e where e.employee_id=? and e.employee_name=?";
		Object[] arguments3 = {1, "Sree Sagar"};
		String employeeName2 = jdbcTemplate.queryForObject(sql7, arguments3, String.class);
		System.out.println(employeeName2);
		
		String sql8 = "select * from employee e where e.employee_code=? and e.employee_name=?";
		Object[] arguments4 = {"A002", "Sathish"};
		Employee employee4 = jdbcTemplate.queryForObject(sql8, arguments4, employeeRowMapper);
		System.out.println(employee4);
		
	}
}
