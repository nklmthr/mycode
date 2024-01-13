package com.nklmthr.finance.personal.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nklmthr.finance.personal.dao.AccountType;
import com.nklmthr.finance.personal.dao.AccountTypeRepository;

@Service
public class AccountTypeService {

	private static Logger logger = Logger.getLogger(AccountTypeService.class);

	@Autowired
	private AccountTypeRepository accountTypeRepository;

	public List<AccountType> getAllAccountTypes() {
		return accountTypeRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "name"));
	}

	public AccountType save(AccountType accountType) {
		return accountTypeRepository.save(accountType);
		
	}

	public AccountType findAccountTypeById(String id) {
		Optional<AccountType> at = accountTypeRepository.findById(id);
		if(at.isPresent()) {
			return at.get();
		}
		return null;
	}

	public void deleteAccountTypeById(String id) {
		accountTypeRepository.deleteById(id);
	}



}
