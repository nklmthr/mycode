package org.personal.util;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Level;
import org.personal.exception.RefreshException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XMLHelper {	

	public static String getStringFromDocument(Document doc)
			throws RefreshException {		
		try {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			return writer.toString();
		} catch (Exception ex) {			
			throw new RefreshException(ex,
					"getStringFromDocument() Exception due to"
							+ ex.getMessage());
		}
	}

	public static Document createDomDocument() throws RefreshException {		
		Document doc = null;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			doc = builder.newDocument();
		} catch (Exception ex) {			
			throw new RefreshException(ex,
					"createDomDocument() Exception due to " + ex.getMessage());
		}
		return doc;
	}

	public static String nodeToString(Node node) throws RefreshException {
		
		StringWriter sw = new StringWriter();
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			t.transform(new DOMSource(node), new StreamResult(sw));
		} catch (Exception ex) {
			
			throw new RefreshException(ex, "nodeToString() Exception due to "
					+ ex.getMessage());
		}
		return sw.toString();
	}

}
