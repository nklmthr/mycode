package com.nklmthr.finance.parser.banks;

import java.io.FileReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.nklmthr.finance.Account;
import com.nklmthr.finance.Transaction;
import com.nklmthr.finance.parser.CSVParser;
import com.opencsv.CSVReader;

public class SBICardManualStatementParser extends CSVParser {

	public SBICardManualStatementParser(String path) {
		super(path);
	}

	@Override
	public List<Transaction> parse() throws Exception {
		List<Transaction> transactions = new ArrayList<Transaction>();
		CSVReader reader = new CSVReader(new FileReader(path));
		List<String[]> myEntries = reader.readAll();
		for (String[] string : myEntries) {

			boolean isCredit = string[2].endsWith("Credit");
			int commas = StringUtils.countMatches(string[3], ",");
			
			String amountStr = string[3].replace(",", "");
			BigDecimal amount = new BigDecimal(amountStr);
			if (isCredit) {
				amount = amount.multiply(new BigDecimal(-1));
			}
			Transaction transaction = new Transaction();
			transaction.setDate(new SimpleDateFormat("dd-MM-yyyy").parse(string[0]));
			transaction.setAccount(Account.SBI_CC);
			transaction.setDescription(string[1]);
			transaction.setAmount(amount);
			transaction.setCredit(isCredit);
			transaction.setReference(StringUtils.EMPTY);
			transactions.add(transaction);

		}
		return transactions;
	}

}
