package com.nklmthr.finance.sbicard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TransactionSeperator {
	public static void main(String[] args) {
		String file = "/Users/i344377/Desktop/transactions.txt";
		BufferedReader reader = null;
		FileWriter fileWriter = null;
		DateFormat istformat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat usFormat = new SimpleDateFormat("MM/dd/yyyy");
		try {
			fileWriter = new FileWriter("/Users/i344377/Desktop/transactions.csv");
			reader = new BufferedReader(new FileReader(new File(file)));
			String line = reader.readLine();
			while (line != null && line.trim().length() > 0) {
				// System.out.println(line);

				String date = line.substring(0, 10);
				// System.out.print(date);
				String amount = line.substring(line.lastIndexOf(" ") + 1, line.length());
				// System.out.print(amount);
				amount = amount.replaceAll(",","");
				String type = line.lastIndexOf("Credit") > 0
						? line.substring(line.lastIndexOf("Credit"), line.lastIndexOf(" "))
						: line.substring(line.lastIndexOf("Debit"), line.lastIndexOf(" "));
				// System.out.println(type);
				String description = line.substring(date.length() + 1, line.indexOf(type)-1);
				// System.out.println(description);
				fileWriter
						.append(date + "," + type + "," + description + "," + amount+"\n");
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
}
