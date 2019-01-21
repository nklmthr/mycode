package com.nklmthr.an.utils.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.nklmthr.an.utils.dto.Order;

public class POManager extends BuyerDomainManager {
	private static Logger logger = Logger.getLogger(POManager.class);

	public static void main(String[] args) {
		/*
		 * Dont use this main method for more then this. This is for testing
		 */
		try {
			createOrders(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<Order> createOrders(int count) throws Exception {
		List<Order> orders = postOrdersToBuyerForSupplier("samples/SamplePO-JP.xml", count);
		return orders;
	}

	private static List<Order> postOrdersToBuyerForSupplier(String file, int count) throws Exception {
		Document doc = DomainManager.convertStringToDocument(getResource(file));
		List<Order> orders = new ArrayList<Order>();
		for (int i = 0; i < count; i++) {
			Order order = new Order();
			order.setPayloadId("OrderPL" + LocalDateTime.now().toString());
			order.setOrderId("Order" + LocalDateTime.now().format(formatter_dttm));
			changeOrderDocument(doc, order);
			logger.debug(convertDocumentToString(doc));
			postCXML(doc, getBuyerANCXMLDispatcherURL());
			orders.add(order);
		}
		return orders;
	}

	private static void changeOrderDocument(Document doc, Order order) throws Exception {
		changeNodeAttributeValue(doc, "/cXML", "cXML", "payloadID", order.getPayloadId());
		changeNodeAttributeValue(doc, "cXML/Request/OrderRequest/OrderRequestHeader", "OrderRequestHeader", "orderID", order.getOrderId());

	}

}
