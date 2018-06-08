package com.nklmthr.system.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;

import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.security.KeyStoreUtil;
import com.itextpdf.text.pdf.security.PdfPKCS7;

public class PDFTest {
	public static void main(String args[]) {
		try {
			testPDF();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected static void testPDF() throws Exception {
		KeyStoreUtil.loadCacertsKeyStore();

		Class c = Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider");
		java.security.Security.insertProviderAt((java.security.Provider) c.newInstance(), 2000);

		byte[] bytes = IOUtils.toByteArray(
				new FileInputStream(new File("/Users/i344377/Desktop/2018 01 15 an_signed_original.pdf")));

		PdfReader reader;
		try {
			reader = new PdfReader(bytes);
		} catch (IOException e1) {
			throw new Exception(e1);
		}
		AcroFields af = reader.getAcroFields();

		ArrayList<?> names = af.getSignatureNames();
		
		for (int k = 0; k < names.size(); ++k) {
			String name = (String) names.get(k);
			PdfPKCS7 pk = null;
			try {
				pk = af.verifySignature(name);
				System.out.println(pk.getSignDate());
				System.out.println(pk.verify());
				
			} catch (ExceptionConverter e1) {
				e1.printStackTrace();
			}
		}
	}

}

