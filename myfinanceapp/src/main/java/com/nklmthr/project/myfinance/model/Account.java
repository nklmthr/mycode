package com.nklmthr.project.myfinance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ACCOUNT")
@Getter
@Setter
@ToString
public class Account {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private String id;

	@Column(name = "description")
	private String description;
	
	@Column(name="short_name")
	private String shortName;
	
	@ManyToOne
	@JoinColumn(name = "account_category_id", referencedColumnName = "id")
	private AccountCategory accountCategory;

	@ManyToOne
	@JoinColumn(name = "account_type_id", referencedColumnName = "id")
	private AccountType accountType;
	
	@ManyToOne
	@JoinColumn(name = "institution_id", referencedColumnName = "id")
	private Institution institution;
}
