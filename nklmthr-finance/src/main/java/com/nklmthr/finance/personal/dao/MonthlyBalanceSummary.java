package com.nklmthr.finance.personal.dao;

import java.math.BigDecimal;
import java.util.Date;


public class MonthlyBalanceSummary {
	
	private Date date;

	private String accountSummaryType;

	private BigDecimal amount;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAccountSummaryType() {
		return accountSummaryType;
	}

	public void setAccountSummaryType(String accountSummaryType) {
		this.accountSummaryType = accountSummaryType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
