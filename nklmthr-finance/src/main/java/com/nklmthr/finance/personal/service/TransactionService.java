package com.nklmthr.finance.personal.service;

import java.math.BigDecimal;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nklmthr.finance.personal.dao.Account;
import com.nklmthr.finance.personal.dao.Category;
import com.nklmthr.finance.personal.dao.CategoryRepository;
import com.nklmthr.finance.personal.dao.Transaction;
import com.nklmthr.finance.personal.dao.TransactionRepository;
import com.nklmthr.finance.personal.exception.SaveSplitTransactionException;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

	private static final Logger logger = Logger.getLogger(TransactionService.class);

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	CategoryRepository categoryRepository;

	public Page<Transaction> getTransactionsByCategoryInYearAndMonth(String keyword, Pageable pageable,
			String categoryId, Integer year, Integer month) {
		Page<Transaction> pageTransactions;
		if (categoryId != null) {
			Category category = categoryRepository.findById(categoryId).get();
			List<Category> categories = new ArrayList<>();
			Queue<Category> queue = new LinkedList<>();
			queue.add(category);
			while (!queue.isEmpty()) {
				logger.debug("queue Size:" + queue.size() + " category sz:" + categories.size());
				Category current = queue.poll();
				categories.add(current);
				categories.addAll(current.getChildCategorys());
				queue.addAll(current.getChildCategorys());
			}
			logger.info("Total Child Categories" + categories.size());
			pageTransactions = transactionRepository.findAllTransactionsInCategoriesByMonth(pageable, year, month,
					categories.stream().map(Category::getId).collect(Collectors.toList()));
		} else {
			logger.info("No Category. Find Transactions by year and month");
			pageTransactions = findAllTransactionsByMonth(pageable, year, month);
		}
		return pageTransactions;
	}

	public Page<Transaction> findAllTransactionsByMonth(Pageable pageable, Integer year, Integer month) {
		Page<Transaction> transactions = transactionRepository.findAllTransactionsByMonth(pageable, year, month);
		return transactions;
	}

	public void saveTransaction(Transaction transaction) {
		if (transaction.getTransactionType().equals(TransactionType.DEBIT)) {
			transaction.getAccount().setTransactionBalance(
					transaction.getAccount().getTransactionBalance().subtract(transaction.getAmount()));
		} else if (transaction.getTransactionType().equals(TransactionType.CREDIT)) {
			transaction.getAccount().setTransactionBalance(
					transaction.getAccount().getTransactionBalance().add(transaction.getAmount()));
		}
		transactionRepository.save(transaction);
	}

	public Transaction findTransactionById(String id) {
		Optional<Transaction> transaction = transactionRepository.findById(id);
		if (transaction.isPresent()) {
			return transaction.get();
		}
		return null;
	}

	public List<Transaction> getTransactionsByCategoryInMonth(Integer year, Integer month, String categoryId) {
		return transactionRepository.findAllTransactionsInCategoryByMonth(year, month, categoryId);
	}

	public List<TransactionType> getTransactionTypes() {
		return Arrays.asList(TransactionType.DEBIT, TransactionType.CREDIT);
	}

	@Transactional
	public String saveSplitTransactions(String id, List<Transaction> transactions)
			throws SaveSplitTransactionException {
		Transaction parent = transactionRepository.findById(id).get();
		BigDecimal sum = new BigDecimal(0);
		for (Transaction t : transactions) {
			sum = sum.add(t.getAmount());
		}
		if (sum.compareTo(parent.getAmount()) != 0) {
			throw new SaveSplitTransactionException("Sum of Child transactions not equal to Parent");
		}
		BigDecimal origParentAmount = parent.getAmount();
		String originalParentDescription = parent.getDescription();

		parent.setDescription(
				parent.getCategory().getName() + "|" + parent.getDescription() + "|" + parent.getAmount());
		String originalParentCategory = parent.getCategory().getName();
		Category splitCat = categoryRepository.findSplitCategory();
		parent.setCategory(splitCat);
		for (Transaction t : transactions) {
			t.setDate(parent.getDate());
			t.setAccount(parent.getAccount());
			t.setParentTransaction(parent);
			t.setCategory(categoryRepository.findById(t.getCategory().getId()).get());
			t.setTransactionType(parent.getTransactionType());
			t.setDescription("[" + originalParentCategory + "|" + originalParentDescription + "|" + origParentAmount
					+ "] " + t.getDescription());
			parent.setAmount(parent.getAmount().subtract(t.getAmount()));
			transactionRepository.save(t);
		}
		transactionRepository.save(parent);
		return "Successfully Saved Split Transaction";

	}

	public List<Transaction> findAllTransactions() {
		return transactionRepository.findAll();
	}

	public void deleteTransaction(String id) {
		Transaction child = transactionRepository.findById(id).get();
		logger.info("deleting Transaction..." + child.getDescription() + child.getAmount());
		if (child.getChildTransactions() != null) {
			logger.info("children..." + child.getChildTransactions().stream()
					.map(s -> s.getDescription() + "|" + s.getAmount()).collect(Collectors.joining(";")));
		}
		if (child.getParentTransaction() != null) {
			logger.info("| Parent =" + child.getParentTransaction().getDescription()
					+ child.getParentTransaction().getAmount());
		}
		Transaction parent = child.getParentTransaction();
		if (parent != null) {
			parent.setAmount(parent.getAmount().add(child.getAmount()));
			if (!parent.getChildTransactions().isEmpty()) {
				parent.setCategory(child.getCategory());
			}
			parent.getChildTransactions().remove(child);
			transactionRepository.save(parent);
		}
		transactionRepository.delete(child);
		logger.info("deleteTransaction " + id);

	}

	public Page<Transaction> findAllTransactionsByAccount(Pageable pageable, Account account) {
		return transactionRepository.findByAccount_Id(pageable, account);
	}

	public Transaction findTransactionsBySource(String source, Long sourceTime) {
        return transactionRepository.findBySource(source, sourceTime);

	}

}
