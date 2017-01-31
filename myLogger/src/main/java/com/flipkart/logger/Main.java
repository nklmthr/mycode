package com.flipkart.logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.flipkart.logger.level.Level;
import com.flipkart.logger.sink.LoggerSink;

public class Main {
	public static void main(String[] args) {
		List<Properties> configurations = new ArrayList<Properties>();
		LoggerSink sink = null;
		try {
			Properties fileLog = new Properties();
			fileLog.setProperty("ts_format", "yyyy-MM-dd");
			fileLog.setProperty("log_level", "INFO");
			fileLog.setProperty("sink_type", "FILE");
			fileLog.setProperty("writeMode", "SYNC");
			fileLog.setProperty("file_location", "/Users/nmathur/Desktop/Logger.txt");
			configurations.add(fileLog);
			
			sink = new LoggerSink(configurations);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sink.logToSinks("flipkar-logger", Level.INFO, "Log this message");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
