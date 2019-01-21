package com.ariba.microservices.globalisation.lsgstatemachine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "DOCUMENT")
@Getter
@Setter
@ToString
public class Document {
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name = "content")
	private String content;
	
	
}
