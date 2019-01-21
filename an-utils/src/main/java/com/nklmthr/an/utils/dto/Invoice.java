package com.nklmthr.an.utils.dto;

public class Invoice {
	private String payloadId;
	private String invoiceId;
	private String orderId;
	private boolean isPOInvoice;
	private String orderPayloadId;
	public String getOrderPayloadId() {
		return orderPayloadId;
	}
	public void setOrderPayloadId(String orderPayloadId) {
		isPOInvoice = true;
		this.orderPayloadId = orderPayloadId;
	}
	public String getPayloadId() {
		return payloadId;
	}
	public void setPayloadId(String payloadId) {
		this.payloadId = payloadId;
	}
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		isPOInvoice = true;
		this.orderId = orderId;
	}
	public boolean isPOInvoice() {
		return isPOInvoice;
	}
	public void setPOInvoice(boolean isPOInvoice) {
		this.isPOInvoice = isPOInvoice;
	}
	
	
}
