package com.nklmthr.an.utils.dto;

public class Order {
	private String payloadId;
	private String orderId;
	public String getPayloadId() {
		return payloadId;
	}
	public void setPayloadId(String payloadId) {
		this.payloadId = payloadId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "Order [payloadId=" + payloadId + ", orderId=" + orderId + "]";
	}
	
}
