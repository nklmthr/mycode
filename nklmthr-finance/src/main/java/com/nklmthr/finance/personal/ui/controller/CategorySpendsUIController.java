package com.nklmthr.finance.personal.ui.controller;

import java.time.YearMonth;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nklmthr.finance.personal.dao.CategorySpends;
import com.nklmthr.finance.personal.service.CategorySpendsService;

@Controller
@RequestMapping("/")
public class CategorySpendsUIController {
	Logger logger = Logger.getLogger(CategorySpendsUIController.class);

	@Autowired
	private CategorySpendsService categorySpendsService;

	@GetMapping("/")
	public String index(Model m) {
		return getCategorySpendsByMonth(m, null, null);
	}

	@GetMapping("/home")
	public String categoryFragment(Model m) {
		return getCategorySpendsByMonth(m, null, null);
	}

	@GetMapping("/home/{year}/{month}")
	public String getCategorySpendsByMonth(Model m, @PathVariable(value = "year") Integer year,
			@PathVariable(value = "month") Integer month) {
		YearMonth yearMonth = YearMonth.now();
		Integer nextMonthYear = year, nextMonth = month, previousMonthYear = year, previousMonth = month;
		if (year == null || year == 0) {
			year = yearMonth.getYear();
		}
		if (month == null || month == 0) {
			month = yearMonth.getMonth().getValue();
		}
		previousMonthYear = YearMonth.of(year, month).minusMonths(1).getYear();
		nextMonthYear = YearMonth.of(year, month).plusMonths(1).getYear();
		previousMonth = YearMonth.of(year, month).minusMonths(1).getMonth().getValue();
		nextMonth = YearMonth.of(year, month).plusMonths(1).getMonth().getValue();
		logger.info("year-month=" + year + "-" + month);
		m.addAttribute("previousMonth", previousMonth);
		m.addAttribute("previousMonthYear", previousMonthYear);
		m.addAttribute("nextMonth", nextMonth);
		m.addAttribute("nextMonthYear", nextMonthYear);
		m.addAttribute("currentYear", year);
		m.addAttribute("currentMonth", month);
		CategorySpends categorySpends= categorySpendsService.getCategorySpendsTree(year, month);
		logger.info("getCategorySpends " + categorySpends);
		m.addAttribute("categorySpends", categorySpends);
		return "index";
	}

	@GetMapping("/CategorySpendsFragment")
	public String getCategorySpends(Model m) {
		return "CategorySpendsFragment";
	}
}
