package com.nklmthr.finance.personal.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) {
		List<Date> dates = new ArrayList<>();
		Date d1 = parseDate("2024-07-16");
		Date d2 = parseDate("2024-07-15");
		Date d3 = parseDate("2024-07-14");
		Date d4 = parseDate("2024-07-13");
		dates.addAll(Arrays.asList(new Date[] { d1, d2, d3, d4 }));
		Date cDate = parseDate("2024-07-13");
		System.out.println(checkDate(dates, cDate));
	}

	private static Date checkDate(List<Date> dates, Date cDate) {
		long minDiff = Long.MAX_VALUE;
		Date existingDate = null;
		for (Date curr : dates) {
			long diff = curr.getTime() - cDate.getTime();
			long diffDays = TimeUnit.HOURS.convert(Math.abs(diff), TimeUnit.MILLISECONDS);
			System.out.println("curr:" + curr + ", diffDays:" + diffDays + ", minDiff=" + minDiff );
			if (diffDays < minDiff) {
				minDiff = diffDays;
				existingDate = curr;
			}
			if (minDiff < 22) {
				return existingDate;
			}
		}
		return cDate;

	}

	public static Date parseDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}
