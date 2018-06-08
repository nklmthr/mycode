package com.nklmthr.an.utils.domain;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.xml.security.Init;
import org.apache.xml.security.c14n.Canonicalizer;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class DomainManager {
	private static Logger logger = Logger.getLogger(DomainManager.class);
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH-mm-ss");

	public static void changeNodeAttributeValue(Document doc, String nodeName, String attributeName, String newValue)
			throws Exception {
		NodeList nodeList = doc.getElementsByTagName(nodeName);
		logger.info("node:" + nodeName + ", attributeName:" + attributeName + ", new value=" + newValue);
		Stream<Node> nodeStream = IntStream.range(0, nodeList.getLength()).mapToObj(nodeList::item);
		Optional<Node> node = nodeStream.filter(s -> s.getNodeType() == Node.ELEMENT_NODE).findFirst();
		if (node.isPresent()) {
			node.get().getAttributes().getNamedItem(attributeName).setNodeValue(newValue);
		} else {
			throw new Exception("Node note found with name:" + nodeName);
		}
	}

	public static void postCXML(Document doc, String url) throws Exception {
		logger.info("Posting request to URL:" + url);
		String request = convertDocumentToString(doc);
		logger.info("request:" + request);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		StringEntity entity = new StringEntity(request);
		httpPost.setEntity(entity);

		// Create a custom response handler
		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

			@Override
			public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity) : null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			}

		};
		String response = httpclient.execute(httpPost, responseHandler);
		Document document = convertStringToDocument(response);
		logger.info("response:" + convertDocumentToString(document));
	}

	public static String convertDocumentToString(Document doc) throws Exception {
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
			Init.init();
			Canonicalizer canon = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_OMIT_COMMENTS);
			byte canonXmlBytes[] = canon.canonicalize(output.getBytes());
			String canonXmlString = new String(canonXmlBytes);
			logger.debug("document to string:" + output);
			return output;
		} catch (TransformerException e) {
			throw new Exception("Unable to convert to document to string");
		}
	}

	public static Document convertStringToDocument(String xmlStr) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
			return doc;
		} catch (Exception e) {
			throw new Exception("Unable to convert to string to document");
		}
	}

	public static InputStream getResourceAsInputStream(String resource) throws IOException {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream stream = classloader.getResourceAsStream(resource);
		return stream;
	}

	public static String getResource(String resource) throws IOException {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream stream = classloader.getResourceAsStream(resource);
		String content = IOUtils.toString(stream);
		return content;
	}
}
