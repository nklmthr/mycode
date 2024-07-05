package com.nklmthr.finance.personal;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nklmthr.finance.personal.dao.Transaction;
import com.nklmthr.finance.personal.exception.SaveSplitTransactionException;
import com.nklmthr.finance.personal.service.CategoryService;
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
		String message = null;
		try {
			message = transactionService.saveSplitTransactions(id, transactions);
		} catch(SaveSplitTransactionException e) {
			return ResponseEntity.badRequest().body(message);
		}
		return ResponseEntity.ok(message);
	}
	
	@GetMapping("/transactions")
	public ResponseEntity<List<Transaction>> getTransactions(){
		return ResponseEntity.ok(transactionService.findAllTransactions());
	}
	
	@GetMapping("/hint/{q}")
	public ResponseEntity<String> getHint(@PathVariable("q") String q){
		return ResponseEntity.ok("Health Ok"+q);
	}
}
