package com.nklmthr.finance.personal;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.nklmthr.finance.personal.dao.Institution;
import com.nklmthr.finance.personal.dao.InstitutionRepository;
import com.nklmthr.finance.personal.dao.Transaction;
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

	@GetMapping("/home")
	public String getHome(Model m) {
		return "index";
	}

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

	@GetMapping("/Categorys")
	public String getCategorys(Model m) {
		List<Category> categoryList = categoryRepository
				.findAll(Sort.by(Direction.ASC, "level").and(Sort.by(Sort.Direction.ASC, "parentCategory")));
		m.addAttribute("categoryList", categoryList);
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
		List<Transaction> transactionList = transactionRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
		m.addAttribute("transactionList", transactionList);
		logger.info("getTransactions size:" + transactionList.size());
		return "transactions/Transactions";
	}

	@GetMapping("/addnewTransaction")
	public String addNewTransaction(Model m) {
		List<Transaction> transactionList = transactionRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "date"));
		m.addAttribute("transactionList", transactionList);
		List<Category> categoryList = categoryRepository
				.findAll(Sort.by(Direction.ASC, "level").and(Sort.by(Sort.Direction.ASC, "parentCategory")));
		m.addAttribute("CategoryList", categoryList);

		List<Account> accountList = accountRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("accountList", accountList);
		Transaction transaction = new Transaction();
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
		logger.info("deleteTransaction "+id);
		return "redirect:/Transactions";
	}
}
