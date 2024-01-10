package com.nklmthr.finance.personal.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CategorySpends extends Category {

	private BigDecimal amount = new BigDecimal(0.00);
	private List<CategorySpends> childCategorySpends = new ArrayList<>();
	private CategorySpends parentCategorySpends; 
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<CategorySpends> getChildCategorySpends() {
		return childCategorySpends;
	}

	public void setChildCategorySpends(List<CategorySpends> childCategorySpends) {
		this.childCategorySpends = childCategorySpends;
	}

	public CategorySpends getParentCategorySpends() {
		return parentCategorySpends;
	}

	public void setParentCategorySpends(CategorySpends parentCategorySpends) {
		this.parentCategorySpends = parentCategorySpends;
	}

	@Override
	public int hashCode() {	
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "CategorySpends [ name()=" + getName() +", amount=" + amount + ", childCategorySpends=" + childCategorySpends.size()
				+ ", parentCategorySpends=" + parentCategorySpends + "]";
	}

	
}
