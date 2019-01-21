package com.nklmthr.an.utils.domain;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.nklmthr.an.utils.dto.Invoice;
import com.nklmthr.an.utils.dto.Order;

public class InvoiceManager extends SupplierDomainManager {
	private static Logger logger = Logger.getLogger(InvoiceManager.class);

	public static void createInvoiceWithOrders(List<Order> orders) throws IOException, Exception {
		postInvoiceToBuyerFromSupplier("samples/SampleInvoice-SamplePO-Japan.xml", orders);

	}

	private static void postInvoiceToBuyerFromSupplier(String file, List<Order> orders) throws IOException, Exception {
		Document doc = DomainManager.convertStringToDocument(getResource(file));
		for (Order order : orders) {
			postInvoiceWithOrder(file, order, 1);
		}

	}

	private static List<Invoice> postInvoiceWithOrder(String file, Order order, int count) throws Exception {
		Document doc = DomainManager.convertStringToDocument(getResource(file));
		List<Invoice> invoices = new ArrayList<Invoice>();
		for (int i = 0; i < count; i++) {
			Invoice invoice = new Invoice();
			invoice.setPayloadId("InvociePL" + LocalDateTime.now().toString());
			invoice.setOrderId(order.getOrderId());
			invoice.setOrderPayloadId(order.getPayloadId());
			invoice.setInvoiceId(order.getOrderId() + "_Invoice" + LocalDateTime.now().format(formatter_tm) +"-"+ count);
			changeInvoiceDocument(doc, invoice);
			logger.debug(convertDocumentToString(doc));
			postCXML(doc, getSupplierANCXMLDispatcherURL());
			invoices.add(invoice);
		}
		return invoices;

	}

	private static void changeInvoiceDocument(Document doc, Invoice invoice) throws Exception {
		changeNodeAttributeValue(doc, "/cXML", "cXML", "payloadID", invoice.getPayloadId());
		changeNodeAttributeValue(doc, "/cXML/Request/InvoiceDetailRequest/InvoiceDetailRequestHeader",
				"InvoiceDetailRequestHeader", "invoiceID", invoice.getInvoiceId());
		changeNodeAttributeValue(doc,
				"/cXML/Request/InvoiceDetailRequest/InvoiceDetailOrder/InvoiceDetailOrderInfo/OrderReference",
				"OrderReference", "orderID", invoice.getOrderId());
		changeNodeAttributeValue(doc,
				"/cXML/Request/InvoiceDetailRequest/InvoiceDetailOrder/InvoiceDetailOrderInfo/OrderReference/DocumentReference",
				"DocumentReference", "payloadID", invoice.getOrderPayloadId());

	}

}
