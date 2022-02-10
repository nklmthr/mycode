package org.nklmthr.model;

import java.time.Instant;

public class MoneyTransaction {
	private Instant dateOfTran;
	private String description;
	private Number amount;
	private boolean addsToPortfolio;

	public Instant getDateOfTran() {
		return dateOfTran;
	}

	public void setDateOfTran(Instant dateOfTran) {
		this.dateOfTran = dateOfTran;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Number getAmount() {
		return amount;
	}

	public void setAmount(Number amount) {
		this.amount = amount;
	}

	public boolean isAddsToPortfolio() {
		return addsToPortfolio;
	}

	public void setAddsToPortfolio(boolean addsToPortfolio) {
		this.addsToPortfolio = addsToPortfolio;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MoneyTransaction [dateOfTran=").append(dateOfTran).append(", description=").append(description)
				.append(", amount=").append(amount).append(", addsToPortfolio=").append(addsToPortfolio).append("]");
		return builder.toString();
	}

}
