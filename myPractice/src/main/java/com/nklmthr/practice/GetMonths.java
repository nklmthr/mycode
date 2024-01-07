package com.nklmthr.practice;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GetMonths {

	private enum MONTHS {
		JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUN, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
	};

	public static void main(String[] args) {
		System.out.println(getMonths());

	}

	public static Map<Integer, String> getMonths() {
		Map<Integer, String> months = new LinkedHashMap<Integer, String>();
		YearMonth current = YearMonth.now();
		for (int i = -3; i < 3; i++) {
			months.put(i, current.plusMonths(i).getMonth().name());
		}
		return months;
	}
}
