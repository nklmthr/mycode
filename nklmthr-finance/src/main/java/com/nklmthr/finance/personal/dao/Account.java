package com.nklmthr.finance.personal.dao;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Account {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
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

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", institution=" + institution + ", accountType=" + accountType
				 + ", transactionBalance=" + transactionBalance + "]";
	}
	
}
