package com.nklmthr.mycode.game29.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import com.nklmthr.mycode.game29.exception.RefreshException;

public class HTMLDOMHelper {


	public static void addLineBreak(Document doc, Node node)
			throws RefreshException {
		try {
			node.appendChild(doc.createElement("br"));
		} catch (Exception ex) {
			throw new RefreshException(ex, ex.getMessage());
		}

	}

	public static void addTextNode(Document doc, Node node,
			String targetObjectId, String text) throws RefreshException {
		try {
			
			Node colDataNode = doc.getElementById(targetObjectId);
			Text textNode = doc.createTextNode(text);
			if (node != null) {
				node.appendChild(textNode);
			} else {
				colDataNode.appendChild(textNode);
			}

		} catch (Exception ex) {
			throw new RefreshException(ex, "addTextNode() due to"
					+ ex.getMessage());
		}
	}

	public static void addHTMLSubmitButton(Document doc, String targetCellId,
			String name, String value, String onClick) throws RefreshException {
		try {
			Node cellDataNode = doc.getElementById(targetCellId);

			Element button = doc.createElement("input");
			button.setAttribute("type", "submit");
			button.setAttribute("name", name);
			button.setAttribute("value", value);
			button.setAttribute("onClick", onClick);
			cellDataNode.appendChild(button);
		} catch (Exception ex) {
			throw new RefreshException(ex, "addHTMLSubmitButton() due to"
					+ ex.getMessage());
		}

	}

	public static void addHTMLTable(Document doc, String border, String width,
			String color, int row, int col) throws RefreshException {
		try {
			Element table = doc.createElement("table");
			table.setAttribute("ObjectId", "table");
			table.setIdAttribute("ObjectId", true);
			table.setAttribute("border", border);
			table.setAttribute("width", width);
			table.setAttribute("bgcolor", color);

			for (int i = 1; i <= row; i++) {
				Element rowNode = doc.createElement("tr");
				rowNode.setAttribute("ObjectId", "tr_" + i);
				rowNode.setIdAttribute("ObjectId", true);
				for (int j = 1; j <= col; j++) {
					Element colNode = doc.createElement("td");
					colNode.setAttribute("ObjectId", "td_" + i + j);
					colNode.setIdAttribute("ObjectId", true);
					rowNode.appendChild(colNode);
				}
				table.appendChild(rowNode);
			}
			doc.appendChild(table);
		} catch (Exception ex) {
			throw new RefreshException(ex, "addHTMLTable() due to"
					+ ex.getMessage());
		}
	}

	public static void addHTMLInput(Document doc, String targetObjectId,
			String inputNodeId, String inputName, String inputType,
			String inputValue) throws RefreshException {
		try {
			Element node = doc.getElementById(targetObjectId);

			Element inputNode = doc.createElement("input");
			inputNode.setAttribute("type", inputType);
			inputNode.setAttribute("name", inputName);
			inputNode.setAttribute("ObjectId", inputNodeId);
			inputNode.setIdAttribute("ObjectId", true);
			inputNode.setAttribute("value", inputValue);

			node.appendChild(node);
		} catch (Exception ex) {
			throw new RefreshException(ex, "addHTMLInput() due to"
					+ ex.getMessage());
		}
	}

	public static Node addHTMLhRef(Document doc, Node hrefValue, String hRefLoc)
			throws RefreshException {
		try {
			Element hRefNode = doc.createElement("a");
			hRefNode.setAttribute("href", hRefLoc);
			hRefNode.appendChild(hrefValue);
			return hRefNode;
		} catch (Exception ex) {
			throw new RefreshException(ex, "addHTMLhRef() due to"
					+ ex.getMessage());
		}
	}

	public static Node addHtmlImage(Document doc, String name, String src,
			String alt) throws RefreshException {
		try {
			Element imgNode = doc.createElement("img");
			imgNode.setAttribute("src", src);
			imgNode.setAttribute("alt", alt);
			imgNode.setAttribute("name", name);
			return imgNode;
		} catch (Exception ex) {
			throw new RefreshException(ex, "addHtmlImage() due to"
					+ ex.getMessage());
		}
	}

}
