package com.nklmthr.finance.personal.ui.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
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
import org.springframework.web.multipart.MultipartFile;

import com.nklmthr.finance.personal.dao.Account;
import com.nklmthr.finance.personal.dao.Category;
import com.nklmthr.finance.personal.dao.Transaction;
import com.nklmthr.finance.personal.dao.TransactionAttachment;
import com.nklmthr.finance.personal.service.AccountService;
import com.nklmthr.finance.personal.service.CategoryService;
import com.nklmthr.finance.personal.service.TransactionService;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;

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
	public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

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
		Integer nextMonthYear, nextMonth, previousMonthYear, previousMonth;
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

	@GetMapping("/addnewTransaction/{year}/{month}")
	public String addNewTransaction(Model m, @PathVariable(value = "year") Integer year,
			@PathVariable(value = "month") Integer month,
			@RequestParam(value = "page", defaultValue = "1") Integer page) {
		String sortField = "date";
		String sortDirection = "desc";
		Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Order order = new Order(direction, sortField);
		Pageable pageable = PageRequest.of(0, 15, Sort.by(order));
		if (year == null || year == 0) {
			year = YearMonth.now().getYear();
		}
		if (month == null || month == 0) {
			month = YearMonth.now().getMonthValue();
		}
		Page<Transaction> pageTansactions = transactionService.findAllTransactionsByMonth(pageable, year, month);
		m.addAttribute("transactions", pageTansactions.getContent());
		List<Category> categorys = categoryService.getAllCategoryExcludingHidden();
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

	@PostMapping("/saveAndAddnewTransaction/{year}/{month}")
	public String saveAndAddnewTransaction(@ModelAttribute("transaction") Transaction transaction, Model m,
			@PathVariable(value = "year") Integer year, @PathVariable(value = "month") Integer month,
			@RequestParam(value = "page", defaultValue = "1") Integer page) {
		saveTransaction(transaction, year, month, page);
		String sortField = "date";
		String sortDirection = "desc";
		Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Order order = new Order(direction, sortField);
		Pageable pageable = PageRequest.of(0, 25, Sort.by(order));
		if (year == null || year == 0) {
			year = YearMonth.now().getYear();
		}
		if (month == null || month == 0) {
			month = YearMonth.now().getMonthValue();
		}
		Page<Transaction> pageTansactions = transactionService.findAllTransactionsByMonth(pageable, year, month);
		m.addAttribute("transactions", pageTansactions.getContent());
		List<Category> categorys = categoryService.getAllCategory();
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

	@PostMapping("/saveTransaction/{year}/{month}")
	public String saveTransaction(@ModelAttribute("transaction") Transaction transaction,
			@PathVariable(value = "year") Integer year, @PathVariable(value = "month") Integer month,
			@RequestParam(value = "page", defaultValue = "1") Integer page) {
		if(StringUtils.isBlank(transaction.getCurrency())){
			transaction.setCurrency("INR");
		}
		transactionService.saveTransaction(transaction);
		logger.info("saveTransaction " + transaction);
		return "redirect:/Transactions/" + year + "/" + month + "?&page=" + page;
	}

	@GetMapping("/showFormForTransactionUpdate/{id}/{year}/{month}")
	public String showFormForTransactionUpdate(@PathVariable(value = "id") String id,
			@PathVariable(value = "year") Integer year, @PathVariable(value = "month") Integer month,
			@RequestParam(value = "page", defaultValue = "1") Integer page, Model m) throws IOException {
		List<Category> categorys = categoryService.getAllCategory();
		m.addAttribute("CategoryList", categorys);
		List<Account> accounts = accountService.getAllAccounts();
		m.addAttribute("accountList", accounts);
		Transaction transaction = transactionService.findTransactionById(id);
		m.addAttribute("transaction", transaction);
		m.addAttribute("transactionTypes", transactionService.getTransactionTypes());
		m.addAttribute("year", year);
		m.addAttribute("month", month);
		m.addAttribute("page", page);
		List<TransactionAttachment> transactionAttachments = transactionService.getTransactionAttachments(id);
		m.addAttribute("transactionAttachments", transactionAttachments);
		logger.info("transactionAttachments" + transactionAttachments);
		logger.info("showFormForTransactionUpdate ");
		return "transactions/UpdateTransaction";
	}

	@GetMapping("/deleteTransaction/{id}/{year}/{month}")
	public String deleteTransaction(@PathVariable(value = "id") String id, @PathVariable(value = "year") Integer year,
			@PathVariable(value = "month") Integer month,
			@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
		transactionService.deleteTransaction(id);
		return "redirect:/Transactions/" + year + "/" + month + "?page=" + page;
	}

	@GetMapping("/openTransferPage/{id}/{year}/{month}")
	public String openTransferPageForTransaction(@PathVariable(value = "id") String id,
			@PathVariable(value = "year") Integer year, @PathVariable(value = "month") Integer month,
			@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
		Transaction transaction = transactionService.getTransactionById(id);
		List<Account> accounts = accountService.getAllAccounts();
		List<Category> categorys = categoryService.getAllCategory();
		model.addAttribute("categorys", categorys);
		model.addAttribute("transaction", transaction);
		model.addAttribute("accounts", accounts);
		model.addAttribute("transactionTypes", transactionService.getTransactionTypes());
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("page", page);
		return "transactions/Transfer";
	}

	@PostMapping("/saveTransactionTransfer/{year}/{month}")
	public String saveTransactionTransfer(@ModelAttribute("transaction") Transaction transaction,
			@RequestParam(value = "transferToAccount") String transferToAccount,
			@PathVariable(value = "year") Integer year, @PathVariable(value = "month") Integer month,
			@RequestParam(value = "page", defaultValue = "1") Integer page) {
		transactionService.performTransferOperation(transaction, transferToAccount);
		return "redirect:/Transactions/" + year + "/" + month + "?&page=" + page;
	}

	@GetMapping("/splitTransaction/{id}/{year}/{month}")
	public String splitTransaction(@PathVariable(value = "id") String id, @PathVariable(value = "year") Integer year,
			@PathVariable(value = "month") Integer month,
			@RequestParam(value = "page", defaultValue = "1") Integer page, Model m) {
		Transaction parentTransaction = transactionService.findTransactionById(id);
		m.addAttribute("parentTransaction", parentTransaction);
		List<Category> categorys = categoryService.getAllCategoryExcludingHidden();
		m.addAttribute("categoryList", categorys);
		List<Account> accounts = accountService.getAllAccounts();
		m.addAttribute("accountList", accounts);
		m.addAttribute("transactionTypes", transactionService.getTransactionTypes());
		return "transactions/SplitTransaction";
	}

	@PostMapping("transaction/{id}/upload/{year}/{month}")
	public String uploadImage(Model model, @RequestParam("image") MultipartFile file,
			@PathVariable(value = "id") String id, @PathVariable(value = "year") Integer year,
			@PathVariable(value = "month") Integer month,
			@RequestParam(value = "page", defaultValue = "1") Integer page) throws IOException {
		TransactionAttachment attachment = new TransactionAttachment();
		Transaction t = transactionService.getTransactionById(id);
		attachment.setTransaction(t);
		attachment.setFileName(file.getOriginalFilename());
		attachment.setDate(new Date());
		attachment.setImageData(file.getBytes());
		transactionService.addAttachment(t, attachment);
		return "redirect:/showFormForTransactionUpdate/" + id + "/" + year + "/" + month + "?page=" + page;
	}

	@GetMapping("/transaction/{transactionId}/deleteAttachment/{attachmentId}/{year}/{month}")
	public String deleteAttachment(Model model, @PathVariable(value = "transactionId") String transactionId,
			@PathVariable(value = "attachmentId") String attachmentId, @PathVariable(value = "year") Integer year,
			@PathVariable(value = "month") Integer month,
			@RequestParam(value = "page", defaultValue = "1") Integer page) throws IOException {
		transactionService.deleteTransactionAttachmentById(attachmentId);
		return "redirect:/showFormForTransactionUpdate/" + transactionId + "/" + year + "/" + month + "?page=" + page;
	}

	@GetMapping("/transaction/{transactionId}/attachment/{attachmentId}/{year}/{month}")
	public void getAttachment(Model model, @PathVariable(value = "transactionId") String transactionId,
			@PathVariable(value = "attachmentId") String attachmentId, @PathVariable(value = "year") Integer year,
			@PathVariable(value = "month") Integer month,
			@RequestParam(value = "page", defaultValue = "1") Integer page, HttpServletResponse response)
			throws IOException {
		byte[] attachment = transactionService.getTransactionAttachmentById(attachmentId);
		IOUtils.copy(new ByteArrayInputStream(attachment), response.getOutputStream());
	}
}
