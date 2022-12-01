package com.nklmthr.project.myfinance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.opencsv.bean.CsvBindByPosition;

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
	@CsvBindByPosition(position = 0)
	private String id;

	@Column(name = "name")
	@CsvBindByPosition(position =1)
	private String name;

	@Column(name = "description")
	@CsvBindByPosition(position = 2)
	private String description;

	@ManyToOne
	@JoinColumn(name = "parent_category_id", referencedColumnName = "id")
	@CsvBindByPosition(position = 3)
	private TransactionCategory parent;

}
