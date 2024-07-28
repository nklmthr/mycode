package com.nklmthr.finance.personal.service;

public enum CategoryType {
	EARNING(true, false, false, false, false, false), EXPENSE(false, true, false, false, false, false),
	NOT_CLASSIFIED(false, false, true, false, false, false), TRANSFERS(false, false, false, true, false, false),
	SPLIT(false, false, false, false, true, false), CASH(false, false, false, false, false, true);

	private boolean expense;
	private boolean earning;
	private boolean notClassified;
	private boolean transfer;
	private boolean split;
	private boolean cash;

	private CategoryType(boolean expense, boolean earning, boolean notClassified, boolean transfer, boolean split,
			boolean cash) {
		this.earning = earning;
		this.expense = expense;
		this.notClassified = notClassified;
		this.transfer = transfer;
		this.split = split;
		this.cash = cash;
	}

	public String getCategoryType() {
		return name();
	}

	public boolean isExpense() {
		return expense;
	}

	public boolean isEarning() {
		return earning;
	}

	public boolean isNotClassified() {
		return notClassified;
	}

	public boolean isTransfer() {
		return transfer;
	}

	public boolean isSplit() {
		return split;
	}

	public boolean isCash() {
		return cash;
	}

}
