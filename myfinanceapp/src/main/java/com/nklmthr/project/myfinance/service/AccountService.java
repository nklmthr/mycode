package com.nklmthr.project.myfinance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nklmthr.project.myfinance.model.Account;
import com.nklmthr.project.myfinance.model.AccountCategory;
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
		Account retun = accountRepository.save(e);
		return retun;
	}
	
	public List<Account> getAccounts(){
		return (List<Account>) accountRepository.findAll();
	}

	public List<AccountCategory> getAccountCategories() {
		return (List<AccountCategory>) accountCategoryRepository.findAll();
	}
	
	public void createAccountCategory(AccountCategory accountCategory) {
		accountCategoryRepository.save(accountCategory);
	}
}
