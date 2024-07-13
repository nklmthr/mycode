package com.nklmthr.finance.personal.dao;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AccountType {

	@Id
	@UuidGenerator
	@Column
	private String id;

	@Column
	private String name;

	@Column
	private String description;

	@Column
	private String classification;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	@Override
	public String toString() {
		return "AccountType [id=" + id + ", name=" + name + ", description=" + description + ", classification="
				+ classification + "]";
	}

}
