package com.sap.ariba;

public class RequestContext {
	private static final ThreadLocal<RequestContext> context = new ThreadLocal<>(); 
    private String transactionId;
    public static RequestContext getContext() {
        RequestContext result = context.get();
        if (result == null) {
            result = new RequestContext();
            context.set(result);
        }
        return result;
    }
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
