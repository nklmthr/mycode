package com.nklmthr.work.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

public class CSVMerger {

	public static void main(String[] args) throws Exception {
		List<String> files = new ArrayList<String>();
		File folder = new File("/Users/i344377/OneDrive - SAP SE/Ariba/NP-18352");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {

				if (listOfFiles[i].getName().startsWith("comm")) {
					// System.out.println("File " + listOfFiles[i].getName());
					files.add(listOfFiles[i].getAbsolutePath());
				}

			} else if (listOfFiles[i].isDirectory()) {

				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
		List<List<String>> results = new ArrayList<List<String>>();
		for (String file : files) {
			System.out.println("Processing File " + file);
			results.addAll(readWithCsvListReader(file));
		}

		System.out.println(results.size());
		writeWithCsvListWriter(results);

	}

	private static void writeWithCsvListWriter(List<List<String>> results) throws Exception {

		ICsvListWriter listWriter = null;
		try {
			listWriter = new CsvListWriter(new FileWriter("/Users/i344377/OneDrive - SAP SE/Ariba/NP-18352/final.csv"),
					CsvPreference.EXCEL_PREFERENCE);

			CellProcessor[] processors = new CellProcessor[] { 
					new Optional(), // ID
					new Optional(), // DOCUMENT_NUMBER
					new Optional(), // FROM_ORG
					new Optional(), // FROM_ORG_ANID
					new Optional(), // FROM_ORG_NAME
					new Optional(), // TO_ORG
					new Optional(), // TO_ORG_ANID
					new Optional(), // TO_ORG_NAME
					new Optional(), // DOC_STATUS
					new Optional(), // DASHBOARD_STATUS
					new Optional(), // DOCUMENT_TYPE
					new Optional(), // LEG_ITEM_STATUS
					new Optional(), // STEP
					new Optional(), // RETRY_COUNT
					new Optional() // Q_STATUS
			};
			final String[] header = new String[] { "ID", "DOCUMENT_NUMBER", "FROM_ORG", "FROM_ORG_ANID",
					"FROM_ORG_NAME", "TO_ORG", "TO_ORG_ANID", "TO_ORG_NAME", "DOC_STATUS", "DASHBOARD_STATUS",
					"DOCUMENT_TYPE", "LEG_ITEM_STATUS", "STEP", "RETRY_COUNT", "Q_STATUS" };

			// write the header
			listWriter.writeHeader(header);
			for(List<String> result: results){
				listWriter.write(result, processors);
			}
			

		} finally {
			if (listWriter != null) {
				listWriter.close();
			}
		}
	}

	private static List<List<String>> readWithCsvListReader(String file) throws Exception {
		List<List<String>> results  = new ArrayList<List<String>>();

		// "ID","DOCUMENT_NUMBER","FROM_ORG","FROM_ORG_ANID","FROM_ORG_NAME","TO_ORG","TO_ORG_ANID",
		// "TO_ORG_NAME","DOC_STATUS","DASHBOARD_STATUS","DOCUMENT_TYPE","LEG_ITEM_STATUS",
		// "STEP","RETRY_COUNT","Q_STATUS"
		CellProcessor[] processors = new CellProcessor[] { new Optional(), // ID
				new Optional(), // DOCUMENT_NUMBER
				new Optional(), // FROM_ORG
				new Optional(), // FROM_ORG_ANID
				new Optional(), // FROM_ORG_NAME
				new Optional(), // TO_ORG
				new Optional(), // TO_ORG_ANID
				new Optional(), // TO_ORG_NAME
				new Optional(), // DOC_STATUS
				new Optional(), // DASHBOARD_STATUS
				new Optional(), // DOCUMENT_TYPE
				new Optional(), // LEG_ITEM_STATUS
				new Optional(), // STEP
				new Optional(), // RETRY_COUNT
				new Optional() // Q_STATUS
		};
		ICsvListReader listReader = null;
		try {
			listReader = new CsvListReader(new FileReader(file), CsvPreference.STANDARD_PREFERENCE);

			listReader.getHeader(true); // skip the header (can't be used with
										// CsvListReader)
			List<String> customerList;
			List<Object> cust;
			while ((cust = listReader.read(processors)) != null) {
				System.out.println(String.format("lineNo=%s, rowNo=%s, customerList=%s", listReader.getLineNumber(),
						listReader.getRowNumber(), cust));
				customerList = new ArrayList<String>();
				for(Object o : cust){
					customerList.add(o.toString());
				}
				results.add(customerList);
			}

		} finally {
			if (listReader != null) {
				listReader.close();
			}
		}
		return results;
	}

}
