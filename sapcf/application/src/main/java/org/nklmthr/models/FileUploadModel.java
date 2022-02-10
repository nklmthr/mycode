package org.nklmthr.models;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "UPLOADED_FILES")
public class FileUploadModel {
	@JsonProperty
	@Id	
	private String id;
	@JsonProperty
	private String fileName;

	@JsonProperty
	private String status;
	
	@JsonProperty
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="file_type_id")
	private FileType fileType;
	
	@JsonProperty
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="account_type_id")
	private AccountType accountType;

	@JsonProperty
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="institution_id")
	private Institution institution;

	@JsonProperty
	@Lob
	private byte[] contents;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public byte[] getContents() {
		return contents;
	}

	public void setContents(byte[] contents) {
		this.contents = contents;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileUploadModel [id=").append(id).append(", fileName=").append(fileName).append(", status=")
				.append(status).append(", fileType=").append(fileType).append(", accountType=").append(accountType)
				.append(", institution=").append(institution).append("]");
		return builder.toString();
	}

	

	

}
