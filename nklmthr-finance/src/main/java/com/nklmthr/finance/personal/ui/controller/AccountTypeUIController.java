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

import com.nklmthr.finance.personal.dao.AccountType;
import com.nklmthr.finance.personal.service.AccountTypeService;

@Controller
@RequestMapping("/")
public class AccountTypeUIController {
	Logger logger = Logger.getLogger(AccountTypeUIController.class);

	@Autowired
	private AccountTypeService accountTypeService;

	@GetMapping("/AccountTypes")
	public String getAccountTypes(Model m) {
		List<AccountType> accountTypeList = accountTypeService.getAllAccountTypes();
		m.addAttribute("accountTypeList", accountTypeList);
		logger.info("AccountTypes size:" + accountTypeList.size());
		return "accountType/AccountTypes";
	}

	@GetMapping("/addnewAccountType")
	public String addnewAccountType(Model m) {
		List<AccountType> accountTypeList = accountTypeService.getAllAccountTypes();
		m.addAttribute("accountTypeList", accountTypeList);
		AccountType accountType = new AccountType();
		m.addAttribute("accountType", accountType);
		logger.info("addnewAccountType existing size:" + accountTypeList.size());
		return "accountType/addnewAccountType";
	}

	@PostMapping("/saveAccountType")
	public String saveAccountType(@ModelAttribute("accountType") AccountType accountType) {
		accountTypeService.save(accountType);
		logger.info("saveAccountType " + accountType.getName());
		return "redirect:/AccountTypes";
	}

	@GetMapping("/showFormForAccountTypeUpdate/{id}")
	public String showFormForAccountTypeUpdate(@PathVariable(value = "id") String id, Model model) {
		List<AccountType> accountTypeList = accountTypeService.getAllAccountTypes();
		model.addAttribute("accountTypeList", accountTypeList);
		AccountType i = accountTypeService.findAccountTypeById(id);
		model.addAttribute(i);
		logger.info("showFormForAccountTypeUpdate existing size:" + accountTypeList.size());
		return "accountType/UpdateAccountType";
	}

	@GetMapping("/deleteAccountType/{id}")
	public String deleteAccountType(@PathVariable(value = "id") String id, Model model) {
		accountTypeService.deleteAccountTypeById(id);
		logger.info("deleteAccountType " + id);
		return "redirect:/AccountTypes";
	}
}
