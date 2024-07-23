package com.nklmthr.finance.personal.dao;

import java.math.BigDecimal;
import java.text.Format;
import java.util.Locale;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Account {

	@Id
	@UuidGenerator
	@Column
	private String id;

	@Column
	private String name;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "institution", referencedColumnName = "id")
	private Institution institution;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "accountType", referencedColumnName = "id")
	private AccountType accountType;
	
	@Column
	private BigDecimal transactionBalance;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getTransactionBalance() {
		return transactionBalance;
	}

	public void setTransactionBalance(BigDecimal transactionBalance) {
		this.transactionBalance = transactionBalance;
	}

	public String getFormattedBalance() {
		Format indianCurrencyFormat = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
		String balance = indianCurrencyFormat.format(transactionBalance);
		return balance;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", institution=" + institution + ", accountType=" + accountType
				 + ", transactionBalance=" + transactionBalance + "]";
	}
	
}
