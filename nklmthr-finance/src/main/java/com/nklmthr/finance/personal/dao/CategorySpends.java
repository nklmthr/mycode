package com.nklmthr.finance.personal.dao;

import java.math.BigDecimal;

public class CategorySpends extends Category implements Comparable<CategorySpends> {

	private BigDecimal amount = new BigDecimal(0.00);

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
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
