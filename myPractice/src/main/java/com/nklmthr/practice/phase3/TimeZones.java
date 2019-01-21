package com.nklmthr.practice.phase3;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;

public class TimeZones {

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance(TimeZone.getDefault());
		printDate(c);
		c.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
		printDate(c);
		
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		//String timezones = Arrays.asList(TimeZone.getAvailableIDs()).stream().reduce("", (s1,s2)->s1+","+s2);
		//System.out.println(timezones);
		//LocalDateTime ldt = LocalDateTime.ofInstant(c.toInstant(), ZoneId.of("PST"));
		ZonedDateTime zonedDateTime = ZonedDateTime.of(ldt, ZoneId.systemDefault());
		System.out.println(zonedDateTime);
		ZonedDateTime utcDate = zonedDateTime.withZoneSameInstant(TimeZone.getTimeZone("Africa/Johannesburg").toZoneId());
		System.out.println(utcDate.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
	}

	private static void printDate(Calendar c) {
		System.out.println(c.get(Calendar.YEAR) + "-" + String.format("%02d", c.get(Calendar.MONTH) + 1) + "-"
				+ String.format("%02d", c.get(Calendar.DATE)) + " " + String.format("%02d", c.get(Calendar.HOUR_OF_DAY))
				+ ":" + String.format("%02d", c.get(Calendar.MINUTE)));
	}
}
