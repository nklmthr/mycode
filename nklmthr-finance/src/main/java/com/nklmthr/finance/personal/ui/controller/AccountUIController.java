package com.nklmthr.finance.personal.ui.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nklmthr.finance.personal.dao.Account;
import com.nklmthr.finance.personal.dao.AccountType;
import com.nklmthr.finance.personal.dao.Institution;
import com.nklmthr.finance.personal.service.AccountService;
import com.nklmthr.finance.personal.service.AccountTypeService;
import com.nklmthr.finance.personal.service.InstitutionService;

@Controller
@RequestMapping("/")
public class AccountUIController {
	Logger logger = Logger.getLogger(AccountUIController.class);

	@Autowired
	private AccountTypeService accountTypeService;
	@Autowired
	private AccountService accountService;

	@Autowired
	private InstitutionService institutionService;

	@GetMapping("/Accounts")
	public String getAccounts(Model m) {
		List<Account> accountList = accountService.getAllAccounts();
		m.addAttribute("accountList", accountList);
		logger.info("getAccounts size:" + accountList.size());
		return "account/Accounts";
	}

	@GetMapping("/addnewAccount")
	public String addnewAccount(Model m) {
		List<AccountType> accountTypes = accountTypeService.getAllAccountTypes();
		m.addAttribute("accountTypeList", accountTypes);
		List<Institution> institutionPage = institutionService.getAllInstitutions();
		m.addAttribute("institutionList", institutionPage);
		List<Account> accountPage = accountService.getAllAccounts();
		m.addAttribute("accountList", accountPage);
		logger.info("addnewAccount ");
		Account account = new Account();
		m.addAttribute("account", account);
		return "account/addnewAccount";
	}

	@PostMapping("/saveAccount")
	public String saveAccount(@ModelAttribute("account") Account account) {		
		accountService.saveAccount(account);
		logger.info("saveAccount " + account.getName());
		return "redirect:/Accounts";
	}

	@GetMapping("/showFormForAccountUpdate/{id}")
	public String showFormForAccountUpdate(@PathVariable(value = "id") String id, Model m) {
		List<AccountType> accountTypes = accountTypeService.getAllAccountTypes();
		m.addAttribute("accountTypeList", accountTypes);
		List<Institution> institutionPage = institutionService.getAllInstitutions();
		m.addAttribute("institutionList", institutionPage);
		logger.info("showFormForAccountUpdate ");
		Account i = accountService.findAccountById(id);
		m.addAttribute(i);
		return "account/UpdateAccount";
	}

	@GetMapping("/deleteAccount/{id}")
	public String deleteAccount(@PathVariable(value = "id") String id, Model model) {
		accountService.deleteAccountById(id);
		logger.info("deleteAccount " + id);
		return "redirect:/Accounts";
	}

}
