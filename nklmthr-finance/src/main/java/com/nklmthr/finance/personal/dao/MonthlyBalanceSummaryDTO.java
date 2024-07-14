package com.nklmthr.finance.personal.dao;

import java.math.BigDecimal;
import java.util.Date;

public class MonthlyBalanceSummaryDTO {
	private Date date;

	private String description;

	private BigDecimal amount;

	public MonthlyBalanceSummaryDTO(Date date, String description, BigDecimal amount) {
		super();
		this.date = date;
		this.description = description;
		this.amount = amount;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
}
