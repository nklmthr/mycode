package com.srishti.geektrust.metro.reports;

public class CollectionSummary {

	Double totalDiscount;
	Double totalAmount;

	public CollectionSummary() {
		super();
	}

	public CollectionSummary(Double totalDiscount, Double totalAmount) {
		super();
		this.totalAmount = totalAmount;
		this.totalDiscount = totalDiscount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

}
