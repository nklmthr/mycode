package com.nklmthr.finance.sbicard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionSeperator {
	public static void main(String[] args) {
		String file = "/Users/i344377/Desktop/Unbilled_Transactions.txt";
		BufferedReader reader = null;
		FileWriter fileWriter = null;
		DateFormat istformat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat usFormat = new SimpleDateFormat("MM/dd/yyyy");
		try {
			fileWriter = new FileWriter("/Users/i344377/Desktop/Unbilled_Transactions.csv");
			reader = new BufferedReader(new FileReader(new File(file)));
			String line = reader.readLine();
			while (line != null && line.trim().length() > 0) {
				// System.out.println(line);

				String date = line.substring(0, 10);
				//System.out.print(date);
				
				String amount = line.substring(line.lastIndexOf(" ") + 1, line.length());
				//System.out.print(amount);
			
				amount = amount.replaceAll(",","");
				BigDecimal am= new BigDecimal(amount);
				//System.out.println(am.toPlainString());
				String type = line.lastIndexOf("Credit") > 0
						? line.substring(line.lastIndexOf("Credit"), line.lastIndexOf(" "))
						: line.substring(line.lastIndexOf("Debit"), line.lastIndexOf(" "));
				// 
				if(type.equals("Credit")){
					
					am = am.negate();
				}
				String description = line.substring(date.length() + 1, line.indexOf(type)-1);
				// System.out.println(description);
				String outputLine = StringToDate(date) + "," + description + "," + am.toString()+"\n";
				System.out.print(outputLine);
				fileWriter
						.append(outputLine);
				line = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String StringToDate(String dob) throws ParseException {
		// Instantiating the SimpleDateFormat class
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		// Parsing the given String to Date object
		Date date = formatter.parse(dob);
		//System.out.println("Date object value: " + date);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); 
		return dateFormat.format(date);
	}
}
