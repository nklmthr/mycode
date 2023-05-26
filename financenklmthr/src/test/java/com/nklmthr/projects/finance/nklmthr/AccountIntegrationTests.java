package com.nklmthr.projects.finance.nklmthr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.nklmthr.projects.finance.nklmthr.dao.Account;
import com.nklmthr.projects.finance.nklmthr.dao.AccountType;
import com.nklmthr.projects.finance.nklmthr.dao.Institution;
import com.nklmthr.projects.finance.nklmthr.dao.Transaction;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@TestPropertySource(locations = "classpath:test.properties")
public class AccountIntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	
	private ResponseEntity<AccountType> createAccountType(String name, String description) {
		AccountType accountType = new AccountType();
		accountType.setName(name);
		accountType.setDescription(description);
		ResponseEntity<AccountType> responseEntity = restTemplate.postForEntity("/accountType", accountType,
				AccountType.class);
		return responseEntity;
	}
	
	private ResponseEntity<Institution> createInstitution(String name, String description) {
		Institution institution = new Institution();
		institution.setName(name);
		institution.setDescription(description);
		ResponseEntity<Institution> responseEntity = restTemplate.postForEntity("/institution", institution,
				Institution.class);
		return responseEntity;
	}
	
	private ResponseEntity<Account> createAccount(String name){
		Account account = new Account();
		ResponseEntity<AccountType> accountTypeResponseEntity = createAccountType("SB", "Savings");
		ResponseEntity<Institution> InstitutionResponseEntity = createInstitution("YES", "YES Bank");
		account.setInstitution(InstitutionResponseEntity.getBody());
		account.setAccountType(accountTypeResponseEntity.getBody());
		account.setName(name);
		ResponseEntity<Account> responseEntity = restTemplate.postForEntity("/account", account,
				Account.class);
		return responseEntity;
	}
	
	@Test
	public void test_createAccountTypeIntegrationtests() {
		ResponseEntity<AccountType> responseEntity =  createAccountType("SB", "Savings");
		int status = responseEntity.getStatusCodeValue();
		AccountType resultAccountType = responseEntity.getBody();
		assertNotNull(resultAccountType);
		assertEquals(status, 200);
		assertNotNull(resultAccountType.getId());
	}
	
	@Test
	public void test_createInstitutionIntegrationtests() {
		ResponseEntity<Institution> responseEntity =  createInstitution("ICICI", "ICICI Bank");
		int status = responseEntity.getStatusCodeValue();
		Institution resultInstitution = responseEntity.getBody();
		assertNotNull(resultInstitution);
		assertEquals(status, 200);
		assertNotNull(resultInstitution.getId());
	}
	
	@Test
	public void test_createAccountIntegrationtests() {
		ResponseEntity<Account> responseEntityAccount =  createAccount("YES Bank Savings");
		int status = responseEntityAccount.getStatusCodeValue();
		Account resultAccount = responseEntityAccount.getBody();
		assertNotNull(resultAccount);
		assertEquals(status, 200);
		assertNotNull(resultAccount.getId());
	}
	@Test
	public void test_createTransactionIntegrationTests() {
		ResponseEntity<Account> responseEntityAccount =  createAccount("YES Bank Savings");
		Transaction t = new Transaction();
		t.setAccount(responseEntityAccount.getBody());
		t.setDate(new Date());
		t.setAmount(525d);
		t.setDescription("Hello World Kolhapur Trp");
		ResponseEntity<Transaction> responseEntityTransaction = restTemplate.postForEntity("/transaction", t, Transaction.class);
		assertNotNull(responseEntityTransaction);
		assertEquals(responseEntityTransaction.getStatusCode().value(), 200);
		assertNotNull(responseEntityTransaction.getBody().getId());
		System.out.println("account balance:"+responseEntityTransaction.getBody().getAccount().getTransactionBalance());
	}
}
