package com.nklmthr.finance.personal.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MonthlyBalance {
	@Id
	@UuidGenerator
	@Column
	private String id;

	@Column
	private Date date;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "account", referencedColumnName = "id")
	private Account account;

	@Column
	private BigDecimal amount;


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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "MonthlyBalance [id=" + id + ", date=" + date + ", account=" + account + ", amount=" + amount + "]";
	}

}
