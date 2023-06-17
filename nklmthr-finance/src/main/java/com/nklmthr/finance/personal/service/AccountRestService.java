package com.nklmthr.finance.personal.service;


import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

import javax.websocket.server.PathParam;

@RestController
@CrossOrigin
@RequestMapping("/api")
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

	@DeleteMapping("/accountType/{accountTypeId}")
	public ResponseEntity<String> deleteAccountType(@PathVariable("accountTypeId") String accountTypeId) {
		accountTypeRepository.deleteById(accountTypeId);
		logger.info(accountTypeId);
		return new ResponseEntity<String>("Successfully deleted"+accountTypeId, HttpStatus.OK);
	}

	@PutMapping("/accountType/{accountTypeId}")
	public ResponseEntity<String> updateAccountType(@PathVariable("accountTypeId")String accountTypeId, @RequestBody AccountType newAccountType) {
		logger.info("update accountType:"+newAccountType.getId());
		Optional<AccountType> accountTypeOptional = accountTypeRepository.findById(accountTypeId);
		if(accountTypeOptional.isPresent()){
			logger.info("accountType Fund");
			AccountType oldAccountType = accountTypeOptional.get();
			oldAccountType.setName(newAccountType.getName());
			oldAccountType.setDescription((newAccountType.getDescription()));
			accountTypeRepository.save(oldAccountType);
		}
		else{
			logger.info("Account Type Not Found"+accountTypeId);
			return new ResponseEntity<String>("Account Type not Found"+accountTypeId, HttpStatus.BAD_REQUEST);
		}
		logger.info("Successfully updated");
		return new ResponseEntity<String>("Successfully updated"+accountTypeId, HttpStatus.OK);
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
	public ResponseEntity<Institution> addInstitution(@RequestBody Institution institution) {

		institution = institutionRepository.save(institution);
		logger.info(institution);
		return new ResponseEntity<Institution>(institution, HttpStatus.OK);
	}

	@DeleteMapping("/institution/{institutionId}")
	public ResponseEntity<String> deleteInstitution(@PathVariable("institutionId") String institutionId) {
		accountTypeRepository.deleteById(institutionId);
		logger.info(institutionId);
		return new ResponseEntity<String>("Successfully deleted"+institutionId, HttpStatus.OK);
	}

	@PutMapping("/institution/{institutionId}")
	public ResponseEntity<String> updateInstitution(@PathVariable("institutionId")String institutionId, @RequestBody Institution newInstitution) {
		logger.info("update accountType:"+newInstitution.getId());
		Optional<Institution> institutionOptional = institutionRepository.findById(institutionId);
		if(institutionOptional.isPresent()){
			logger.info("institution found");
			Institution oldInstitution = institutionOptional.get();
			oldInstitution.setName(newInstitution.getName());
			oldInstitution.setDescription((newInstitution.getDescription()));
			institutionRepository.save(oldInstitution);
		}
		else{
			logger.info("Institution Not Found"+institutionId);
			return new ResponseEntity<String>("Institution not Found"+institutionId, HttpStatus.BAD_REQUEST);
		}
		logger.info("Successfully updated");
		return new ResponseEntity<String>("Successfully updated"+institutionId, HttpStatus.OK);
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
