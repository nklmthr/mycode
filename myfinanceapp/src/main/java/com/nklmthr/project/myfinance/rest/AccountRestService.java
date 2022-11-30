package com.nklmthr.project.myfinance.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nklmthr.project.myfinance.model.Account;
import com.nklmthr.project.myfinance.model.AccountCategory;
import com.nklmthr.project.myfinance.service.AccountService;

@Controller
public class AccountRestService {
	
	@Autowired
	private AccountService service;

	@GetMapping("/account")
    @ResponseBody
    public List<Account> getAccounts() {
        return service.getAccounts();
	}
	
	@GetMapping("/acccountcategory")
	@ResponseBody
    public List<AccountCategory> getAccountCategories() {
		return service.getAccountCategories();
	}
	
	@PostMapping("/accountCategory")
	public void createAccountCategory(AccountCategory accountCategory) {
		service.createAccountCategory(accountCategory);
	}
}
