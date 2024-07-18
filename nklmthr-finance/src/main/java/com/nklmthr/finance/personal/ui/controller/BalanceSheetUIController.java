package com.nklmthr.finance.personal.ui.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nklmthr.finance.personal.dao.MonthlyBalanceSummary;
import com.nklmthr.finance.personal.service.MonthlyBalanceService;

import io.micrometer.common.util.StringUtils;

@Controller
@RequestMapping("/")
public class BalanceSheetUIController {
	Logger logger = Logger.getLogger(BalanceSheetUIController.class);
	@Autowired
	MonthlyBalanceService monthlyBalanceService;

	@GetMapping("/BalanceSheet")
	public String getBalanceSheet(Model m) {
		MonthlyBalanceSummary monthlyBalanceSummary = monthlyBalanceService.getLastYearBalanceSheet();
		m.addAttribute("monthlyBalanceSummary", monthlyBalanceSummary);
		logger.debug("monthlyBalanceSummary" + monthlyBalanceSummary);
		return "balanceSheet/BalanceSheet";
	}

	@GetMapping("/generateMonthEndReport")
	public String generateMonthEndReport(Model m) {
		monthlyBalanceService.generateMonthEndReport();
		MonthlyBalanceSummary monthlyBalanceSummary = monthlyBalanceService.getLastYearBalanceSheet();
		m.addAttribute("monthlyBalanceSummary", monthlyBalanceSummary);
		logger.info("monthlyBalanceSummary" + monthlyBalanceSummary);
		return "balanceSheet/BalanceSheet";
	}

	@GetMapping("/fetchReportForMonth")
	public String fetchReportForMonth(Model m, @RequestParam(value = "dateStr") String dateStr) throws ParseException {
		logger.info("dateStr:" + dateStr);
		if (StringUtils.isBlank(dateStr)) {
			return "balanceSheet/BalanceSheet";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(dateStr, formatter);
		logger.info("date:" + date);
		MonthlyBalanceSummary monthlyBalanceSummary = monthlyBalanceService.getMonthlyBalanceSheet(date);
		logger.info("monthlyBalanceSummary:" + monthlyBalanceSummary.getDates().size());
		m.addAttribute("monthlyBalanceSummary", monthlyBalanceSummary);
		return "balanceSheet/BalanceSheet";
	}
}
