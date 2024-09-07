package com.nklmthr.finance.personal.ui.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nklmthr.finance.personal.dao.MonthlyBalanceSummary;
import com.nklmthr.finance.personal.exception.InvalidMessageException;
import com.nklmthr.finance.personal.scheduler.AxisBankCCSchedulerImpl;
import com.nklmthr.finance.personal.scheduler.AxisSBATMScheduleImpl;
import com.nklmthr.finance.personal.scheduler.AxisSBTransactionScheduleImpl;
import com.nklmthr.finance.personal.scheduler.AxisSBUPIScheduleImpl;
import com.nklmthr.finance.personal.scheduler.ICICIAmazonCCSchedulerImpl;
import com.nklmthr.finance.personal.scheduler.IDFCCCScheduleImpl;
import com.nklmthr.finance.personal.scheduler.SBICCSchedulerImpl;
import com.nklmthr.finance.personal.scheduler.ScheduledTask;
import com.nklmthr.finance.personal.scheduler.YesBankCCSchedulerImpl;
import com.nklmthr.finance.personal.service.MonthlyBalanceService;

import io.micrometer.common.util.StringUtils;

@Controller
@RequestMapping("/")
public class BalanceSheetUIController {
	Logger logger = Logger.getLogger(BalanceSheetUIController.class);
	@Autowired
	MonthlyBalanceService monthlyBalanceService;

	@Autowired
	private ApplicationContext applicationContext;

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

	@GetMapping("/triggerTransactionFetch")
	public String triggerTransactionFetch(Model m) throws GeneralSecurityException, IOException, ParseException, InvalidMessageException {
		Runnable myThread = () -> {
			// Used to set custom name to the current thread
			Thread.currentThread().setName("myThread");
			System.out.println(Thread.currentThread().getName() + " is running");
			try {
				ScheduledTask task = null;
				task = applicationContext.getBean(YesBankCCSchedulerImpl.class);
				task.doScheduledTask();
				task = applicationContext.getBean(SBICCSchedulerImpl.class);
				task.doScheduledTask();
				task = applicationContext.getBean(AxisBankCCSchedulerImpl.class);
				task.doScheduledTask();
				task = applicationContext.getBean(AxisSBUPIScheduleImpl.class);
				task.doScheduledTask();
				task = applicationContext.getBean(AxisSBATMScheduleImpl.class);
				task.doScheduledTask();
				task = (ScheduledTask) applicationContext.getBean(ICICIAmazonCCSchedulerImpl.class);
				task.doScheduledTask();
				task = (ScheduledTask) applicationContext.getBean(IDFCCCScheduleImpl.class);
				task.doScheduledTask();
				task = (ScheduledTask) applicationContext.getBean(AxisSBTransactionScheduleImpl.class);
				task.doScheduledTask();
				
			} catch (GeneralSecurityException | IOException | ParseException | InvalidMessageException e) {
				e.printStackTrace();
			}
		};
		Thread run = new Thread(myThread);
		run.start();
		MonthlyBalanceSummary monthlyBalanceSummary = monthlyBalanceService.getLastYearBalanceSheet();
		m.addAttribute("monthlyBalanceSummary", monthlyBalanceSummary);
		return "balanceSheet/BalanceSheet";

	}
}
