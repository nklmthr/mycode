package com.nklmthr.finance.personal.dao;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.nklmthr.finance.personal.service.TransactionType;


@Entity
public class Transaction {	
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column
	private String id;

	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

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
	private TransactionType transactionType;

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

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", account=" + account + ", category=" + category + "]";
	}
	
}
