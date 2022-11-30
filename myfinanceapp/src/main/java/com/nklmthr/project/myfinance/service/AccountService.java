package com.nklmthr.project.myfinance.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nklmthr.project.myfinance.model.Account;
import com.nklmthr.project.myfinance.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository repository;
	
	public Account createEmployee(Account e) {
		Account retun = repository.save(e);
		return retun;
	}
	
	public List<Account> getAccounts(){
		Iterable<Account> lsit = repository.findAll();
		List<Account> targetCollection = new ArrayList<Account>();
		CollectionUtils.addAll(targetCollection, lsit.iterator());
		return targetCollection;
	}
}
