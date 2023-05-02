package com.srishti.geektrust.metro.card;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.srishti.geektrust.metro.controllers.TravelCategory;

@Entity(name = "METRO_CARD")
public class MetroCard {
	@Id
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column
	private String id;

	@Column(name = "NAME")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name="CATEGORY")
	private TravelCategory category;
	
	@Column(name = "BALANCE")
	private Double balance;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TravelCategory getCategory() {
		return category;
	}

	public void setCategory(TravelCategory category) {
		this.category = category;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
