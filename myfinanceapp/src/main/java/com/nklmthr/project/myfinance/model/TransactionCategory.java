package com.nklmthr.project.myfinance.model;

import java.util.Date;

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
@Table(name = "TRANSACTION_CATEGORY")
@Getter
@Setter
@ToString
public class TransactionCategory {
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column(name="name")
	private Date name;
	
	@Column(name = "description")
	private boolean description;
	
	@ManyToOne
	@JoinColumn(name = "parent_category_id", referencedColumnName = "id")
	private TransactionCategory parent;
	

}
