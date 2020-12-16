package com.nklmthr.finance;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction implements Comparable<Transaction> {
	private Date date;
	private Account account;
	private String description;
	private BigDecimal amount;
	private String reference;
	private Category category;
	private boolean isCredit;
	private boolean isDebit;
	private String externalReference;

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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean isCredit() {
		return isCredit;
	}

	public void setCredit(boolean isCredit) {
		this.isCredit = isCredit;
	}

	public boolean isDebit() {
		return !isCredit;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [date=").append(date).append(", account=").append(account).append(", description=")
				.append(description).append(", amount=").append(amount).append(", reference=").append(reference)
				.append(", category=").append(category).append(", isCredit=").append(isCredit).append(", isDebit=")
				.append(isDebit).append("]\n");
		return builder.toString();
	}
	@Override
	public int compareTo(Transaction tran2) {
		if (this.getReference().equals(tran2.getReference())) {
			return 0;
		} else if (this.getDate().equals(tran2.getDate())
				&& this.getDescription().equalsIgnoreCase(tran2.getDescription())
				&& this.getAmount().equals(tran2.getAmount()) && this.getAccount().equals(tran2.getAccount())) {
			return 0;
		} else if (this.getDate().after(tran2.getDate())) {
			return 1;
		}
		return -1;
	}

	public String getExternalReference() {
		return externalReference;
	}

	public void setExternalReference(String externalReference) {
		this.externalReference = externalReference;
	}

}
