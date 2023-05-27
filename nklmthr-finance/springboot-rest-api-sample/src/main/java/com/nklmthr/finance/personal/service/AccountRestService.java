package com.nklmthr.finance.personal.service;


import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
public class AccountRestService {

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

	@RequestMapping(value = "/accountType", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<AccountType> getAccountTypes() {
		List<AccountType> results = (List<AccountType>) accountTypeRepository.findAll();
		return results;
	}

	@PostMapping(value = "/accountType", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AccountType> addAccountType(@RequestBody AccountType accountType) {

		accountType = accountTypeRepository.save(accountType);
		logger.info(accountType);
		return new ResponseEntity<AccountType>(accountType, HttpStatus.OK);
	}

	@RequestMapping(value = "/institution", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Institution> getInstitutions() {
		List<Institution> results = (List<Institution>) institutionRepository.findAll();
		return results;
	}

	@PostMapping(value = "/institution", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Institution> addAccountType(@RequestBody Institution institution) {

		institution = institutionRepository.save(institution);
		logger.info(institution);
		return new ResponseEntity<Institution>(institution, HttpStatus.OK);
	}

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Account> getAccounts() {
		List<Account> results = (List<Account>) accountRepository.findAll();
		return results;
	}

	@PostMapping(value = "/account", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Account> addAccount(@RequestBody Account account) {

		account = accountRepository.save(account);
		logger.info(account);
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Category> getCategory() {
		List<Category> results = (List<Category>) categoryRepository.findAll();
		return results;
	}

	@PostMapping(value = "/category", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		category = categoryRepository.save(category);
		logger.info(category);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}

	@RequestMapping(value = "/transaction", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Transaction> getTransaction() {
		List<Transaction> results = (List<Transaction>) transactionRepository.findAll();
		return results;
	}

	@PostMapping(value = "/transaction", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@Transactional
	public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {

		transaction = transactionRepository.save(transaction);
		Optional<Account> optional = accountRepository.findById(transaction.getId());
		if (optional.isPresent()) {
			Account account = optional.get();
			account.setTransactionBalance(account.getTransactionBalance() + transaction.getAmount());
		}
		logger.info(transaction);
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}
}
