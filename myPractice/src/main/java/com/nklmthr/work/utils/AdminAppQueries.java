package com.nklmthr.work.utils;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;

public class AdminAppQueries {

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException {
		try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {

			HtmlPage page = webClient.getPage("https://admin.snv.ariba.com:8443/Admin.aw");
			String pageAsText = page.asText();
			// System.out.println(pageAsText);
			HtmlForm form = page.getFormByName("loginForm");
			HtmlTextInput textField = form.getInputByName("UserName");
			textField.type("i344377_admin@ariba.com");
			HtmlPasswordInput passwordField = form.getInputByName("Password");
			passwordField.type("Ii21&Je@AJnd");
			System.out.println(textField);
			System.out.println(passwordField);
			HtmlDivision div = (HtmlDivision) form.getByXPath("//div[@class='rbBC rbBFC rbB']").get(0);
			HtmlPage page2 = div.click();
			//System.out.println(page2.asXml());
			HtmlAnchor systemAnchor = (HtmlAnchor) page2.getByXPath("//a[text()='Inspect']").get(0);
			HtmlPage page3 = systemAnchor.click();
			// System.out.println(systemAnchor);
			//Page page3 = systemAnchor.click();
			//Thread.sleep(2000);
			//HtmlPage page3a = (HtmlPage) page3;
			// System.out.println(page3.isHtmlPage());
			System.out.println("page3\n" + page3.asXml());
			HtmlAnchor sqlAnchor = (HtmlAnchor) page3.getByXPath("//a[@id='_meqa$']").get(0);
			System.out.println(sqlAnchor);

			HtmlPage page4 = sqlAnchor.dblClick();
			// System.out.println("page4\n"+page4.asText());

		}

	}

}
