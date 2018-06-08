package com.nklmthr.an.utils.domain;

public class BuyerDomainManager extends DomainManager {
	private static String buyerANCXMLDispatcherURL = "http://localhost:1114/scripts/WebObjects.dll/ANCXMLDispatcher.woa/ad/cxml";

	public static String getBuyerANCXMLDispatcherURL() {
		return buyerANCXMLDispatcherURL;
	}
}
