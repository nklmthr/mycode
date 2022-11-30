package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.config.BootstrapMode;

import com.nklmthr.project.myfinance.model.AccountCategory;
import com.nklmthr.project.myfinance.service.AccountService;

@SpringBootTest(classes = com.nklmthr.project.myfinance.Application.class)

public class ApplicationTests {
	@Autowired
	private AccountService service;

	@Test
	@Transactional
	public void testAccountCategoryCreate() {
		AccountCategory e = new AccountCategory();
		e.setAccountCategoryName("BANKING");
		service.createAccountCategory(e);
	}

	@Test
	public void testAccountCategoryGet() {
		List<AccountCategory> result = service.getAccountCategories();
		assertNotNull(result);
	}

}
