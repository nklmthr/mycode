package com.srishti.geektrust.metro.ratecard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.srishti.geektrust.metro.controllers.TravelCategory;

@Entity(name = "RATE_CARD")
public class RateCard {
	@Id
	@Column  
	private String id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CATEGORY")
	private TravelCategory category;
	@Column
	private Double amount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TravelCategory getCategory() {
		return category;
	}
	public void setCategory(TravelCategory category) {
		this.category = category;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
