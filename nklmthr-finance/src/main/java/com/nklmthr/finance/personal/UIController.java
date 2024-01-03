package com.nklmthr.finance.personal;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nklmthr.finance.personal.dao.Account;
import com.nklmthr.finance.personal.dao.AccountRepository;
import com.nklmthr.finance.personal.dao.AccountType;
import com.nklmthr.finance.personal.dao.AccountTypeRepository;
import com.nklmthr.finance.personal.dao.CategoryRepository;
import com.nklmthr.finance.personal.dao.Institution;
import com.nklmthr.finance.personal.dao.InstitutionRepository;
import com.nklmthr.finance.personal.dao.TransactionRepository;
import com.nklmthr.finance.personal.service.AccountRestService;

@Controller
@RequestMapping("/")
public class UIController {
	Logger logger = Logger.getLogger(AccountRestService.class);

	@Autowired
	AccountTypeRepository accountTypeRepository;

	@Autowired
	InstitutionRepository institutionRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@GetMapping("/Institutions")
	public String Institutions(Model m) {
		List<Institution> institutionPage = institutionRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("institutionList", institutionPage);
		return "institutions/Institutions";
	}

	@GetMapping("/addnewInstitution")
	public String addnewInstitution(Model m) {
		List<Institution> institutionPage = institutionRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("institutionList", institutionPage);
		Institution institution = new Institution();
		m.addAttribute("institution", institution);
		return "institutions/addnewInstitution";
	}

	@PostMapping("/saveInstitution")
	public String addnewInstitution(@ModelAttribute("institution") Institution institution) {
		institutionRepository.save(institution);
		return "redirect:/Institutions";
	}

	@GetMapping("/showFormForInstitutionUpdate/{id}")
	public String showFormForInstitutionUpdate(@PathVariable(value = "id") String id, Model model) {
		Institution i = institutionRepository.findById(id).get();
		model.addAttribute(i);
		return "institutions/UpdateInstitution";
	}

	@GetMapping("/deleteInstitution/{id}")
	public String deleteInstitution(@PathVariable(value = "id") String id, Model model) {
		institutionRepository.deleteById(id);
		return "redirect:/Institutions";
	}

	/*
	 * 
	 * Account Type
	 */

	@GetMapping("/AccountTypes")
	public String AccountTypes(Model m) {
		List<AccountType> accountTypePage = accountTypeRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("accountTypeList", accountTypePage);
		return "accountType/AccountTypes";
	}

	@GetMapping("/addnewAccountType")
	public String addnewAccountType(Model m) {
		List<AccountType> accountTypePage = accountTypeRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("accountTypeList", accountTypePage);

		AccountType accountType = new AccountType();
		m.addAttribute("accountType", accountType);
		return "accountType/addnewAccountType";
	}

	@PostMapping("/saveAccountType")
	public String addnewAccountType(@ModelAttribute("accountType") AccountType accountType) {
		accountTypeRepository.save(accountType);
		return "redirect:/AccountTypes";
	}

	@GetMapping("/showFormForAccountTypeUpdate/{id}")
	public String showFormForAccountTypeUpdate(@PathVariable(value = "id") String id, Model model) {
		AccountType i = accountTypeRepository.findById(id).get();
		model.addAttribute(i);
		return "accountType/UpdateAccountType";
	}

	@GetMapping("/deleteAccountType/{id}")
	public String deleteAccountType(@PathVariable(value = "id") String id, Model model) {
		accountTypeRepository.deleteById(id);
		return "redirect:/AccountTypes";
	}

	/*
	 * 
	 * Accounts
	 */

	@GetMapping("/Accounts")
	public String Accounts(Model m) {
		List<Account> accountPage = accountRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("accountList", accountPage);
		return "account/Accounts";
	}

	@GetMapping("/addnewAccount")
	public String addnewAccount(Model m) {
		List<AccountType> accountTypes = accountTypeRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("accountTypeList", accountTypes);

		List<Account> accountPage = accountRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("accountList", accountPage);

		Account account = new Account();
		m.addAttribute("account", account);
		return "account/addnewAccount";
	}

	@PostMapping("/saveAccount")
	public String addnewAccount(@ModelAttribute("account") Account account) {
		if (!account.getName().startsWith(account.getInstitution().getName())) {
			account.setName(account.getInstitution().getName() + "-" + account.getAccountType().getName() + "-"
					+ account.getName());
		}
		accountRepository.save(account);

		return "redirect:/Accounts";
	}

	@GetMapping("/showFormForAccountUpdate/{id}")
	public String showFormForAccountUpdate(@PathVariable(value = "id") String id, Model m) {
		List<AccountType> accountTypes = accountTypeRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("accountTypeList", accountTypes);
		List<Institution> institutionPage = institutionRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("institutionList", institutionPage);

		Account i = accountRepository.findById(id).get();
		m.addAttribute(i);
		return "account/UpdateAccount";
	}

	@GetMapping("/deleteAccount/{id}")
	public String deleteAccount(@PathVariable(value = "id") String id, Model model) {
		accountRepository.deleteById(id);
		return "redirect:/Accounts";
	}
}
