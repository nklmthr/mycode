package code.project.india.projects.ppp;

import java.sql.Date;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

public class Transaction {
	@CsvDate(value = "dd-MMM")
	@CsvBindByPosition(position = 0)
	private Date date;

	@CsvBindByPosition(position = 1)
	private int accountId;

	@CsvBindByPosition(position = 2)
	private String description;

	@CsvBindByPosition(position = 3)
	private int categoryID;

	@CsvBindByPosition(position = 4)
	private double amount;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Transaction [date=" + date + ", accountId=" + accountId + ", description=" + description
				+ ", categoryID=" + categoryID + ", amount=" + amount + "]";
	}

}
