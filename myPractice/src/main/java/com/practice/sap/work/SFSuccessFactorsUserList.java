package com.practice.sap.work;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SFSuccessFactorsUserList {

	public static void main(String[] args) throws UnirestException, XPathExpressionException,
			ParserConfigurationException, SAXException, IOException, XPatherException {
		// TODO Auto-generated method stub
		extract();
	}

	private static void extract() throws UnirestException, XPathExpressionException, ParserConfigurationException,
			SAXException, IOException, XPatherException {
		Set<String> resultSet = new HashSet<String>();
		Unirest.setTimeouts(0, 0);
		for (int page = 0; page < 24; page++) {
//			System.out.println("page:" + page);
			HttpResponse<String> response = Unirest.post(
					"https://qacand.hcm.ondemand.com/acme?bplte_company=RWDNBCUnivT2&fbacme_o=admin&itrModule=rewarding&_s.crb=32B1p8dExhLT8WGekMoQhpOnC%2bvGm%2fgaZnUdQtm1%2bnc%3d")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.header("Cookie", "\r\n"
							+ "loginMethodCookieKey=PWD; route=c249d7cdf981ce76e77b986f908c89deb3d85ada; bizxCompanyId=RWDNBCUnivT2; bizxThemeId=27obg8pky; JSESSIONID=668DBBE7885390B58484DD64E362C3D7.mc25bcf07; zsessionid=b1475024-305b-4eaa-966b-430c68b632af; BIGipServer~net_bd50ec9f_4652_44cc_8c3e_014201a323f8~lb_17147c27-86e3-42f8-a627-cbc312c3bb6b~pool_d30b1bdd-93e1-4db7-88a4-2facd1f2fb08=rd2789o00000000000000000000ffff0aed0818o80")
					.header("Referer",
							"https://qacand.hcm.ondemand.com/acme?bplte_p_hide_v12_layout_and_header_url=true&bplte_company=RWDNBCUnivT2&fbacme_o=admin&_s.crb=Czt41EGeisMDA%2fMB5jLBAHoErzRzxCq77wVcpKNl2S8%3d&ap_param_action=comp_admin_plan_mgt&fbca_comp_plan_hasVarPay=false&fbca_comp_plan_plan_id=356&fbca_comp_plan_ttlCmp=true&itrModule=rewarding")
					.body("ap_param_action=comp_admin_plan_mgt&fbca_comp_plan_plan_id=356&fbca_comp_plan_listsize=10&fbca_comp_plan_hasVarPay=false&fbca_comp_plan_ttlCmp=true&sortFieldIndex=&sortDirection=&deleteTypeIdx=&exportTypeIdx=&ajaxSecKey=&bplte_p_hide_v12_layout_and_header_url=true&fbca_comp_plan_employee_type=planner&fbca_comp_plan_employee=&fbca_comp_plan_employee_text=&fbca_comp_plan_division=&fbca_comp_plan_department_selection=All&fbca_comp_plan_department=&fbca_comp_plan_location_selection=All&fbca_comp_plan_location=&fbca_comp_plan_status=&"
							+ "ps_defaultPaginationBean=250&nps_defaultPaginationBean=250&ri_defaultPaginationBean="
							+ (page * 250)
							+ "&ps_defaultPaginationBean=250&nps_defaultPaginationBean=10&ri_defaultPaginationBean=250")
					.asString();
			String responseStr = response.getBody();
			CleanerProperties props = new CleanerProperties();
			props.setPruneTags("script");
			TagNode resultHmtl = new HtmlCleaner(props).clean(responseStr);
//			System.out.println(responseStr);
			String path = "//*[@id=\"planListTbl\"]/tbody/tr";
			Object[] result = resultHmtl.evaluateXPath(path);
//			System.out.println("result size:" + result.length);

			org.w3c.dom.Document doc = new DomSerializer(new CleanerProperties()).createDOM(resultHmtl);
			XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList nodeList = (NodeList) xpath.compile(path).evaluate(doc, XPathConstants.NODESET);
//			System.out.println(nodeList.getLength());
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node trNode = nodeList.item(i);
				NodeList trNodeList = trNode.getChildNodes();
				for (int j = 0; j < trNodeList.getLength(); j++) {
					Node tdNodeList = trNodeList.item(j);
					// System.out.println("tdNode:"+tdNodeList.getTextContent());
					if (tdNodeList.getTextContent() != null
							&& tdNodeList.getTextContent().trim().startsWith("worksheet")) {
						String cellValue = tdNodeList.getTextContent().trim();
//						System.out.println(cellValue.substring(cellValue.indexOf("(")+1, cellValue.indexOf(")")));
						resultSet.add(cellValue.substring(cellValue.indexOf("(")+1, cellValue.indexOf(")")));											
					}
				}

			}
		}
		System.out.println(Arrays.toString(resultSet.toArray()));
	}

	// method to convert Document to String
	public static String getStringFromDocument(Document doc) {
		try {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			return writer.toString();
		} catch (TransformerException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private static String nodeToString(Node node) {
		StringWriter sw = new StringWriter();
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.transform(new DOMSource(node), new StreamResult(sw));
		} catch (TransformerException te) {
			System.out.println("nodeToString Transformer Exception");
		}
		return sw.toString();
	}
}
