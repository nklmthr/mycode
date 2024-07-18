package com.nklmthr.finance.personal.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.nklmthr.finance.personal.service.MonthlyBalanceService;

public class MonthlyBalanceSummary {
	private static Logger logger = Logger.getLogger(MonthlyBalanceService.class);
	List<Map<String, String>> rows = new ArrayList<>();
	Map<Date, String> dates = new HashMap<>();

	public List<Map<String, String>> getRows() {
		return rows;
	}

	public Map<Date, String> getDates() {
		return dates;
	}

	public void setDates(Map<Date, String> dates) {
		this.dates = dates;
	}

	public void setRows(List<Map<String, String>> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "MonthlyBalanceSummary [rows=" + rows + ", dates=" + dates + "]";
	}
}
