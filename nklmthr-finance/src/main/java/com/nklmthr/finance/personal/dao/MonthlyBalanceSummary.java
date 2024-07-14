package com.nklmthr.finance.personal.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.thymeleaf.expression.Dates;

import com.nklmthr.finance.personal.service.MonthlyBalanceService;

public class MonthlyBalanceSummary {
	private static Logger logger = Logger.getLogger(MonthlyBalanceService.class);
	List<Map<String, String>> rows = new ArrayList<>();
	List<Date> dates = new ArrayList<>();

	public List<Map<String, String>> getRows() {
		return rows;
	}

	public List<Date> getDates() {
		return dates;
	}

	public void setRows(List<Map<String, String>> rows) {
		this.rows = rows;
	}

	public Date addDate(Date date) {
		Date existingDate = checkIfDateAlreadyPresent(date);
		if (existingDate!=null) {
			dates.add(existingDate);
			return existingDate;
		} else if (dates.size() == 0) {
			dates.add(date);
			return date;
		}
		return existingDate;
	}

	private Date checkIfDateAlreadyPresent(Date date) {
		long minDiff = Long.MAX_VALUE;
		Date existingDate = null;
		for (Date curr : dates) {
			long diff = curr.getTime() - date.getTime();
			long diffDays = TimeUnit.HOURS.convert(Math.abs(diff), TimeUnit.MILLISECONDS);
			logger.info("curr:" + curr + ", diffDays:" + diffDays + ", minDiff=" + minDiff + ", dates=" + dates);
			if (diffDays < minDiff) {
				minDiff = diffDays;
				existingDate = curr;
			}
		}
		if (minDiff > 22) {
			return date;
		}
		return existingDate;
	}

}
