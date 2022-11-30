package com.nklmthr.project.myfinance.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nklmthr.project.myfinance.model.Account;
import com.nklmthr.project.myfinance.model.AccountCategory;
import com.nklmthr.project.myfinance.model.AccountType;
import com.nklmthr.project.myfinance.model.Institution;
import com.nklmthr.project.myfinance.model.Transaction;
import com.nklmthr.project.myfinance.model.TransactionCategory;
import com.nklmthr.project.myfinance.service.AccountService;

@Controller
public class AccountRestService {

	@Autowired
	private AccountService service;

	@GetMapping("/accountCategory")
	@ResponseBody
	public List<AccountCategory> getAccountCategories() {
		return service.getAccountCategories();
	}

	@PostMapping("/accountCategory")
	public void createAccountCategory(AccountCategory accountCategory) {
		service.createAccountCategory(accountCategory);
	}

	@GetMapping("/accountType")
	@ResponseBody
	public List<AccountType> getAccountTypes() {
		return service.getAccountTypes();
	}

	@PostMapping("/accountType")
	public void createAccountType(AccountType accountType) {
		service.createAccountType(accountType);
	}

	@GetMapping("/institution")
	@ResponseBody
	public List<Institution> getInstitutions() {
		return service.getInstitutions();
	}

	@PostMapping("/institution")
	public void createInstitution(Institution institution) {
		service.createInstitution(institution);
	}

	@GetMapping("/account")
	@ResponseBody
	public List<Account> getAccounts() {
		return service.getAccounts();
	}

	@PostMapping("/account")
	public void createAccount(Account account) {
		service.createAccount(account);
	}

	@GetMapping("/transactionCategory")
	@ResponseBody
	public List<TransactionCategory> getTransactionCategories() {
		return service.getTransactionCategories();
	}

	@PostMapping("/transactionCategory")
	public void createTransactionCategory(TransactionCategory transactionCategory) {
		service.createTransactionCategory(transactionCategory);
	}

	@GetMapping("/transaction")
	@ResponseBody
	public List<Transaction> getTransactions() {
		return service.getTransactions();
	}

	@PostMapping("/transaction")
	public void createTransaction(Transaction Transaction) {
		service.createTransaction(Transaction);
	}
}
