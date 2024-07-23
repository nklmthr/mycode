package com.nklmthr.finance.personal.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import com.nklmthr.finance.personal.dao.Account;
import com.nklmthr.finance.personal.dao.MonthlyBalance;
import com.nklmthr.finance.personal.service.AccountService;

public class MonthlySummaryImporter {

	@Autowired
	AccountService accountService;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMM-yyyy");
	private static SimpleDateFormat edateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) {
		Format indianCurrencyFormat = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
		String s = "-40000000000.00";
		System.out.println(indianCurrencyFormat.format(new BigDecimal(s)));
	}
	
	
	public static void main1(String[] args) throws IOException, ParseException {
		MonthlySummaryImporter inst = new MonthlySummaryImporter();

		ICsvListReader accountIdMappingreader = new CsvListReader(new FileReader(
				"C:\\Develop\\github.com\\nklmthr\\mycode\\nklmthr-finance\\src\\main\\resources\\AccountIdMappingICICIDirect.csv"),
				CsvPreference.STANDARD_PREFERENCE);

		List<Object> accountsMappings;
		Map<String, String> accountIdMap = new HashMap<String, String>();
		while ((accountsMappings = accountIdMappingreader.read(getProcessorsForAccountIdMapping())) != null) {
			accountIdMap.put(accountsMappings.get(0).toString(), String.valueOf(accountsMappings.get(1)));

		}
//		System.out.println("accountIdMap" + accountIdMap);
		if (accountIdMappingreader != null) {
			accountIdMappingreader.close();
		}
//		accountIdMap.entrySet().stream().forEach(s -> System.out.println(s.getValue()));
		List<String> filter = accountIdMap.entrySet().stream().filter(s -> s.getValue().equals("null"))
				.map(s -> s.getKey()).collect(Collectors.toList());
//		System.out.println("filter" + filter);
		filter.stream().forEach(s -> accountIdMap.remove(s));
		System.out.println("vaccountIdMap" + accountIdMap);

		ICsvListReader accountSummparyMappingreader = new CsvListReader(new FileReader(
				"C:\\Develop\\github.com\\nklmthr\\mycode\\nklmthr-finance\\src\\main\\resources\\Balance Sheet - ICICIDirect.csv"),
				CsvPreference.STANDARD_PREFERENCE);
		List<Object> accountsBalances;

		DecimalFormat decFormat = new DecimalFormat("₹###,##,###.##");
		boolean header = true;
		List<Date> dates = new ArrayList<Date>();
		List<MonthlyBalance> summaryPerAccount = new ArrayList<MonthlyBalance>();
		while ((accountsBalances = accountSummparyMappingreader
				.read(getProcessorsForAccountSummaryMapping())) != null) {

			if (header) {
				for (int k = 2; k < accountsBalances.size(); k++) {
					// System.out.println(accountsBalances.get(k).toString());
					Date date = dateFormat.parse(accountsBalances.get(k).toString());
					dates.add(date);
				}
				header = false;
				System.out.println(dates);
			}

			if (accountSummparyMappingreader.getRowNumber() > 8) {
//				System.out.println(accountsBalances);
				for (int k = 2; k < accountsBalances.size(); k++) {
					MonthlyBalance mb = new MonthlyBalance();
					mb.setDate(dates.get(k - 2));
					Account account = new Account();
					if (accountIdMap.get(accountsBalances.get(0)) != null) {
						System.out.println("accountsBalances.get(0)=" + accountsBalances.get(0)
								+ ", accountIdMap.get(accountsBalances.get(0)):"
								+ accountIdMap.get(accountsBalances.get(0)));
						mb.setId(UUID.randomUUID().toString());
						account.setId(accountIdMap.get(accountsBalances.get(0)));
						mb.setAccount(account);
						String valueFromcsv = accountsBalances.get(k).toString();
						mb.setAmount(new BigDecimal(decFormat.parse(valueFromcsv).toString()));
						summaryPerAccount.add(mb);
					}
				}

			}

		}
		System.out.println(summaryPerAccount);
		/*
		 * ICICI direct merge code
		 */
		
		for (int i =0; i< summaryPerAccount.size();i++) {
			MonthlyBalance mb = summaryPerAccount.get(i);
			
			for (int j= 0; j<summaryPerAccount.size();j++) {
				MonthlyBalance mbother = summaryPerAccount.get(j);
				if (!mb.getId().equals(mbother.getId()) && mb.getDate().equals(mbother.getDate())) {
					mb.setAmount(mbother.getAmount().add(mb.getAmount()));
					summaryPerAccount.remove(j);
					j--;
					i--;
				}
			}
		}
		if (accountSummparyMappingreader != null) {
			accountSummparyMappingreader.close();
		}

		writeToCsv(summaryPerAccount);
	}

	private static void writeToCsv(List<MonthlyBalance> summaryPerAccount) throws IOException {
		ICsvListWriter writer = new CsvListWriter(new FileWriter("output.csv"), CsvPreference.STANDARD_PREFERENCE);
		CellProcessor[] processors = getWriterProcessors();
		for (MonthlyBalance bal : summaryPerAccount) {
			List<Object> objects = new ArrayList<Object>();
			objects.add(bal.getId());
			objects.add(bal.getAmount());
			objects.add(edateFormat.format(bal.getDate()));
			objects.add(bal.getAccount().getId());
			System.out.println(objects);
			writer.write(objects, processors);
		}
		if (writer != null) {
			writer.close();
		}

	}

	private static CellProcessor[] getWriterProcessors() {
		final CellProcessor[] processors = new CellProcessor[] { new NotNull(), new NotNull(), new NotNull(),
				new NotNull() };
		return processors;
	}

	private static CellProcessor[] getProcessorsForAccountSummaryMapping() {
		final CellProcessor[] processors = new CellProcessor[86];
		for (int i = 0; i < 86; i++) {
			processors[i] = new Optional();
		}

		return processors;
	}

	private static CellProcessor[] getProcessorsForAccountIdMapping() {
		final CellProcessor[] processors = new CellProcessor[] { new NotNull(), new Optional() };
		return processors;
	}

}
