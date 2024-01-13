package com.nklmthr.finance.personal.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nklmthr.finance.personal.dao.Category;
import com.nklmthr.finance.personal.dao.CategoryRepository;
import com.nklmthr.finance.personal.dao.Transaction;
import com.nklmthr.finance.personal.dao.TransactionRepository;

@Service
public class TransactionService {

	public enum TransactionType {DEBIT,CREDIT};
	
	private static Logger logger = Logger.getLogger(TransactionService.class);

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	CategoryRepository categoryRepository;

	public List<Transaction> getTransactionsByCategoryInYearAndMonth(String categoryId, Integer year, Integer month) {
		List<Transaction> transactions = new ArrayList<>();
		if (categoryId != null) {
			Category category = categoryRepository.findById(categoryId).get();
			transactions.addAll(transactionRepository.findAllTransactionsInCategoryByMonth(year, month, categoryId));
			List<Category> categories = new ArrayList<Category>();
			Queue<Category> queue = new LinkedList<Category>();
			queue.add(category);
			while (!queue.isEmpty()) {
				logger.info("queue Size:" + queue.size() + " category sz:" + categories.size());
				Category current = queue.poll();
				categories.addAll(current.getChildCategorys());
				queue.addAll(current.getChildCategorys());
			}
			logger.info("Total Child Categories" + categories.size());
			transactions.addAll(transactionRepository.findAllTransactionsInCategoriesByMonth(year, month,
					categories.stream().map(s -> s.getId()).collect(Collectors.toList())));
		} else {
			transactions = transactionRepository.findAllTransactionsByMonth(year, month);
		}
		return transactions;
	}

	public List<Transaction> getLatestTransaction(int numberOfTransactions) {
		Page<Transaction> transactions = transactionRepository
				.findAll(PageRequest.of(0, numberOfTransactions, Sort.by(Sort.Direction.DESC, "date")));
		return transactions.getContent();
	}

	public void saveTransaction(Transaction transaction) {
		if(transaction.getTransactionType().equals(TransactionType.DEBIT)) {
			transaction.getAccount().setTransactionBalance(transaction.getAccount().getTransactionBalance().subtract(transaction.getAmount()));
		} else if(transaction.getTransactionType().equals(TransactionType.CREDIT)) {
			transaction.getAccount().setTransactionBalance(transaction.getAccount().getTransactionBalance().add(transaction.getAmount()));
		}
		transactionRepository.save(transaction);	
	}

	public Transaction findTransactioById(String id) {
		Optional<Transaction> transaction = transactionRepository.findById(id);
		if(transaction.isPresent()) {
			return transaction.get();
		}
		return null;
	}

	public void deleteTransactionById(String id) {
		transactionRepository.deleteById(id);
		
	}

	public List<Transaction> getTransactionsByCategoryInMonth(Integer year, Integer month, String categoryId) {
		return transactionRepository.findAllTransactionsInCategoryByMonth(year, month, categoryId);
	}
	
	public List<TransactionType> getTransactionTypes(){
		return Arrays.asList(TransactionType.DEBIT, TransactionType.CREDIT);
	}

}
