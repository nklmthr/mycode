package com.nklmthr.finance.parser;

import java.text.SimpleDateFormat;
import java.util.List;

import com.nklmthr.finance.Transaction;

public abstract class CSVParser {
	
	public CSVParser(String path) {
		super();
		this.path = path;
	}
	protected SimpleDateFormat GoogleSheetDateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
	protected String path;
	public abstract List<Transaction> parse() throws Exception;
	public void csvPath(String path) {
		this.path = path;
	}
}
