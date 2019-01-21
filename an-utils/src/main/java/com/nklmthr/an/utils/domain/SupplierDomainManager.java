package com.nklmthr.an.utils.domain;

public class SupplierDomainManager extends DomainManager {

	private static String supplierANCXMLDispatcherURL = "http://localhost:1126/scripts/WebObjects.dll/ANCXMLDispatcher.woa/ad/cxml";

	protected static String getSupplierANCXMLDispatcherURL() {
		return supplierANCXMLDispatcherURL;
	}
}
