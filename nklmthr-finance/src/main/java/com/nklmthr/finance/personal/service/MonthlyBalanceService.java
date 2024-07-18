package com.nklmthr.finance.personal.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nklmthr.finance.personal.dao.Account;
import com.nklmthr.finance.personal.dao.MonthlyBalance;
import com.nklmthr.finance.personal.dao.MonthlyBalanceRepository;
import com.nklmthr.finance.personal.dao.MonthlyBalanceSummary;
import com.nklmthr.finance.personal.dao.MonthlyBalanceSummaryDTO;

@Service
public class MonthlyBalanceService {
	private static Logger logger = Logger.getLogger(MonthlyBalanceService.class);

	@Autowired
	MonthlyBalanceRepository monthlyBalanceRepository;

	@Autowired
	AccountService accountService;

	public MonthlyBalanceSummary getMonthlyBalanceSheet(LocalDate date) {
		Integer year = date.getYear();
		Integer month = date.getMonth().getValue();
		return getMonthlyBalanceByDate(year, month);
	}

	public MonthlyBalanceSummary getLastYearBalanceSheet() {

		YearMonth current = YearMonth.now();
		YearMonth yearBack = current.minusMonths(12);
		Integer year = yearBack.getYear();
		Integer month = yearBack.getMonthValue();

		return getMonthlyBalanceByDate(year, month);
	}

	private MonthlyBalanceSummary getMonthlyBalanceByDate(Integer year, Integer month) {
		MonthlyBalanceSummary summReport = new MonthlyBalanceSummary();
		logger.info("Fetch Summary after:" + year + " month:" + month);
		List<MonthlyBalanceSummaryDTO> monthlyBalances = monthlyBalanceRepository.findAllMonthBalanceForLastMonth(year,
				month);
		logger.info("monthlyBalances:" + monthlyBalances.size());
		for (MonthlyBalanceSummaryDTO mb : monthlyBalances) {
			Date checkedDateExists = checkIfDateExists(summReport, mb.getDate());
			Optional<Map<String, String>> optional = summReport.getRows().stream()
					.filter(s -> s.containsKey("Classification"))
					.filter(s -> s.get("Classification").equals(mb.getDescription())).findFirst();
			Map<String, String> row = null;
			if (optional.isEmpty()) {
				row = new HashMap<String, String>();
				row.put("Classification", mb.getDescription());
				summReport.getRows().add(row);
			} else {
				row = optional.get();
			}
			row.put(checkedDateExists.toString(), mb.getAmount().toString());
		}
		logger.debug(summReport);
		for (Date date : summReport.getDates().keySet()) {
			logger.debug("*********date:" + date);
			for (Map<String, String> map : summReport.getRows()) {
				Double summ = 0.0;
				if (map.containsKey(date.toString())) {
					logger.debug("map" + map);
					summ = map.entrySet().stream().filter(s -> s.getKey().equals(date.toString()))
							.filter(s -> !s.getKey().equals("Classification"))
							.collect(Collectors.summarizingDouble(s -> Double.valueOf(s.getValue()))).getSum();
					logger.debug("summ" + summ);
					if (summReport.getDates().containsKey(date)) {
						if (summReport.getDates().get(date) != null) {
							Double sum = Double.valueOf(summReport.getDates().get(date)) + summ;
							summReport.getDates().put(date, String.valueOf(sum));
						} else {
							summReport.getDates().put(date, String.valueOf(summ));
						}
					} else {
						summReport.getDates().put(date, String.valueOf(summ));
					}
				}
			}
		}
		return summReport;
	}

	private Date checkIfDateExists(MonthlyBalanceSummary summReport, Date date) {
		if (summReport.getDates().size() == 0) {
			summReport.getDates().put(date, null);
			return date;
		}
		Date latest = summReport.getDates().keySet().stream().sorted().findFirst().get();
		long diff = latest.getTime() - date.getTime();
		long diffDays = TimeUnit.DAYS.convert(Math.abs(diff), TimeUnit.MILLISECONDS);
		if (diffDays > 0) {
			summReport.getDates().put(date, null);
		}
		return date;

	}

	public void generateMonthEndReport() {
		LocalDate curr = LocalDate.now();
		LocalDate threeWeeksBack = curr.minusWeeks(3);
		List<MonthlyBalance> monthlyBalances = monthlyBalanceRepository
				.findMonthlyDataBalanceForLastThreeWeek(threeWeeksBack.getYear(), threeWeeksBack.getMonth().getValue());
		if (monthlyBalances.size() > 0) {
			return;
		}
		List<Account> accounts = accountService.getAllAccounts();
		for (Account account : accounts) {
			MonthlyBalance mb = new MonthlyBalance();
			mb.setDate(Date.from(curr.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			mb.setAccount(account);
			mb.setAmount(account.getTransactionBalance());
			monthlyBalanceRepository.save(mb);
		}
	}

}
