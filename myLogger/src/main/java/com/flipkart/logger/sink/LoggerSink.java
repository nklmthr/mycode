package com.flipkart.logger.sink;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.flipkart.logger.level.Level;

public class LoggerSink {
	Map<Sink, List<Level>> sinksToLevelMap = new HashMap<Sink, List<Level>>();

	private LoggerSink() {

	}

	public LoggerSink(List<Properties> configurations) throws Exception {
		if (configurations == null || configurations.size() < 1)
			throw new Exception("Configuration not set for Sink");

		for (Properties configuration : configurations) {
			Level logLevel = null;

			GenericSinkEnum sinkType = null;
			if (configuration.containsKey("sink_type")) {
				String sinkTypeStr = configuration.getProperty("sink_type");
				sinkType = GenericSinkEnum.valueOf(sinkTypeStr);
			}

			if (configuration.containsKey("log_level")) {
				String logLevelStr;
				logLevelStr = configuration.getProperty("log_level");
				logLevel = Level.valueOf(logLevelStr);
			}
			if (sinksToLevelMap.containsKey(sinkType)) {
				List<Level> levels = sinksToLevelMap.get(sinkType);
				levels.add(logLevel);
			} else {
				List<Level> levels = new ArrayList<Level>();
				levels.add(logLevel);
				sinksToLevelMap.put(sinkType.classObject(), levels);
			}

			switch (sinkType) {
			case FILE:
				GenericTextFileSink fileSink = (GenericTextFileSink) sinkType.classObject();
				if (configuration.containsKey("file_location")) {
					String fileLocation = configuration.getProperty("file_location");
					fileSink.setFileLocation(fileLocation);
				}
				break;
			case DB:
				GenericDatabaseSink dbSink = (GenericDatabaseSink) sinkType.classObject();
				if (configuration.containsKey("db-host")) {
					String dbHost = configuration.getProperty("db-host");
					dbSink.setDBHost(dbHost);
				}
				if (configuration.containsKey("db-port")) {
					String dbPort = configuration.getProperty("db-port");
					dbSink.setDBPort(dbPort);
				}
				break;
			case CONSOLE:

				break;
			}

			if (configuration.containsKey("ts_format")) {
				String tsFormatStr = configuration.getProperty("ts_format", "mm/dd/yyyy");
				sinkType.classObject().setTimeStampFormat(tsFormatStr);
			}
			if (configuration.containsKey("write_mode")) {
				String writeModeStr = configuration.getProperty("write_mode", "async");
				sinkType.classObject().setWriteMode(writeModeStr);
			}

		}
	}

	public void logToSinks(String namespace, Level level, String content) throws Exception {
		for (Sink sink : sinksToLevelMap.keySet()) {
			List<Level> levels = sinksToLevelMap.get(sink);
			if (levels.contains(level)) {
				sink.log(namespace, level, content);
			}

		}
	}

}
