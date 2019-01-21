package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
@ToString
public class Employee {
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name = "name")
	private String name;

}
