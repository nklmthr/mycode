package com.sap.ariba.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "DOCUMENT")
@Getter
@Setter
@ToString
public class Document {
	
	public Document() {
		super();
	}

	public Document(String fileName, String contentType, byte[] rawData) {
		this.identifier = UUID.randomUUID().toString();
		this.fileName = fileName;
		this.contentType = contentType;
		this.documentData = rawData;
		this.status="NEW";
	}

	@Id
	@Column(name = "identifier", nullable=false,columnDefinition="varchar(100)")
	private String identifier;

	@Column(name = "file_name", nullable=false,columnDefinition="varchar(255)")
	private String fileName;

	@Column(name = "content_type", nullable=false,columnDefinition="varchar(255)")
	private String contentType;

	@Column(name = "raw_data", columnDefinition="MEDIUMBLOB", nullable=false)
	private byte[] documentData;
	
	@Column(name="Status",nullable=false,columnDefinition="varchar(255)")
	private String status;
	
	@Basic(optional = false)
	@Column(name="created_ts", insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Column(name = "ocr_result", columnDefinition="MEDIUMBLOB", nullable=true)
	private String ocrResult;
	
	@Column(name = "retry_count", nullable=false,columnDefinition="SMALLINT")
	private long retryCount;
	
}
