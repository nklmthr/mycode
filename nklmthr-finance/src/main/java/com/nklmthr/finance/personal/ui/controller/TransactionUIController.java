package com.nklmthr.finance.personal.ui.controller;

import java.time.YearMonth;
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
	
	@GetMapping("transactions/ListTransactionsFragment")
	public String getListTransactionsFragment() {
		return "transactions/ListTransactionsFragment";
	}
	
	@GetMapping("/Transactions")
	public String getTransactions(Model m, @RequestParam(required = false) String keyword,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int size,
			@RequestParam(defaultValue = "date,desc") String[] sort) {
		return getTransactionsByCategoryInMonth(m, null, null, null, keyword, page, size, sort);
	}

	@GetMapping("/Transactions/{year}/{month}")
	public String getTransactionsByCategoryInMonth(Model m, @PathVariable(value = "year") Integer year,
			@PathVariable(value = "month") Integer month, @PathParam(value = "categoryId") String categoryId,
			@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "date,desc") String[] sort) {

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
		logger.info("keyword ="+keyword+", pageable="+pageable);
		Page<Transaction> pageTansactions = transactionService.getTransactionsByCategoryInYearAndMonth(keyword,
				pageable, categoryId, year, month);
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
		Page<Transaction> pageTansactions = transactionService.getLatestTransaction(10);
		m.addAttribute("transactions", pageTansactions.getContent());
		List<Category> categorys = categoryService.getAllCategorys();
		m.addAttribute("categoryList", categorys);
		List<Account> accounts = accountService.getAllAccounts();
		m.addAttribute("accountList", accounts);
		Transaction transaction = new Transaction();
		transaction.setDate(new Date());
		m.addAttribute("transaction", transaction);
		m.addAttribute("transactionTypes", transactionService.getTransactionTypes());
		m.addAttribute("currentPage", pageTansactions.getNumber() + 1);
		m.addAttribute("totalItems", pageTansactions.getTotalElements());
		m.addAttribute("totalPages", pageTansactions.getTotalPages());
		m.addAttribute("pageSize", 1);
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
		Transaction transaction = transactionService.findTransactioById(id);
		m.addAttribute("transaction", transaction);
		m.addAttribute("transactionTypes", transactionService.getTransactionTypes());
		logger.info("showFormForTransactionUpdate ");
		return "transactions/UpdateTransaction";
	}

	@GetMapping("/deleteTransaction/{id}")
	public String deleteTransaction(@PathVariable(value = "id") String id, Model model) {
		transactionService.deleteTransactionById(id);
		logger.info("deleteTransaction " + id);
		return "redirect:/Transactions";
	}
}
