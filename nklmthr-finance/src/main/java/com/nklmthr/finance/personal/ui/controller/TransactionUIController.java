package com.nklmthr.finance.personal.ui.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
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
import com.nklmthr.finance.personal.dao.Category;
import com.nklmthr.finance.personal.dao.Transaction;
import com.nklmthr.finance.personal.service.AccountService;
import com.nklmthr.finance.personal.service.CategoryService;
import com.nklmthr.finance.personal.service.TransactionService;

@Controller
@RequestMapping("/")
public class TransactionUIController {
	Logger logger = Logger.getLogger(TransactionUIController.class);
	@Autowired
	private AccountService accountService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/Transactions")
	public String getTransactions(Model m, @PathParam(value = "categoryId") String categoryId,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size,
			@RequestParam(defaultValue = "date,desc") String[] sort) {
		return getTransactionsByCategoryInMonth(m, null, null, null, categoryId, page, size, sort);
	}

	@GetMapping("/Transactions/{year}/{month}")
	public String getTransactionsByCategoryInMonth(Model m, @PathVariable(value = "year") Integer year,
			@PathVariable(value = "month") Integer month, @PathParam(value = "categoryId") String categoryId,
			@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "date,desc") String[] sort) {
		logger.info("Recieved year =" + year + ", month=" + month);
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
		Page<Transaction> pageTansactions = transactionService.getTransactionsByCategoryInYearAndMonth(keyword,
				pageable, categoryId, year, month);
		previousMonthYear = YearMonth.of(year, month).minusMonths(1).getYear();
		nextMonthYear = YearMonth.of(year, month).plusMonths(1).getYear();
		previousMonth = YearMonth.of(year, month).minusMonths(1).getMonth().getValue();
		nextMonth = YearMonth.of(year, month).plusMonths(1).getMonth().getValue();
		m.addAttribute("categoryId", categoryId);
		m.addAttribute("previousMonth", previousMonth);
		m.addAttribute("previousMonthYear", previousMonthYear);
		m.addAttribute("nextMonth", nextMonth);
		m.addAttribute("nextMonthYear", nextMonthYear);
		m.addAttribute("currentMonth", YearMonth.now().getMonthValue());
		m.addAttribute("currentMonthYear", YearMonth.now().getYear());
		m.addAttribute("month", month);
		m.addAttribute("monthYear", year);
		m.addAttribute("transactions", pageTansactions.getContent());
		m.addAttribute("currentPage", pageTansactions.getNumber() + 1);
		m.addAttribute("totalItems", pageTansactions.getTotalElements());
		m.addAttribute("totalPages", pageTansactions.getTotalPages());
		m.addAttribute("pageSize", size);
		m.addAttribute("sortField", sortField);
		m.addAttribute("sortDirection", sortDirection);
		m.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
		return "transactions/Transactions";
	}

	@GetMapping("/addnewTransaction")
	public String addNewTransaction(Model m) {
		String sortField = "date";
		String sortDirection = "desc";
		Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Order order = new Order(direction, sortField);
		Pageable pageable = PageRequest.of(0, 15, Sort.by(order));
		Integer year = null, month = null;
		if (year == null || year == 0) {
			year = YearMonth.now().getYear();
		}
		if (month == null || month == 0) {
			month = YearMonth.now().getMonthValue();
		}
		Page<Transaction> pageTansactions = transactionService.findAllTransactionsByMonth(pageable, year, month);
		m.addAttribute("transactions", pageTansactions.getContent());
		List<Category> categorys = categoryService.getAllCategorys();
		m.addAttribute("categoryList", categorys);
		List<Account> accounts = accountService.getAllAccounts();
		m.addAttribute("accountList", accounts);
		Transaction transaction = new Transaction();
		transaction.setDate(new Date());
		m.addAttribute("transaction", transaction);
		m.addAttribute("currentMonth", YearMonth.now().getMonthValue());
		m.addAttribute("currentMonthYear", YearMonth.now().getYear());
		m.addAttribute("transactionTypes", transactionService.getTransactionTypes());
		m.addAttribute("currentPage", pageTansactions.getNumber() + 1);
		m.addAttribute("totalItems", pageTansactions.getTotalElements());
		m.addAttribute("totalPages", pageTansactions.getTotalPages());
		m.addAttribute("pageSize", 15);
		m.addAttribute("sortField", "date");
		m.addAttribute("sortDirection", "desc");
		m.addAttribute("reverseSortDirection", "asc");
		logger.info("addNewTransaction ");
		return "transactions/AddNewTransaction";
	}

	@PostMapping("/saveAndAddnewTransaction")
	public String saveAndAddnewTransaction(@ModelAttribute("transaction") Transaction transaction, Model m) {
		saveTransaction(transaction);
		String sortField = "date";
		String sortDirection = "desc";
		Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Order order = new Order(direction, sortField);
		Pageable pageable = PageRequest.of(0, 25, Sort.by(order));
		Integer year = null, month = null;
		if (year == null || year == 0) {
			year = YearMonth.now().getYear();
		}
		if (month == null || month == 0) {
			month = YearMonth.now().getMonthValue();
		}
		Page<Transaction> pageTansactions = transactionService.findAllTransactionsByMonth(pageable, year, month);
		m.addAttribute("transactions", pageTansactions.getContent());
		List<Category> categorys = categoryService.getAllCategorys();
		m.addAttribute("categoryList", categorys);
		List<Account> accounts = accountService.getAllAccounts();
		m.addAttribute("accountList", accounts);
		Transaction newTransaction = new Transaction();
		newTransaction.setDate(new Date());
		m.addAttribute("transaction", newTransaction);
		m.addAttribute("currentMonth", YearMonth.now().getMonthValue());
		m.addAttribute("currentMonthYear", YearMonth.now().getYear());
		m.addAttribute("transactionTypes", transactionService.getTransactionTypes());
		m.addAttribute("currentPage", pageTansactions.getNumber() + 1);
		m.addAttribute("totalItems", pageTansactions.getTotalElements());
		m.addAttribute("totalPages", pageTansactions.getTotalPages());
		m.addAttribute("pageSize", 20);
		m.addAttribute("sortField", "date");
		m.addAttribute("sortDirection", "desc");
		m.addAttribute("reverseSortDirection", "asc");
		logger.info("addNewTransaction ");
		return "transactions/AddNewTransaction";
	}

	@PostMapping("/saveTransaction")
	public String saveTransaction(@ModelAttribute("transaction") Transaction transaction) {
		transactionService.saveTransaction(transaction);
		logger.info("saveTransaction " + transaction);
		return "redirect:/Transactions";
	}

	@GetMapping("/showFormForTransactionUpdate/{id}")
	public String showFormForTransactionUpdate(@PathVariable(value = "id") String id, Model m) {
		List<Category> categorys = categoryService.getAllCategorys();
		m.addAttribute("CategoryList", categorys);
		List<Account> accounts = accountService.getAllAccounts();
		m.addAttribute("accountList", accounts);
		Transaction transaction = transactionService.findTransactionById(id);
		m.addAttribute("transaction", transaction);
		m.addAttribute("transactionTypes", transactionService.getTransactionTypes());
		logger.info("showFormForTransactionUpdate ");
		return "transactions/UpdateTransaction";
	}

	@GetMapping("/deleteTransaction/{id}")
	public String deleteTransaction(@PathVariable(value = "id") String id, Model model) {
		transactionService.deleteTransaction(id);
		return "redirect:/Transactions";
	}

	@GetMapping("/splitTransaction/{id}")
	public String splitTransaction(@PathVariable(value = "id") String id, Model m) {
		Transaction parentTransaction = transactionService.findTransactionById(id);
		m.addAttribute("parentTransaction", parentTransaction);
		List<Category> categorys = categoryService.getAllCategorys();
		m.addAttribute("categoryList", categorys);
		List<Account> accounts = accountService.getAllAccounts();
		m.addAttribute("accountList", accounts);
		m.addAttribute("transactionTypes", transactionService.getTransactionTypes());
		return "transactions/SplitTransaction";
	}

	@GetMapping("/saveSplitTransaction/{id}")
	public String saveSplitTransaction(Model m, @PathVariable(value = "id") String id) {
		return "redirect:/Transactions";
	}
}
