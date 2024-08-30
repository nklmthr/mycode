package com.nklmthr.finance.personal.dao;

import java.math.BigDecimal;
import java.text.Format;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nklmthr.finance.personal.service.TransactionType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Transaction {

	@Id
	@UuidGenerator
	@Column
	private String id;

	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date date;

	@Column
	private String explanation;

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	@Column
	private String description;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "account", referencedColumnName = "id")
	private Account account;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "category", referencedColumnName = "id")
	private Category category;

	@Column
	private BigDecimal amount;

	@Column
	private String source;

	@Column
	private Long sourceTime;

	@Column
	private String currency = "INR";

	@Column
	private TransactionType transactionType;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonIgnore
	@JoinColumn(name = "parentTransaction", referencedColumnName = "id")
	private Transaction parentTransaction;

	@JsonIgnore
	@OneToMany(mappedBy = "parentTransaction", cascade = CascadeType.ALL)
	private Set<Transaction> childTransactions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Set<Transaction> getChildTransactions() {
		return childTransactions;
	}

	public void setChildTransactions(Set<Transaction> childTransactions) {
		this.childTransactions = childTransactions;
	}

	public Transaction getParentTransaction() {
		return parentTransaction;
	}

	public void setParentTransaction(Transaction parentTransaction) {
		this.parentTransaction = parentTransaction;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getSourceTime() {
		return sourceTime;
	}

	public void setSourceTime(Long sourceTime) {
		this.sourceTime = sourceTime;
	}

	public String getFormattedBalance() {
		Format indianCurrencyFormat = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
		String balance = indianCurrencyFormat.format(amount);
		return balance;
	}

	@Override
	public String toString() {
		return "Transaction [date=" + date + ", description=" + description + ", amount="
				+ amount + ", source=" + source + ", sourceTime=" + sourceTime + "]";
	}

	
}
