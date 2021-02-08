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

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getDocumentData() {
		return documentData;
	}

	public void setDocumentData(byte[] documentData) {
		this.documentData = documentData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getOcrResult() {
		return ocrResult;
	}

	public void setOcrResult(String ocrResult) {
		this.ocrResult = ocrResult;
	}

	public long getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(long retryCount) {
		this.retryCount = retryCount;
	}
	
}
