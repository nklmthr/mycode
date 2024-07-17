package com.nklmthr.finance.personal.ui.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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
		MonthlyBalanceSummary monthlyBalanceSheet = monthlyBalanceService.getLastMonthBalanceSheet();
		Map<String, Double> sumamryList = new HashMap<>(monthlyBalanceSheet.getDates().size());

		for (Date date : monthlyBalanceSheet.getDates()) {
			logger.info("*********date:" + date);
			for (Map<String, String> map : monthlyBalanceSheet.getRows()) {
				Double summ = 0.0;
				if (map.containsKey(date.toString())) {
					logger.info("map" + map);
					summ = map.entrySet().stream().filter(s -> s.getKey().equals(date.toString()))
							.filter(s -> !s.getKey().equals("Classification"))
							.collect(Collectors.summarizingDouble(s -> Double.valueOf(s.getValue()))).getSum();
					logger.info("summ" + summ);
					if (sumamryList.containsKey(date.toString())) {
						sumamryList.put(date.toString(), sumamryList.get(date.toString()) + summ);
					} else {
						sumamryList.put(date.toString(), summ);
					}
				}
			}
		}
		m.addAttribute("monthlyBalanceSheet", monthlyBalanceSheet);
		m.addAttribute("sumamryList", sumamryList);
		logger.info("sumamryList" + sumamryList);
		return "balanceSheet/BalanceSheet";
	}

	@GetMapping("/generateMonthEndReport")
	public String generateMonthEndReport(Model m) {
		monthlyBalanceService.generateMonthEndReport();
		MonthlyBalanceSummary summReport = monthlyBalanceService.getLastMonthBalanceSheet();
		m.addAttribute("monthlyBalanceSheetDate", new Date());
		m.addAttribute("monthlyBalanceSheet", summReport);
		return "balanceSheet/BalanceSheet";
	}

	@GetMapping("/fetchReportForMonth")
	public String fetchReportForMonth(Model m, @RequestParam(value = "dateStr") String dateStr) throws ParseException {
		logger.info("dateStr:" + dateStr);
		if (StringUtils.isBlank(dateStr)) {
			return "balanceSheet/BalanceSheet";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(dateStr);
		logger.info("date:" + date);
		MonthlyBalanceSummary monthlyBalanceSheet = monthlyBalanceService.getMonthlyBalanceSheet(date);
		logger.info("monthlyBalanceSheet:" + monthlyBalanceSheet.getRows().size());
		m.addAttribute("monthlyBalanceSheet", monthlyBalanceSheet);
		return "balanceSheet/BalanceSheet";
	}
}
