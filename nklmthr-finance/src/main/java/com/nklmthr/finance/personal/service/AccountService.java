package com.nklmthr.finance.personal.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nklmthr.finance.personal.dao.Account;
import com.nklmthr.finance.personal.dao.AccountRepository;

@Service
public class AccountService {

	private static Logger logger = Logger.getLogger(AccountService.class);

	@Autowired
	private AccountRepository accountRepository;

	public List<Account> getAllAccounts() {
		return accountRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
	}

	public Account saveAccount(Account account) {
		return accountRepository.save(account);		
	}

	public Account findAccountById(String id) {
		Optional<Account> account = accountRepository.findById(id);
		if(account.isPresent()) {
			return account.get();
		}
		return null;
	}

	public void deleteAccountById(String id) {
		accountRepository.deleteById(id);
	}

	public Account findAccountByName(String name) {
		return accountRepository.findByName(name);
	}

}
