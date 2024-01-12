package com.nklmthr.finance.personal;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

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
import com.nklmthr.finance.personal.dao.AccountRepository;
import com.nklmthr.finance.personal.dao.AccountType;
import com.nklmthr.finance.personal.dao.AccountTypeRepository;
import com.nklmthr.finance.personal.dao.Category;
import com.nklmthr.finance.personal.dao.CategoryRepository;
import com.nklmthr.finance.personal.dao.CategorySpends;
import com.nklmthr.finance.personal.dao.Institution;
import com.nklmthr.finance.personal.dao.InstitutionRepository;
import com.nklmthr.finance.personal.dao.Transaction;
import com.nklmthr.finance.personal.dao.TransactionRepository;
import com.nklmthr.finance.personal.service.AccountRestService;
import com.nklmthr.finance.personal.service.FinanceService;

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

	@Autowired
	private FinanceService financeService;

	@GetMapping("/")
	public String index(Model m) {
		return getCategorySpendsByMonth(m, null, null);
	}

	/*
	 * @GetMapping("/home") public String getHome(Model m) {
	 * 
	 * return "index"; }
	 */

	/**
	 * Institutions
	 * 
	 * @param m
	 * @return
	 */
	@GetMapping("/Institutions")
	public String getInstitutions(Model m) {
		List<Institution> institutions = institutionRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("institutionList", institutions);
		logger.info("getInstitutions size:" + institutions.size());
		return "institutions/Institutions";
	}

	@GetMapping("/addnewInstitution")
	public String addnewInstitution(Model m) {
		logger.info("addnewInstitution()");
		List<Institution> institutionPage = institutionRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("institutionList", institutionPage);
		Institution institution = new Institution();
		m.addAttribute("institution", institution);
		return "institutions/addnewInstitution";
	}

	@PostMapping("/saveInstitution")
	public String addnewInstitution(@ModelAttribute("institution") Institution institution) {
		institutionRepository.save(institution);
		logger.info("saveInstitution " + institution.getName());
		return "redirect:/Institutions";
	}

	@GetMapping("/showFormForInstitutionUpdate/{id}")
	public String showFormForInstitutionUpdate(@PathVariable(value = "id") String id, Model model) {
		Institution i = institutionRepository.findById(id).get();
		model.addAttribute(i);
		logger.info("showFormForInstitutionUpdate " + i.getName());
		return "institutions/UpdateInstitution";
	}

	@GetMapping("/deleteInstitution/{id}")
	public String deleteInstitution(@PathVariable(value = "id") String id, Model model) {
		institutionRepository.deleteById(id);
		logger.info("deleteInstitution " + id);
		return "redirect:/Institutions";
	}

	/*
	 * 
	 * Account Type
	 */

	@GetMapping("/AccountTypes")
	public String getAccountTypes(Model m) {
		List<AccountType> accountTypeList = accountTypeRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("accountTypeList", accountTypeList);
		logger.info("AccountTypes size:" + accountTypeList.size());
		return "accountType/AccountTypes";
	}

	@GetMapping("/addnewAccountType")
	public String addnewAccountType(Model m) {
		List<AccountType> accountTypeList = accountTypeRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("accountTypeList", accountTypeList);
		AccountType accountType = new AccountType();
		m.addAttribute("accountType", accountType);
		logger.info("addnewAccountType existing size:" + accountTypeList.size());
		return "accountType/addnewAccountType";
	}

	@PostMapping("/saveAccountType")
	public String saveAccountType(@ModelAttribute("accountType") AccountType accountType) {
		accountTypeRepository.save(accountType);
		logger.info("saveAccountType " + accountType.getName());
		return "redirect:/AccountTypes";
	}

	@GetMapping("/showFormForAccountTypeUpdate/{id}")
	public String showFormForAccountTypeUpdate(@PathVariable(value = "id") String id, Model model) {
		List<AccountType> accountTypeList = accountTypeRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		model.addAttribute("accountTypeList", accountTypeList);
		AccountType i = accountTypeRepository.findById(id).get();
		model.addAttribute(i);
		logger.info("showFormForAccountTypeUpdate existing size:" + accountTypeList.size());
		return "accountType/UpdateAccountType";
	}

	@GetMapping("/deleteAccountType/{id}")
	public String deleteAccountType(@PathVariable(value = "id") String id, Model model) {
		accountTypeRepository.deleteById(id);
		logger.info("deleteAccountType " + id);
		return "redirect:/AccountTypes";
	}

	/*
	 * 
	 * Accounts
	 */

	@GetMapping("/Accounts")
	public String getAccounts(Model m) {
		List<Account> accountList = accountRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("accountList", accountList);
		logger.info("getAccounts size:" + accountList.size());
		return "account/Accounts";
	}

	@GetMapping("/addnewAccount")
	public String addnewAccount(Model m) {
		List<AccountType> accountTypes = accountTypeRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("accountTypeList", accountTypes);
		List<Institution> institutionPage = institutionRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("institutionList", institutionPage);
		List<Account> accountPage = accountRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("accountList", accountPage);
		logger.info("addnewAccount ");
		Account account = new Account();
		m.addAttribute("account", account);
		return "account/addnewAccount";
	}

	@PostMapping("/saveAccount")
	public String saveAccount(@ModelAttribute("account") Account account) {
		if (!account.getName().startsWith(account.getInstitution().getName())) {
			account.setName(account.getInstitution().getName() + "-" + account.getAccountType().getName() + "-"
					+ account.getName());
		}
		accountRepository.save(account);
		logger.info("saveAccount " + account.getName());
		return "redirect:/Accounts";
	}

	@GetMapping("/showFormForAccountUpdate/{id}")
	public String showFormForAccountUpdate(@PathVariable(value = "id") String id, Model m) {
		List<AccountType> accountTypes = accountTypeRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("accountTypeList", accountTypes);
		List<Institution> institutionPage = institutionRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("institutionList", institutionPage);
		logger.info("showFormForAccountUpdate ");
		Account i = accountRepository.findById(id).get();
		m.addAttribute(i);
		return "account/UpdateAccount";
	}

	@GetMapping("/deleteAccount/{id}")
	public String deleteAccount(@PathVariable(value = "id") String id, Model model) {
		accountRepository.deleteById(id);
		logger.info("deleteAccount " + id);
		return "redirect:/Accounts";
	}

	/*
	 * 
	 * Categories
	 */

	@GetMapping("/category/categoryFragment")
	public String getCategoryFragment(Model m) {
		return "category/categoryFragment";
	}

	@GetMapping("/Categorys")
	public String getCategorys(Model m) {
		List<Category> categoryList = categoryRepository
				.findAll(Sort.by(Direction.ASC, "level").and(Sort.by(Sort.Direction.ASC, "parentCategory")));
		Collections.sort(categoryList);
		m.addAttribute("categoryList", categoryList.get(0));
		logger.info("getCategorys size:" + categoryList.size());
		return "category/Categorys";
	}

	@GetMapping("/addnewCategory")
	public String addnewCategory(Model m) {
		List<Category> categorysList = categoryRepository
				.findAll(Sort.by(Direction.ASC, "level").and(Sort.by(Sort.Direction.ASC, "parentCategory")));
		m.addAttribute("CategoryList", categorysList);
		logger.info("addnewCategory");
		Category category = new Category();
		m.addAttribute("category", category);
		return "category/addnewCategory";
	}

	@PostMapping("/saveCategory")
	public String saveCategory(@ModelAttribute("category") Category category) {
		Category temp = (Category) category.clone();
		int level = 0;
		while (temp.getParentCategory() != null) {
			temp = temp.getParentCategory();
			level++;
		}
		category.setLevel(level);
		categoryRepository.save(category);
		logger.info("saveCategory " + category.getName());
		return "redirect:/Categorys";
	}

	@GetMapping("/showFormForCategoryUpdate/{id}")
	public String showFormForCategoryUpdate(@PathVariable(value = "id") String id, Model m) {
		List<Category> Categorys = categoryRepository
				.findAll(Sort.by(Direction.ASC, "level").and(Sort.by(Sort.Direction.ASC, "parentCategory")));
		m.addAttribute("CategoryList", Categorys);
		Category c = categoryRepository.findById(id).get();
		m.addAttribute("category", c);
		logger.info("showFormForCategoryUpdate ");
		return "category/UpdateCategory";
	}

	@GetMapping("/deleteCategory/{id}")
	public String deleteCategory(@PathVariable(value = "id") String id, Model model) {
		categoryRepository.deleteById(id);
		logger.info("deleteCategory " + id);
		return "redirect:/Categorys";
	}

	/*
	 * 
	 * Transactions
	 */
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
		previousMonthYear = YearMonth.of(year, month).minusMonths(1).getYear();
		nextMonthYear = YearMonth.of(year, month).plusMonths(1).getYear();
		previousMonth = YearMonth.of(year, month).minusMonths(1).getMonth().getValue();
		nextMonth = YearMonth.of(year, month).plusMonths(1).getMonth().getValue();
		List<Transaction> transactionList = new ArrayList<>();
		if (categoryId != null) {
			Category category = categoryRepository.findById(categoryId).get();
			transactionList.addAll(transactionRepository.findAllTransactionsInCategoryByMonth(year, month, categoryId));
			List<Category> categories = new ArrayList<Category>();
			Queue<Category> queue = new LinkedList<Category>();
			queue.add(category);
			while (!queue.isEmpty()) {
				logger.info("queue Size:" + queue.size() + " category sz:" + categories.size());
				Category current = queue.poll();
				categories.addAll(current.getChildCategorys());
				queue.addAll(current.getChildCategorys());
			}
			logger.info("Total Child Categories" + categories.size());
			transactionList.addAll(transactionRepository.findAllTransactionsInCategoriesByMonth(year, month,
					categories.stream().map(s -> s.getId()).collect(Collectors.toList())));
		} else {
			transactionList = transactionRepository.findAllTransactionsByMonth(year, month);
		}
		m.addAttribute("previousMonth", previousMonth);
		m.addAttribute("previousMonthYear", previousMonthYear);
		m.addAttribute("nextMonth", nextMonth);
		m.addAttribute("nextMonthYear", nextMonthYear);
		m.addAttribute("transactionList", transactionList);
		return "transactions/Transactions";
	}

	@GetMapping("/addnewTransaction")
	public String addNewTransaction(Model m) {
		Page<Transaction> transactionList = transactionRepository
				.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "date")));
		m.addAttribute("transactionList", transactionList.getContent());
		List<Category> categoryList = categoryRepository
				.findAll(Sort.by(Direction.ASC, "level").and(Sort.by(Sort.Direction.ASC, "parentCategory")));
		m.addAttribute("categoryList", categoryList);

		List<Account> accountList = accountRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("accountList", accountList);
		Transaction transaction = new Transaction();
		transaction.setDate(new Date());
		m.addAttribute("transaction", transaction);
		logger.info("addNewTransaction ");
		return "transactions/addnewTransaction";
	}

	@PostMapping("/saveTransaction")
	public String saveTransaction(@ModelAttribute("transaction") Transaction transaction) {
		transactionRepository.save(transaction);
		logger.info("saveTransaction " + transaction);
		return "redirect:/Transactions";
	}

	@GetMapping("/showFormForTransactionUpdate/{id}")
	public String showFormForTransactionUpdate(@PathVariable(value = "id") String id, Model m) {
		List<Transaction> transactionList = transactionRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "date"));
		m.addAttribute("transactionList", transactionList);
		List<Category> categoryList = categoryRepository
				.findAll(Sort.by(Direction.ASC, "level").and(Sort.by(Sort.Direction.ASC, "parentCategory")));
		m.addAttribute("CategoryList", categoryList);

		List<Account> accountList = accountRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("accountList", accountList);

		Transaction i = transactionRepository.findById(id).get();
		m.addAttribute(i);
		logger.info("showFormForTransactionUpdate ");
		return "transactions/UpdateTransaction";
	}

	@GetMapping("/deleteTransaction/{id}")
	public String deleteTransaction(@PathVariable(value = "id") String id, Model model) {
		transactionRepository.deleteById(id);
		logger.info("deleteTransaction " + id);
		return "redirect:/Transactions";
	}

	@GetMapping("/CategorySpendsFragment")
	public String getCategorySpends(Model m) {
		return "CategorySpendsFragment";
	}

	@GetMapping("/home")
	public String categoryFragment(Model m) {
		return getCategorySpendsByMonth(m, null, null);
	}

	@GetMapping("/Reports")
	public String getReports(Model m) {
		Map<String, Map<String, String>> rows = financeService.getReportData();
		m.addAttribute("headers", rows.get(0).keySet());
		m.addAttribute("rows", rows);
		return "reports/Reports";
	}

	@GetMapping("/home/{year}/{month}")
	public String getCategorySpendsByMonth(Model m, @PathVariable(value = "year") Integer year,
			@PathVariable(value = "month") Integer month) {
		YearMonth yearMonth = YearMonth.now();
		Integer nextMonthYear = year, nextMonth = month, previousMonthYear = year, previousMonth = month;
		if (year == null || year == 0) {
			year = yearMonth.getYear();
		}
		if (month == null || month == 0) {
			month = yearMonth.getMonth().getValue();
		}
		previousMonthYear = YearMonth.of(year, month).minusMonths(1).getYear();
		nextMonthYear = YearMonth.of(year, month).plusMonths(1).getYear();
		previousMonth = YearMonth.of(year, month).minusMonths(1).getMonth().getValue();
		nextMonth = YearMonth.of(year, month).plusMonths(1).getMonth().getValue();
		logger.info("year-month=" + year + "-" + month);
		m.addAttribute("previousMonth", previousMonth);
		m.addAttribute("previousMonthYear", previousMonthYear);
		m.addAttribute("nextMonth", nextMonth);
		m.addAttribute("nextMonthYear", nextMonthYear);
		m.addAttribute("currentYear", year);
		m.addAttribute("currentMonth", month);
		Map<String, CategorySpends> map = new HashMap<>();
		List<Category> categorys = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "level"));
		logger.info("categories " + categorys.size());
		List<CategorySpends> results = new ArrayList<CategorySpends>();
		for (Category cat : categorys) {
			CategorySpends catSpend = new CategorySpends();
			catSpend.setId(cat.getId());
			catSpend.setName(cat.getName());
			catSpend.setLevel(cat.getLevel());
			List<Transaction> catTransactions = transactionRepository.findAllTransactionsInCategoryByMonth(year, month,
					cat.getId());
			for (Transaction t : catTransactions) {
				catSpend.setAmount(catSpend.getAmount().add(t.getAmount()));
			}
			results.add(catSpend);
			map.put(catSpend.getId(), catSpend);
		}

		for (Category cat : categorys) {
			CategorySpends catSpend = map.get(cat.getId());
			logger.debug("Processing category spends:" + catSpend);
			for (Category child : cat.getChildCategorys()) {
				CategorySpends childCategorySpend = map.get(child.getId());
				logger.debug("Adding child category Spend:" + childCategorySpend);
				catSpend.getChildCategorySpends().add(childCategorySpend);
			}
			if (cat.getParentCategory() != null) {
				catSpend.setParentCategorySpends(map.get(cat.getParentCategory().getId()));
			}

		}

		logger.debug("results " + results);
		logger.info("CategorySpends size:" + results.size());
		int highestLevel = findHighestCategoryLevel(categorys);
		logger.info("highestLevel:" + highestLevel);
		while (highestLevel >= 0) {
			for (Category c : categorys) {
				if (c.getLevel() == highestLevel) {
					CategorySpends currentCS = getCategorySpendsForCategory(results, c);
					CategorySpends parentCS = getCategorySpendsForCategory(results, c.getParentCategory());
					if (parentCS != null) {
						parentCS.setAmount(parentCS.getAmount().add(currentCS.getAmount()));
					}
				}
			}
			highestLevel--;
		}
		Collections.sort(results);
		logger.info("result getCategorySpends " + results.get(0));
		m.addAttribute("categorySpends", results.get(0));
		return "index";
	}

	private int findHighestCategoryLevel(List<Category> categorys) {
		int level = 0;
		for (Category c : categorys) {
			if (c.getLevel() > level) {
				level = c.getLevel();
			}
		}
		return level;
	}

	private CategorySpends getCategorySpendsForCategory(List<CategorySpends> results, Category temp) {
		for (CategorySpends cs : results) {
			if (temp != null && cs.getName().equals(temp.getName())) {
				return cs;
			}
		}
		return null;
	}
}
