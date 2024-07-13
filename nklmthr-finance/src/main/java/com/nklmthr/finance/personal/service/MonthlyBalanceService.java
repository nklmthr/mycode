package com.nklmthr.finance.personal.service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nklmthr.finance.personal.dao.Account;
import com.nklmthr.finance.personal.dao.MonthlyBalance;
import com.nklmthr.finance.personal.dao.MonthlyBalanceRepository;
import com.nklmthr.finance.personal.dao.MonthlyBalanceSummary;

@Service
public class MonthlyBalanceService {
	private static Logger logger = Logger.getLogger(MonthlyBalanceService.class);

	@Autowired
	MonthlyBalanceRepository monthlyBalanceRepository;

	@Autowired
	AccountService accountService;

	public List<MonthlyBalanceSummary> getMonthlyBalanceSheet(Date date) {
		Integer year = date.getYear() + 1900;
		Integer month = date.getMonth() + 1;
		Integer day = date.getDate();
		return getMonthlyBalanceByDate(year, month, day);
	}

	public List<MonthlyBalanceSummary> getLastMonthBalanceSheet() {

		YearMonth current = YearMonth.now();
		Integer year = current.getYear();
		Integer month = current.getMonthValue();

		return getMonthlyBalanceByDate(year, month, new Date().getDay());
	}

	private List<MonthlyBalanceSummary> getMonthlyBalanceByDate(Integer year, Integer month, Integer day) {
		List<MonthlyBalanceSummary> monthlyBalanceSummaryList = new ArrayList<MonthlyBalanceSummary>();
		List<MonthlyBalance> monthlyBalances = monthlyBalanceRepository.findAllMonthBalanceForLastMonth(year, month);
		logger.info("monthlyBalances:" + monthlyBalances.size());
		Set<String> accountyTypeClassifications = monthlyBalances.stream()
				.map(s -> s.getAccount().getAccountType().getClassification()).collect(Collectors.toSet());
		for (String classfication : accountyTypeClassifications) {
			MonthlyBalanceSummary mbs = new MonthlyBalanceSummary();
			mbs.setAccountSummaryType(classfication);
			mbs.setDate(monthlyBalances.stream()
					.filter(s -> s.getAccount().getAccountType().getClassification().equals(classfication)).findAny()
					.get().getDate());
			mbs.setAmount(new BigDecimal(monthlyBalances.stream()
					.filter(s -> s.getAccount().getAccountType().getClassification().equals(classfication))
					.collect(Collectors.summarizingDouble(e -> e.getAccount().getTransactionBalance().doubleValue()))
					.getSum()));
			monthlyBalanceSummaryList.add(mbs);
		}
		return monthlyBalanceSummaryList;
	}

	public void generateMonthEndReport() {
		Date currentDate = new Date();
		List<MonthlyBalance> monthlyBalances = monthlyBalanceRepository.findByDate(currentDate.getYear() + 1900,
				currentDate.getMonth() + 1, currentDate.getDay());
		if(monthlyBalances.size() > 0) {
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
