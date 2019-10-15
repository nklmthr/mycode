package com.nklmthr.work.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.InflaterInputStream;

import org.drools.core.util.IoUtils;

public class DeflatorExample {
	public static void main(String[] args) throws DataFormatException, IOException {
//		String message = "Welcome to TutorialsPoint.com;" + "Welcome to TutorialsPoint.com;"
//				+ "Welcome to TutorialsPoint.com;" + "Welcome to TutorialsPoint.com;" + "Welcome to TutorialsPoint.com;"
//				+ "Welcome to TutorialsPoint.com;" + "Welcome to TutorialsPoint.com;" + "Welcome to TutorialsPoint.com;"
//				+ "Welcome to TutorialsPoint.com;" + "Welcome to TutorialsPoint.com;";
//		System.out.println("Original Message length : " + message.length());
//		FileInputStream fis = new FileInputStream(new File(
//				"/Users/i344377/resource/InvoiceDetailRequestAttachments/2019/3/12/dev3-1552378243782-34343-11-7216.zip"));
//		// byte[] input = message.getBytes("UTF-8");
//		byte[] input = IoUtils.readBytes(new File(
//				"/Users/i344377/resource/InvoiceDetailRequestAttachments/2019/3/12/dev3-1552378243782-34343-11-7216.zip"));
//		// Compress the bytes
//		byte[] output = new byte[1024];
//
//		FileOutputStream fos = new FileOutputStream(
//				"/Users/i344377/resource/InvoiceDetailRequestAttachments/2019/3/12/test.zip");

		// Assign FileOutputStream to DeflaterOutputStream
//		InflaterInputStream dos = new InflaterInputStream(fos);
//
//		// read data from FileInputStream and write it into DeflaterOutputStream
//		int data;
//		while ((data = fis.read()) != -1) {
//			dos.write(data);
//		}

		FileInputStream fis=new FileInputStream("/Users/i344377/resource/InvoiceDetailRequestAttachments/2019/3/12/dev3-1552378243782-34343-11-7216.zip"); 
		  
        //assign output file: file3 to FileOutputStream for reading the data 
        FileOutputStream fos=new FileOutputStream("/Users/i344377/resource/InvoiceDetailRequestAttachments/2019/3/12/test123"); 
          
        //assign inflaterInputStream to FileInputStream for uncompressing the data 
        InflaterInputStream iis=new InflaterInputStream(fis); 
        System.out.println(fis.getChannel()); 
        //read data from inflaterInputStream and write it into FileOutputStream  
        int data; 
        while((data=iis.read())!=-1) 
        { 
            fos.write(data); 
        } 
          
        //close the files 
        fos.close(); 
        iis.close(); 
		// close the file
		fis.close();
		fos.close();
	}

}
