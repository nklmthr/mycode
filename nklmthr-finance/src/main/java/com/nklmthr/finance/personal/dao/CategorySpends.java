package com.nklmthr.finance.personal.dao;

import java.util.Comparator;

public class CategorySpends extends Category implements Comparable<CategorySpends> {

	private Double amount = new Double(0.00);

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {	
		return super.hashCode();
	}
	@Override
	public int compareTo(CategorySpends o) {
		if(this.getLevel() > o.getLevel()) {
			return 1;
		}else if(this.getLevel() < o.getLevel()) { 
			return -1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "CategorySpends [name="+ getName()+",amount=" + amount +"]";
	}	
}
