package com.example.demo;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nklmthr.project.myfinance.model.Account;
import com.nklmthr.project.myfinance.service.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest

public class DemoApplicationTests {
	@Autowired
	private AccountService service;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testEmployee() {
		Account e = new Account();
		
		Account result = service.createEmployee(e);
		assertNotNull(result);
	}

}
