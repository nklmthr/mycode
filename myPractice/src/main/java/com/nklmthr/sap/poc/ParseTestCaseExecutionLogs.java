package com.nklmthr.sap.poc;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseTestCaseExecutionLogs {

	public static void main(String[] args) throws IOException {
		File input = new File("C:\\Users\\I344377\\OneDrive - SAP SE\\Desktop\\html.html");
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
		Elements links = doc.select("a");
		Iterator<Element> itr = links.iterator();
		while(itr.hasNext()) {
			Element obj = itr.next();
			if(obj.html().startsWith("com.successfactors")) {
				System.out.println(""+obj.html());
			}else {
				System.out.println("\t\t\t\t"+obj.html());
			}
		}		
	}

}
