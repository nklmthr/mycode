package com.nklmthr.system.utils;

public class Invoice {
	private String payloadId;
	private String invoiceId;
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
	@Override
	public String toString() {
		return "Invoice [payloadId=" + payloadId + ", invoiceId=" + invoiceId + "]";
	}
	
}
