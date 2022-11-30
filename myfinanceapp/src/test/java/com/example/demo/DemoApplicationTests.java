package com.example.demo;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nklmthr.project.myfinance.model.Employee;
import com.nklmthr.project.myfinance.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest

public class DemoApplicationTests {
	@Autowired
	private EmployeeService service;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testEmployee() {
		Employee e = new Employee();
		e.setId(2L);
		e.setName("Nikhil2");
		Employee result = service.createEmployee(e);
		assertNotNull(result);
	}

}
