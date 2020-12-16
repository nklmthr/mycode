package com.nklmthr.finance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class TransactionUtils {
	private static SimpleDateFormat GoogleSheetDateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
	public static List<List<Object>> getTransactionsAsValuesList(List<Transaction> transactions) {
		List<List<Object>> objectValues = new ArrayList<List<Object>>();
		for (Transaction transaction : transactions) {
			List<Object> objects = new ArrayList<Object>();
			objects.add(GoogleSheetDateFormatter.format(transaction.getDate()));
			objects.add(transaction.getAccount().name());
			objects.add(transaction.getDescription());
			objects.add(transaction.getAmount());
			objects.add(transaction.getCategory() != null ? transaction.getCategory().name() : StringUtils.EMPTY);
			objects.add(transaction.getReference() != null ? transaction.getReference() : StringUtils.EMPTY);
			objectValues.add(objects);
		}
		return objectValues;
	}
}
