package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nklmthr.project.myfinance.model.AccountCategory;
import com.nklmthr.project.myfinance.service.AccountService;

@SpringBootTest(classes=com.nklmthr.project.myfinance.Application.class)
public class ApplicationTests {
	@Autowired
	private AccountService service;


	@Test
	public void testEmployee() {
		AccountCategory e = new AccountCategory();
		e.setAccountCategoryName("BANKING");
		service.createAccountCategory(e);
	}

}
