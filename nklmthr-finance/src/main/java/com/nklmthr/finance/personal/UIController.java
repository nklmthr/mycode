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
	 * @param m
	 * @return
	 */
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
		List<Institution> institutionPage = institutionRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("institutionList", institutionPage);
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

	/*
	 * 
	 * Categories
	 */

	@GetMapping("/Categorys")
	public String Categorys(Model m) {
		List<Category> categoryPage = categoryRepository
				.findAll(Sort.by(Direction.ASC, "level").and(Sort.by(Sort.Direction.ASC, "parentCategory")));
		m.addAttribute("categoryList", categoryPage);
		return "category/Categorys";
	}

	@GetMapping("/addnewCategory")
	public String addnewCategory(Model m) {
		List<Category> Categorys = categoryRepository
				.findAll(Sort.by(Direction.ASC, "level").and(Sort.by(Sort.Direction.ASC, "parentCategory")));
		m.addAttribute("CategoryList", Categorys);

		Category category = new Category();
		m.addAttribute("category", category);
		return "category/addnewCategory";
	}

	@PostMapping("/saveCategory")
	public String addnewCategory(@ModelAttribute("category") Category category) {
		Category temp = (Category) category.clone();
		int level = 0;
		while (temp.getParentCategory() != null) {
			temp = temp.getParentCategory();
			level++;
		}
		category.setLevel(level);
		categoryRepository.save(category);
		return "redirect:/Categorys";
	}

	@GetMapping("/showFormForCategoryUpdate/{id}")
	public String showFormForCategoryUpdate(@PathVariable(value = "id") String id, Model m) {
		List<Category> Categorys = categoryRepository
				.findAll(Sort.by(Direction.ASC, "level").and(Sort.by(Sort.Direction.ASC, "parentCategory")));
		m.addAttribute("CategoryList", Categorys);
		Category c = categoryRepository.findById(id).get();
		m.addAttribute("category", c);
		return "category/UpdateCategory";
	}

	@GetMapping("/deleteCategory/{id}")
	public String deleteCategory(@PathVariable(value = "id") String id, Model model) {
		categoryRepository.deleteById(id);
		return "redirect:/Categorys";
	}
	
	/*
	 * 
	 * Transactions
	 */

	@GetMapping("/Transactions")
	public String Transactions(Model m) {
		List<Transaction> transactionPage = transactionRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "date"));
		m.addAttribute("transactionList", transactionPage);
		return "transactions/Transactions";
	}

	@GetMapping("/addnewTransaction")
	public String addnewTransaction(Model m) {
		List<Transaction> transactions = transactionRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "date"));
		m.addAttribute("transactionList", transactions);
		List<Category> Categorys = categoryRepository
				.findAll(Sort.by(Direction.ASC, "level").and(Sort.by(Sort.Direction.ASC, "parentCategory")));
		m.addAttribute("CategoryList", Categorys);
		
		List<Account> accountPage = accountRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("accountList", accountPage);
		Transaction transaction = new Transaction();
		m.addAttribute("transaction", transaction);
		return "transactions/addnewTransaction";
	}

	@PostMapping("/saveTransaction")
	public String addnewTransaction(@ModelAttribute("transaction") Transaction transaction) {
		transactionRepository.save(transaction);

		return "redirect:/Transactions";
	}

	@GetMapping("/showFormForTransactionUpdate/{id}")
	public String showFormForTransactionUpdate(@PathVariable(value = "id") String id, Model m) {
		List<Transaction> transactions = transactionRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "date"));
		m.addAttribute("transactionList", transactions);
		List<Category> Categorys = categoryRepository
				.findAll(Sort.by(Direction.ASC, "level").and(Sort.by(Sort.Direction.ASC, "parentCategory")));
		m.addAttribute("CategoryList", Categorys);
		
		List<Account> accountPage = accountRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
		m.addAttribute("accountList", accountPage);

		Transaction i = transactionRepository.findById(id).get();
		m.addAttribute(i);
		return "transactions/UpdateTransaction";
	}

	@GetMapping("/deleteTransaction/{id}")
	public String deleteTransaction(@PathVariable(value = "id") String id, Model model) {
		transactionRepository.deleteById(id);
		return "redirect:/Transactions";
	}
}
