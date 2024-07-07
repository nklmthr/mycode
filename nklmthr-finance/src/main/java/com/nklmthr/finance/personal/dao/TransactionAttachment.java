package com.nklmthr.finance.personal.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.annotations.UuidGenerator;
import org.imgscalr.Scalr;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class TransactionAttachment {
	@Id
	@UuidGenerator
	@Column
	private String id;

	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date date;

	@Column
	private String fileName;

	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private byte[] imageData;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "transaction", referencedColumnName = "id")
	private Transaction transaction;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public String getFullImage() {
		return Base64.encodeBase64String(getImageData());
	}
	public String getThumbnail() throws IOException {
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(getImageData()));
		BufferedImage resizedthumbnailImage = Scalr.resize(image, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, 200,
				Scalr.OP_ANTIALIAS);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(resizedthumbnailImage,
				getFileName().substring(getFileName().lastIndexOf(".")+1, getFileName().length()), os);
		return Base64.encodeBase64String(os.toByteArray());
	}

	@Override
	public String toString() {
		try {
			return "TransactionAttachment [id=" + id + ", date=" + date + ", fileName=" + fileName + ", transaction="
					+ transaction.getDescription() + ", getThumbnail()=" + getThumbnail() + "]";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
