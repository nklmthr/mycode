package com.nklmthr.finance.personal.service;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public MonthlyBalanceSummary getMonthlyBalanceSheet(Date date) {
		Integer year = date.getYear() + 1900;
		Integer month = date.getMonth() + 1;
		Integer day = date.getDate();
		return getMonthlyBalanceByDate(year, month, day);
	}

	public MonthlyBalanceSummary getLastMonthBalanceSheet() {

		YearMonth current = YearMonth.now();
		Integer year = current.getYear();
		Integer month = current.getMonthValue();

		return getMonthlyBalanceByDate(year, month, new Date().getDay());
	}

	private MonthlyBalanceSummary getMonthlyBalanceByDate(Integer year, Integer month, Integer day) {
		MonthlyBalanceSummary summReport = new MonthlyBalanceSummary();
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
		logger.info(summReport);
		return summReport;
	}

	private Date checkIfDateExists(MonthlyBalanceSummary summReport, Date date) {
		if (summReport.getDates().size() == 0) {
			summReport.getDates().add(date);
			return date;
		}
		Date latest = summReport.getDates().stream().sorted().findFirst().get();
		long diff = latest.getTime() - date.getTime();
		long diffDays = TimeUnit.DAYS.convert(Math.abs(diff), TimeUnit.MILLISECONDS);
		if (diffDays > 0) {
			summReport.getDates().add(date);
		}
		return date;

	}

	public void generateMonthEndReport() {
		Date currentDate = new Date();
		List<MonthlyBalance> monthlyBalances = monthlyBalanceRepository.findByDate(currentDate.getYear() + 1900,
				currentDate.getMonth() + 1, currentDate.getDay());
		if (monthlyBalances.size() > 0) {
			return;
		}
		List<Account> accounts = accountService.getAllAccounts();
		for (Account account : accounts) {
			MonthlyBalance mb = new MonthlyBalance();
			mb.setDate(currentDate);
			mb.setAccount(account);
			mb.setAmount(account.getTransactionBalance());
			monthlyBalanceRepository.save(mb);
		}
	}

}
