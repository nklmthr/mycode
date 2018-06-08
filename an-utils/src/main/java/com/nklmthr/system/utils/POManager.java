package com.nklmthr.system.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.nklmthr.an.utils.dto.Order;

public class POManager {
	private static Logger logger = Logger.getLogger(POManager.class);
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH-mm-ss");

	public static void main(String[] args) throws InterruptedException {
		DOMConfigurator.configure("log4j.xml");
		POManager manager = new POManager();

		try {
			int orderCount = 1;
			logger.info("Start Posting Orders..." + orderCount);
			List<Order> orders = manager.postOrdersFromBuyer("SamplePO-JP.xml",
					orderCount);
			logger.warn("Waiting for POs to be propogated to Supplier community...");
			Thread.sleep(15000);
			logger.warn("Wating completed...");
			manager.postPOInvoicesOnSupplier(
					"/Users/i344377/Box Sync/AN/sample cxml/Invoice/Order251000000Invoice1.xml", orders, 1);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void postPOInvoicesOnSupplier(String file, List<Order> orders, int numOfInvoices)
			throws SAXException, IOException, ParserConfigurationException, InterruptedException {
		String url = "http://localhost:1126/scripts/WebObjects.dll/ANCXMLDispatcher.woa/ad/cxml";
		Document doc = parseFile(file);
		for (int i = 0; i < orders.size(); i++) {
			Order order = orders.get(i);
			for (int j = 0; j < numOfInvoices; j++) {
				createInvoice(order, doc, url, j);

				Thread.sleep(10000);
			}
		}
	}

	private void createInvoice(Order order, Document doc, String url, int invoiceNumber)
			throws ClientProtocolException, IOException {
		changeMandatoryNodeAttributeValue(doc, "cXML", "payloadID",
				"InvoicePL" + LocalDateTime.now().format(formatter));
		changeMandatoryNodeAttributeValue(doc, "InvoiceDetailRequestHeader", "invoiceID",
				order.getOrderId() + "-Invoice" + String.format("%03d", invoiceNumber + 1));
		changeMandatoryNodeAttributeValue(doc, "OrderReference", "orderID", order.getOrderId());
		changeMandatoryNodeAttributeValue(doc, "DocumentReference", "payloadID", order.getPayloadId());
		logger.debug(convertDocumentToString(doc));
		logger.info("creating invoice for Order " + order.getOrderId() + " invoice=" + order.getOrderId() + "-Invoice"
				+ String.format("%03d", invoiceNumber + 1));
		postCXML(doc, url);

	}

	private List<Order> postOrdersFromBuyer(String file, int count)
			throws ClientProtocolException, IOException, SAXException, ParserConfigurationException {
		String url = "http://localhost:1114/scripts/WebObjects.dll/ANCXMLDispatcher.woa/ad/cxml";

		Document doc = parseFile(file);
		List<Order> orders = new ArrayList<Order>();
		for (int i = 0; i < count; i++) {
			Order order = new Order();
			order.setPayloadId("OrderPL" + LocalDateTime.now().toString());
			order.setOrderId("Order" + LocalDateTime.now().format(formatter));
			changeOrderDocument(doc, order);
			logger.debug(convertDocumentToString(doc));
			postCXML(doc, url);
			orders.add(order);
		}
		return orders;
	}

	private void changeOrderDocument(Document doc, Order order) {
		changeMandatoryNodeAttributeValue(doc, "cXML", "payloadID", order.getPayloadId());
		changeMandatoryNodeAttributeValue(doc, "OrderRequestHeader", "orderID", order.getOrderId());

	}

	private void postCXML(Document doc, String url) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);

		StringEntity entity = new StringEntity(convertDocumentToString(doc));
		httpPost.setEntity(entity);

		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity respEntity = response.getEntity();

		if (respEntity != null) {
			String content = EntityUtils.toString(respEntity);
			logger.info(content);
		}

	}

	private void changeMandatoryNodeAttributeValue(Document doc, String nodeName, String attributeName,
			String newValue) {
		NodeList nodeList = doc.getElementsByTagName(nodeName);
		for (int temp = 0; temp < nodeList.getLength(); temp++) {
			Node node = nodeList.item(temp);
			logger.debug("change attribute " + attributeName + " from="
					+ node.getAttributes().getNamedItem(attributeName) + " to=" + newValue);
			node.getAttributes().getNamedItem(attributeName).setNodeValue(newValue);
		}

	}

	private Document parseFile(String cXMLFile) throws SAXException, IOException, ParserConfigurationException {
		File file = new File(cXMLFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		return doc;
	}

	private static String convertDocumentToString(Document doc) {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			// below code to remove XML declaration
			// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
			// "yes");
			DocumentType doctype = doc.getDoctype();
			if (doctype != null) {
				// transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,
				// doctype.getPublicId());
				transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
			}
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			String output = writer.getBuffer().toString();
			return output;
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static Document convertStringToDocument(String xmlStr) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
