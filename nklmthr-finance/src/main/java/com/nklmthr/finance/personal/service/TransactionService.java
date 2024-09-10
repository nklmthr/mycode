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
import com.nklmthr.finance.personal.dao.AccountRepository;
import com.nklmthr.finance.personal.dao.AccountType;
import com.nklmthr.finance.personal.dao.Category;
import com.nklmthr.finance.personal.dao.CategoryRepository;
import com.nklmthr.finance.personal.dao.Transaction;
import com.nklmthr.finance.personal.dao.TransactionAttachment;
import com.nklmthr.finance.personal.dao.TransactionAttachmentRepository;
import com.nklmthr.finance.personal.dao.TransactionRepository;
import com.nklmthr.finance.personal.exception.SaveSplitTransactionException;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

	private static final Logger logger = Logger.getLogger(TransactionService.class);

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransactionAttachmentRepository transactionAttachmentRepository;

	@Autowired
	CategoryService categoryService;

	@Autowired
	AccountService accountService;

	public Page<Transaction> getTransactionsByCategoryInYearAndMonth(String keyword, Pageable pageable,
			String categoryId, Integer year, Integer month) {
		Page<Transaction> pageTransactions;
		if (categoryId != null) {
			Category category = categoryService.findCategoryById(categoryId);
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
		Optional<Transaction> oldTransactionOptional = transactionRepository.findById(transaction.getId());
		if (oldTransactionOptional.isPresent()) {
			Transaction oldTransaction = oldTransactionOptional.get();
			BigDecimal changeValue = transaction.getAmount().subtract(oldTransaction.getAmount());
			logger.info("changeValue" + changeValue);
			if (!transaction.getAccount().getId().equals(oldTransaction.getAccount().getId())) {
				Account oldAccount = accountService.getAccountById(oldTransaction.getAccount().getId());
				if (oldTransaction.getTransactionType().equals(TransactionType.CREDIT)) {
					oldAccount.setTransactionBalance(
							oldAccount.getTransactionBalance().subtract(transaction.getAmount()));
				} else if (oldTransaction.getTransactionType().equals(TransactionType.DEBIT)) {
					oldAccount.setTransactionBalance(oldAccount.getTransactionBalance().add(transaction.getAmount()));
				}
				logger.info("oldAccount" + oldAccount);
				if (transaction.getTransactionType().equals(TransactionType.CREDIT)) {
					transaction.getAccount().setTransactionBalance(
							transaction.getAccount().getTransactionBalance().add(transaction.getAmount()));
				} else if (transaction.getTransactionType().equals(TransactionType.DEBIT)) {
					transaction.getAccount().setTransactionBalance(
							transaction.getAccount().getTransactionBalance().subtract(transaction.getAmount()));
				}
			} else if (transaction.getTransactionType().equals(oldTransaction.getTransactionType())) {
				if (transaction.getTransactionType().equals(TransactionType.CREDIT)) {
					transaction.getAccount()
							.setTransactionBalance(transaction.getAccount().getTransactionBalance().add(changeValue));
				} else if (transaction.getTransactionType().equals(TransactionType.DEBIT)) {
					transaction.getAccount().setTransactionBalance(
							transaction.getAccount().getTransactionBalance().subtract(changeValue));
				}
			} else {
				if (transaction.getTransactionType().equals(TransactionType.CREDIT)) {
					transaction.getAccount().setTransactionBalance(transaction.getAccount().getTransactionBalance()
							.add(oldTransaction.getAmount().add(changeValue)));

				} else if (transaction.getTransactionType().equals(TransactionType.DEBIT)) {
					transaction.getAccount().setTransactionBalance(transaction.getAccount().getTransactionBalance()
							.subtract(oldTransaction.getAmount().add(changeValue)));
				}
			}
		} else {
			if (transaction.getTransactionType().equals(TransactionType.CREDIT)) {
				transaction.getAccount().setTransactionBalance(
						transaction.getAccount().getTransactionBalance().add(transaction.getAmount()));
			} else if (transaction.getTransactionType().equals(TransactionType.DEBIT)) {
				transaction.getAccount().setTransactionBalance(
						transaction.getAccount().getTransactionBalance().subtract(transaction.getAmount()));
			}
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
		Category splitCat = categoryService.getParentCategoryByType(CategoryType.SPLIT);
		parent.setCategory(splitCat);
		for (Transaction t : transactions) {
			t.setDate(parent.getDate());
			t.setAccount(parent.getAccount());
			t.setParentTransaction(parent);
			t.setCategory(categoryService.findCategoryById(t.getCategory().getId()));
			t.setTransactionType(parent.getTransactionType());
			t.setDescription(originalParentDescription + "|" + origParentAmount + "|" + originalParentCategory);
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
		Transaction transaction = transactionRepository.findById(id).get();
		logger.info("deleting Transaction..." + transaction.getDescription() + transaction.getAmount());
		if (transaction.getChildTransactions() != null) {
			logger.info("children..." + transaction.getChildTransactions().stream()
					.map(s -> s.getDescription() + "|" + s.getAmount()).collect(Collectors.joining(";")));
		}
		if (transaction.getParentTransaction() != null) {
			logger.info("| Parent =" + transaction.getParentTransaction().getDescription()
					+ transaction.getParentTransaction().getAmount());
		}
		Transaction parent = transaction.getParentTransaction();
		if (parent != null) {
			parent.setAmount(parent.getAmount().add(transaction.getAmount()));
			if (!parent.getChildTransactions().isEmpty()) {
				parent.setCategory(transaction.getCategory());
			}
			parent.getChildTransactions().remove(transaction);
			transactionRepository.save(parent);
		}
		if (transaction.getTransactionType().equals(TransactionType.DEBIT)) {
			transaction.getAccount().setTransactionBalance(
					transaction.getAccount().getTransactionBalance().add(transaction.getAmount()));
		} else {
			transaction.getAccount().setTransactionBalance(
					transaction.getAccount().getTransactionBalance().subtract(transaction.getAmount()));
		}
		transactionRepository.delete(transaction);
		logger.info("deleteTransaction " + id);

	}

	public Page<Transaction> findAllTransactionsByAccount(Pageable pageable, Account account) {
		return transactionRepository.findByAccount_Id(pageable, account);
	}

	public Transaction findTransactionsBySource(String source, Long sourceTime) {
		return transactionRepository.findBySource(source, sourceTime);

	}

	public Transaction getTransactionById(String id) {
		Optional<Transaction> t = transactionRepository.findById(id);
		return t.isPresent() ? t.get() : null;

	}

	public void addAttachment(Transaction t, TransactionAttachment attachment) {
		transactionAttachmentRepository.save(attachment);

	}

	public List<TransactionAttachment> getTransactionAttachments(String id) {
		return transactionAttachmentRepository.findByTransactionId(id);
	}

	public void deleteTransactionAttachmentById(String attachmentId) {
		transactionAttachmentRepository.deleteById(attachmentId);
	}

	public byte[] getTransactionAttachmentById(String attachmentId) {
		return transactionAttachmentRepository.findById(attachmentId).get().getImageData();

	}

	public void performTransferOperation(Transaction transaction, String transferToAccountId) {
		Account transferToAccount = accountService.getAccountById(transferToAccountId);
		Account transferFromAccount = accountService.getAccountById(transaction.getAccount().getId());

		logger.debug("transaction:" + transaction);
		logger.info("transferToAccount id:" + transferToAccountId + " found Account:" + transferToAccount.getName()
				+ "," + transferToAccount.getTransactionBalance());
		BigDecimal transferToAccBal = transferToAccount.getTransactionBalance();
		BigDecimal newTransferToAccBal = transferToAccBal.add(transaction.getAmount());
		transferToAccount.setTransactionBalance(newTransferToAccBal);
		logger.info("To Account:" + transferToAccount.getName() + ",old balance:" + transferToAccBal + ",New Balance:"
				+ newTransferToAccBal);

		Category category = categoryService.getParentCategoryByType(CategoryType.TRANSFERS);
		transaction.setCategory(category);
		Transaction newTransaction = new Transaction();
		newTransaction.setDate(transaction.getDate());
		newTransaction.setDescription(transaction.getDescription());
		newTransaction.setExplanation(transaction.getExplanation());
		newTransaction.setAccount(transferToAccount);
		newTransaction.setAmount(transaction.getAmount());
		newTransaction.setCurrency(transaction.getCurrency());
		if (transaction.getTransactionType().equals(TransactionType.DEBIT)) {
			newTransaction.setTransactionType(TransactionType.CREDIT);
		} else {
			newTransaction.setTransactionType(TransactionType.DEBIT);
		}
		newTransaction.setCategory(category);
		transactionRepository.save(transaction);
		transactionRepository.save(newTransaction);
	}
}
