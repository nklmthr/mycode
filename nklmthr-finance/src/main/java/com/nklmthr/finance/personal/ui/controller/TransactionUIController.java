package com.nklmthr.finance.personal.ui.controller;

import java.time.YearMonth;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String getTransactions(Model m) {
		return getTransactionsByCategoryInMonth(m, null, null, null);
	}


	@GetMapping("/Transactions/{year}/{month}")
	public String getTransactionsByCategoryInMonth(Model m, @PathVariable(value = "year") Integer year,
			@PathVariable(value = "month") Integer month, @PathParam(value = "categoryId") String categoryId) {
		Integer nextMonthYear = year, nextMonth = month, previousMonthYear = year, previousMonth = month;
		if (year == null || year == 0) {
			year = YearMonth.now().getYear();
		}
		if (month == null || month == 0) {
			month = YearMonth.now().getMonthValue();
		}
		List<Transaction> transactions = transactionService.getTransactionsByCategoryInYearAndMonth(categoryId, year, month);
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
		m.addAttribute("transactions", transactions);
		return "transactions/Transactions";
	}

	@GetMapping("/addnewTransaction")
	public String addNewTransaction(Model m) {
		List<Transaction> transactions = transactionService.getLatestTransaction(10);		
		m.addAttribute("transactions", transactions);
		List<Category> categorys = categoryService.getAllCategorys();				
		m.addAttribute("categoryList", categorys);
		List<Account> accounts = accountService.getAllAccounts();
		m.addAttribute("accountList", accounts);
		Transaction transaction = new Transaction();
		transaction.setDate(new Date());
		m.addAttribute("transaction", transaction);
		m.addAttribute("transactionTypes", transactionService.getTransactionTypes());
		logger.info("addNewTransaction ");
		return "transactions/addnewTransaction";
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
