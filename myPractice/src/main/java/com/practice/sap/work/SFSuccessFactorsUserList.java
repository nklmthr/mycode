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
		for (int page = 1; page < 43; page++) {
			System.out.println("Processing page "+page);
//			System.out.println("page:" + page);
			HttpResponse<String> response = Unirest.post(
					"https://qacand.hcm.ondemand.com/acme?bplte_company=RWDVF8&fbacme_o=admin&itrModule=rewarding&_s.crb=i6blEvK57L6SW7Cd1EpNwN3Ak7imEesvKy3Y1XY%252fMo4%253d&")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.header("Cookie", "\r\n"
							+ "loginMethodCookieKey=PWD; route=ce3f45a6526e3c4860494071f2b1ad1d1121203c; bizxThemeId=1z0beuq2ca; fontstyle=null; bizxCompanyId=RWDVF8; BIGipServer~net_bd50ec9f_4652_44cc_8c3e_014201a323f8~lb_17147c27-86e3-42f8-a627-cbc312c3bb6b~pool_d30b1bdd-93e1-4db7-88a4-2facd1f2fb08=rd2789o00000000000000000000ffff0aed081eo80; JSESSIONID=36059909461A0B5373F3AC45FCC4B973.mc25bcf12; zsessionid=03c2e743-3908-4bee-b346-d2b0fec22b9f")
					.header("Referer",
							"https://qacand.hcm.ondemand.com/acme?bplte_p_hide_v12_layout_and_header_url=true&bplte_company=RWDVF8&fbacme_o=admin&_s.crb=MG9Ciaq%2f3Ufgep%2b5l%2faZhryvD4l0jKtiD11qlGlS3D4%3d&ap_param_action=comp_admin_plan_mgt&fbca_comp_plan_hasVarPay=false&fbca_comp_plan_plan_id=460&fbca_comp_plan_ttlCmp=true&itrModule=rewarding")
					.body("ap_param_action=comp_admin_plan_mgt&fbca_comp_plan_plan_id=460&fbca_comp_plan_listsize=250"
							+ "&fbca_comp_plan_hasVarPay=false&fbca_comp_plan_ttlCmp=true"
							+ "&sortFieldIndex=&sortDirection=&deleteTypeIdx=&exportTypeIdx=&ajaxSecKey=&bplte_p_hide_v12_layout_and_header_url=true"
							+ "&fbca_comp_plan_employee_type=planner&fbca_comp_plan_employee="
							+ "&fbca_comp_plan_employee_text=&fbca_comp_plan_division_selection=All"
							+ "&fbca_comp_plan_division=&fbca_comp_plan_department_selection=All&fbca_comp_plan_department="
							+ "&fbca_comp_plan_location_selection=All&fbca_comp_plan_location="
							+ "&fbca_comp_plan_status=&ps_defaultPaginationBean=250&nps_defaultPaginationBean=250"
							+ "&ri_defaultPaginationBean="
							+  (page*250))
//					.body("ap_param_action=comp_admin_plan_mgt&fbca_comp_plan_plan_id=356&fbca_comp_plan_listsize=10&fbca_comp_plan_hasVarPay=false&fbca_comp_plan_ttlCmp=true&sortFieldIndex=&sortDirection=&deleteTypeIdx=&exportTypeIdx=&ajaxSecKey=&bplte_p_hide_v12_layout_and_header_url=true&fbca_comp_plan_employee_type=planner&fbca_comp_plan_employee=&fbca_comp_plan_employee_text=&fbca_comp_plan_division=&fbca_comp_plan_department_selection=All&fbca_comp_plan_department=&fbca_comp_plan_location_selection=All&fbca_comp_plan_location=&fbca_comp_plan_status=&"
//							+ "ps_defaultPaginationBean=250&nps_defaultPaginationBean=250"							
//							+ (page * 250)
//							+ "&ps_defaultPaginationBean=250&nps_defaultPaginationBean=10&ri_defaultPaginationBean=250")
					.asString();
			String responseStr = response.getBody();
			CleanerProperties props = new CleanerProperties();
			props.setPruneTags("script");
			TagNode resultHmtl = new HtmlCleaner(props).clean(responseStr);
//			System.out.println(responseStr);
			String path = "//*[@id=\"planListTbl\"]/tbody/tr";
			// *[@id="planListTbl"]/tbody/tr[4]/td[3]
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
//					System.out.println("tdNode:" + tdNodeList.getTextContent());
					if (tdNodeList.getTextContent() != null && j == 3) {
						String cellValue = tdNodeList.getTextContent().trim();
						
//						System.out.println(cellValue.substring(cellValue.indexOf("(")+1, cellValue.indexOf(")")));
						resultSet.add(cellValue);											
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
