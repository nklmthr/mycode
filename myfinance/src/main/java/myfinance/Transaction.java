package myfinance;

import java.util.Date;

public class Transaction {
	private Date date;
	private String activity;
	private String fi;
	private String vehicle;
	private boolean purchase = false;
	private Double price;
	private Double quantity;
	private Double currPrice;
	private Double EurToInrRate;
	private Double value;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getFi() {
		return fi;
	}
	public void setFi(String fi) {
		this.fi = fi;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public boolean isPurchase() {
		return purchase;
	}
	public void setPurchase(boolean purchase) {
		this.purchase = purchase;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getCurrPrice() {
		return currPrice;
	}
	public void setCurrPrice(Double currPrice) {
		this.currPrice = currPrice;
	}
	public Double getEurToInrRate() {
		return EurToInrRate;
	}
	public void setEurToInrRate(Double eurToInrRate) {
		EurToInrRate = eurToInrRate;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return String.format(
				"Transaction [date=%s, activity=%s, fi=%s, vehicle=%s, purchase=%s, price=%s, quantity=%s, currPrice=%s, EurToInrRate=%s, value=%s]",
				date, activity, fi, vehicle, purchase, price, quantity, currPrice, EurToInrRate, value);
	}
	
	
}
