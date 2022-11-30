package com.nklmthr.project.myfinance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nklmthr.project.myfinance.model.Account;
import com.nklmthr.project.myfinance.model.AccountCategory;
import com.nklmthr.project.myfinance.model.AccountType;
import com.nklmthr.project.myfinance.model.Institution;
import com.nklmthr.project.myfinance.model.Transaction;
import com.nklmthr.project.myfinance.model.TransactionCategory;
import com.nklmthr.project.myfinance.repository.AccountCategoryRepository;
import com.nklmthr.project.myfinance.repository.AccountRepository;
import com.nklmthr.project.myfinance.repository.AccountTypeRepository;
import com.nklmthr.project.myfinance.repository.InstitutionRepository;
import com.nklmthr.project.myfinance.repository.TransactionCategoryRepository;
import com.nklmthr.project.myfinance.repository.TransactionRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountCategoryRepository accountCategoryRepository;

	@Autowired
	private AccountTypeRepository accountTypeRepository;

	@Autowired
	private InstitutionRepository institutionRepository;

	@Autowired
	private TransactionCategoryRepository transactionCategoryRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	public Account createAccount(Account e) {
		return accountRepository.save(e);
	}

	public List<Account> getAccounts() {
		return (List<Account>) accountRepository.findAll();
	}

	public List<AccountCategory> getAccountCategories() {
		return (List<AccountCategory>) accountCategoryRepository.findAll();
	}

	public void createAccountCategory(AccountCategory accountCategory) {
		accountCategoryRepository.save(accountCategory);
	}

	public List<AccountType> getAccountTypes() {
		return (List<AccountType>) accountTypeRepository.findAll();
	}

	public void createAccountType(AccountType accountType) {
		accountTypeRepository.save(accountType);
	}

	public List<Institution> getInstitutions() {
		return (List<Institution>) institutionRepository.findAll();
	}

	public void createInstitution(Institution institution) {
		institutionRepository.save(institution);

	}

	public List<TransactionCategory> getTransactionCategories() {
		return (List<TransactionCategory>) transactionCategoryRepository.findAll();
	}

	public void createTransactionCategory(TransactionCategory transactionCategory) {
		transactionCategoryRepository.save(transactionCategory);
	}

	public List<Transaction> getTransactions() {
		return (List<Transaction>) transactionRepository.findAll();
	}

	public void createTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
	}
}
