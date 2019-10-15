package com.nklmthr.work.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GenerateRunFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("/SAPDevelop/AN/ws/config/WebObjects.xml");

		BufferedReader br = new BufferedReader(new FileReader(file));

		StringBuilder sb = new StringBuilder();
		String st = null;
		while ((st = br.readLine()) != null){
			sb.append(st);
		}
		br.close();
		Document doc = convertStringToXMLDocument(sb.toString());
//		System.out.println(doc.toString());
		NodeList nodelist = doc.getElementsByTagName("application");
		for(int i=0;i<nodelist.getLength();i++){
			Element node = (Element) nodelist.item(i);
			String app = node.getAttribute("name");
//			System.out.println(app);
			NodeList instantnodeList = node.getChildNodes();
			for(int j=0;j<instantnodeList.getLength();j++){
				Node instantNode = instantnodeList.item(j);
				if(instantNode.getNodeType()==Node.ELEMENT_NODE){
					Element instanceElement = (Element)instantNode;
					String port = instanceElement.getAttribute("port");
//					System.out.println(port);
					String community = instanceElement.getAttribute("community");
//					System.out.println(community);
					String instanceId = instanceElement.getAttribute("id");
//					System.out.println(instanceId);
					
					System.out.println("exec_wo_app "+app+" "+ port +" -d "+(port+1)+" -cl true -c "+app+" -id "+instanceId+" -t "+app.toLowerCase()+" -n "+app+" -l $ARIBA_INSTALL_ROOT/WebObjects/Apps/"+app+".woa");
				}
			}
			
		}
		
	}
	
	private static Document convertStringToXMLDocument(String xmlString)
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         
        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();
             
            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
