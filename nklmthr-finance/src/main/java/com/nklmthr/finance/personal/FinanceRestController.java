package com.nklmthr.finance.personal;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nklmthr.finance.personal.dao.Transaction;
import com.nklmthr.finance.personal.service.TransactionService;
import com.nklmthr.finance.personal.ui.controller.TransactionUIController;

@RestController
@RequestMapping("/api")
public class FinanceRestController {
	Logger logger = Logger.getLogger(TransactionUIController.class);

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/saveSplitTransaction/{id}")
	public ResponseEntity<String> saveSplitTransaction(@PathVariable(name = "id") String id,
			@RequestBody List<Transaction> transactions) {
		logger.info("id=" + id + " trans = " + transactions);
		Transaction parent = transactionService.findTransactioById(id);
		BigDecimal sum = new BigDecimal(0);
		for (Transaction t : transactions) {
			sum = sum.add(t.getAmount());
		}
		if(sum.compareTo(parent.getAmount())!=0) {
			return ResponseEntity.badRequest().body("The Sum of Split Transactions must be equal to Parent");
		}
		
		for (Transaction t : transactions) {
			t.setDate(parent.getDate());
			t.setAccount(parent.getAccount());
			t.setParentTransaction(parent);
			t.setTransactionType(parent.getTransactionType());
			transactionService.saveTransaction(t);
		}

		return ResponseEntity.ok("success");
	}
}
