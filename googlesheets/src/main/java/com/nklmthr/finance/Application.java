package com.nklmthr.finance;

import java.util.ArrayList;
import java.util.List;

import com.nklmthr.finance.googlesheets.GoogleSheetManager;
import com.nklmthr.finance.parser.CSVParser;
import com.nklmthr.finance.parser.banks.CitiManualStatementParser;
import com.nklmthr.finance.parser.banks.ICICICSVParser;
import com.nklmthr.finance.parser.banks.SBICardManualStatementParser;

public class Application {

	/**
	 * Prints the names and majors of students in a sample spreadsheet:
	 * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
	 * 
	 * @throws Exception
	 */
	public static void main(String... args) throws Exception {
		List<Transaction> fiTransactions = new ArrayList<Transaction>();
		CSVParser parser = new ICICICSVParser("C:\\Users\\I344377\\Desktop\\CCStatement21-06-2020.csv");
		List<Transaction> iciciTransactions = parser.parse();
		fiTransactions.addAll(iciciTransactions);
		CSVParser parser2 = new SBICardManualStatementParser("C:\\Users\\I344377\\Desktop\\SBICard21-June-2.csv");
		List<Transaction> sbiTransactions = parser2.parse();
		fiTransactions.addAll(sbiTransactions);
		
		CSVParser parser3 = new CitiManualStatementParser("C:\\Users\\I344377\\Desktop\\citibank-21Jun.csv");
		List<Transaction> citiTrans = parser3.parse();
		fiTransactions.addAll(citiTrans);
		
		//System.out.println(fiTransactions);
		GoogleSheetManager manager = new GoogleSheetManager();
		List<Transaction> googleSheetTransactions = manager.getTransactions();
		//System.out.println(googleSheetTransactions);
		new TransactionManager().updateTransactionsToBeUpdate(fiTransactions,googleSheetTransactions);
		//System.out.println(googleSheetTransactions);
		manager.updateTransactions(fiTransactions);
	}

}