package com.practice.sap.work;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

public class LogParser {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		List<String> list = new ArrayList<String>();
		list.stream().forEach(s -> System.out.println(s));
		// TODO Auto-generated method stub
//		String logFileLocation = "C:\\Users\\nklmt\\OneDrive\\Desktop\\savecsv-1.csv";
//		List<AccessLog> accessLogList = readCSV(logFileLocation);
//		parseData(accessLogList, "C:\\Users\\nklmt\\OneDrive\\Desktop\\empdata.txt");
	}

	private static List<AccessLog> readCSV(String logFileLocation)
			throws FileNotFoundException, IOException, ParseException {
		int i = 0;
		List<AccessLog> accessLogList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String str = "Reading Compensation statement data userId=";
		try (ICsvListReader listReader = new CsvListReader(new FileReader(logFileLocation),
				CsvPreference.STANDARD_PREFERENCE)) {
			List<String> fieldsInCurrentRow;
			while ((fieldsInCurrentRow = listReader.read()) != null) {
//				System.out.println(fieldsInCurrentRow.get(0));
//				System.out.println(fieldsInCurrentRow.get(1));
//				System.out.println(fieldsInCurrentRow.get(2));
				String field = fieldsInCurrentRow.get(1);
				String uid = fieldsInCurrentRow.get(0);
				String affectedUserId = field.substring(field.indexOf(str) + str.length(), field.length());
				OffsetDateTime odt = OffsetDateTime.parse(fieldsInCurrentRow.get(2));
				AccessLog log = new AccessLog(uid, affectedUserId, odt);
				accessLogList.add(log);
//				System.out.println(field147+" -> "+field146);
//				System.out.println(++i + "parsing log..."+log);
//				System.out.println("*****\n\n");
			}
		}
		// Collections.sort(accessLogList);
		return accessLogList;

	}

	private static void parseData(List<AccessLog> accessLogList, String dataFileLoc)
			throws FileNotFoundException, IOException {
		Map<String, String> dbUIDtoUserIdMap = new HashMap<>();
		String str = "Reading Compensation statement data userId=";
		try (BufferedReader br = new BufferedReader(new FileReader(dataFileLoc))) {
			String line;
			while ((line = br.readLine()) != null) {
				String userInternalId = line.split(",")[2];
				String userName = line.split(",")[1];
				String userId = line.split(",")[0];
				dbUIDtoUserIdMap.put(userInternalId, userId);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (AccessLog log : accessLogList) {

			if (dbUIDtoUserIdMap.get(log.uid) == null) {
				sb.append(log.uid + ",");
			} else {
				System.out.println(
						log.uid+ "," + log.date  + "," + dbUIDtoUserIdMap.get(log.uid) + "," + log.accessUserId);
			}

//			String internalId = dbUIDtoUserIdMap.get(log.uid);
//			if (StringUtils.isNotBlank(internalId) && !internalId.equalsIgnoreCase(log.accessUserId)) {
//				System.out.println(log.date+","+log.uid+","+dbUIDtoUserIdMap.get(log.uid) + "," + log.accessUserId);
//			}			
		}
		System.out.println("****" + sb);
	}

}

class AccessLog implements Comparable<AccessLog> {
	public AccessLog(String uid, String accessUserId, OffsetDateTime dt) {
		this.uid = uid;
		this.accessUserId = accessUserId;
		this.date = dt;
	}

	String uid;
	String accessUserId;
	OffsetDateTime date;

	@Override
	public String toString() {
		return "AccessLog [uid=" + uid + ", accessUserId=" + accessUserId + ", date=" + date + "]";
	}

	@Override
	public int compareTo(AccessLog o) {
		if (Integer.parseInt(this.uid) > Integer.parseInt(o.uid)) {
			return 1;
		} else if (Integer.parseInt(this.uid) < Integer.parseInt(o.uid)) {
			return -1;
		}
		return 0;
	}

}