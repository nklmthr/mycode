package com.nklmthr.finance.parser.banks;

import java.io.FileReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.nklmthr.finance.Account;
import com.nklmthr.finance.Transaction;
import com.nklmthr.finance.parser.CSVParser;
import com.opencsv.CSVReader;

public class ICICICSVParser extends CSVParser {

	public ICICICSVParser(String path) {
		super(path);
	}

	public List<Transaction> parse() throws Exception {
		List<Transaction> transactions = new ArrayList<Transaction>();
		int j = 0;
		CSVReader reader = new CSVReader(new FileReader(path));
		List<String[]> myEntries = reader.readAll();
		for (String[] string : myEntries) {
			if (j > 13) {
				boolean isCredit = string[6].endsWith("Cr.");
				int commas = StringUtils.countMatches(string[6], ",");
				String amountStr = string[6].replace(",", "").substring(0, string[6].length() - (4 + commas));
				BigDecimal amount = new BigDecimal(amountStr);
				if (isCredit) {
					amount = amount.multiply(new BigDecimal(-1));
				}
				Transaction transaction = new Transaction();
				transaction.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(string[2]));
				transaction.setAccount(Account.ICICI_CC);
				transaction.setDescription(string[3]);
				transaction.setAmount(amount);
				transaction.setCredit(isCredit);
				transaction.setReference(string[8]);
				transactions.add(transaction);
			}
			j++;
		}
		return transactions;
	}

}
