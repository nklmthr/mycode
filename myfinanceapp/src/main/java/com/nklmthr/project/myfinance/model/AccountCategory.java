package com.nklmthr.project.myfinance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ACCOUNT_CATEGORY")
@Getter
@Setter
@ToString
public class AccountCategory {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "account_category_name")
	private String accountCategoryName;

}
