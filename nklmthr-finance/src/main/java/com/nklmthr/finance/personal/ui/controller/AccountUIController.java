package com.nklmthr.finance.personal.ui.controller;

import java.time.YearMonth;
import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nklmthr.finance.personal.dao.Account;
import com.nklmthr.finance.personal.dao.AccountType;
import com.nklmthr.finance.personal.dao.Institution;
import com.nklmthr.finance.personal.dao.Transaction;
import com.nklmthr.finance.personal.service.AccountService;
import com.nklmthr.finance.personal.service.AccountTypeService;
import com.nklmthr.finance.personal.service.InstitutionService;
import com.nklmthr.finance.personal.service.TransactionService;

@Controller
@RequestMapping("/")
public class AccountUIController {
	Logger logger = Logger.getLogger(AccountUIController.class);

	@Autowired
	private AccountTypeService accountTypeService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private TransactionService transactionService;

	@Autowired
	private InstitutionService institutionService;

	@GetMapping("/account/statement/{id}")
	public String getAccountStatement(@PathVariable(value = "id") String id, Model m, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size,
			@RequestParam(defaultValue = "date,desc") String[] sort) {
		return getAccountStatementByParams(id, m, null, null, null, page, size, sort);
	}

	@GetMapping("/account/statement/{id}/{year}/{month}")
	public String getAccountStatementByParams(@PathVariable(value = "id") String id, Model m,
			@PathVariable(value = "year") Integer year, @PathVariable(value = "month") Integer month,
			@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "date,desc") String[] sort) {
		Account account = accountService.findAccountById(id);
		

		String sortField = sort[0];
		String sortDirection = sort[1];
		Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Order order = new Order(direction, sortField);
		Pageable pageable = PageRequest.of(page - 1, size, Sort.by(order));
		Integer nextMonthYear = year, nextMonth = month, previousMonthYear = year, previousMonth = month;
		if (year == null || year == 0) {
			year = YearMonth.now().getYear();
		}
		if (month == null || month == 0) {
			month = YearMonth.now().getMonthValue();
		}
		logger.info("year =" + year + ", month=" + month);
		Page<Transaction> transactions = transactionService.findAllTransactionsByAccount(pageable, account);
		previousMonthYear = YearMonth.of(year, month).minusMonths(1).getYear();
		nextMonthYear = YearMonth.of(year, month).plusMonths(1).getYear();
		previousMonth = YearMonth.of(year, month).minusMonths(1).getMonth().getValue();
		nextMonth = YearMonth.of(year, month).plusMonths(1).getMonth().getValue();
		m.addAttribute("previousMonth", previousMonth);
		m.addAttribute("previousMonthYear", previousMonthYear);
		m.addAttribute("nextMonth", nextMonth);
		m.addAttribute("nextMonthYear", nextMonthYear);
		m.addAttribute("currentMonth", YearMonth.now().getMonthValue());
		m.addAttribute("currentMonthYear", YearMonth.now().getYear());
		m.addAttribute("month", month);
		m.addAttribute("monthYear", year);
		m.addAttribute("transactions", transactions.getContent());
		m.addAttribute("currentPage", transactions.getNumber() + 1);
		m.addAttribute("totalItems", transactions.getTotalElements());
		m.addAttribute("totalPages", transactions.getTotalPages());
		m.addAttribute("pageSize", size);
		m.addAttribute("sortField", sortField);
		m.addAttribute("sortDirection", sortDirection);
		m.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		return "account/Statement.html";
	}

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
		return "account/AddnewAccount";
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
