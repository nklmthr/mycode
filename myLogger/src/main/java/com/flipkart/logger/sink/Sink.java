package com.flipkart.logger.sink;

import java.text.SimpleDateFormat;
import java.util.Properties;

import com.flipkart.logger.level.Level;

public abstract class Sink {

	String tsFormat = null;
	String writeMode = null;

	Properties sinkConfiguration = new Properties();

	SimpleDateFormat simpleDateFormat = null;

	public void setTimeStampFormat(String str) {
		tsFormat = str;
		simpleDateFormat = new SimpleDateFormat(tsFormat);
	}

	public void setWriteMode(String writeMode) {
		this.writeMode = writeMode;
	}
	
	abstract void log(String namespace, Level level, String content) throws Exception;

	public void debug(String namespace, String content) throws Exception {
		log(namespace, Level.DEBUG, content);
	}

	public void info(String namespace, String content) throws Exception {
		log(namespace, Level.INFO, content);
	}

	void warn(String namespace, String content) throws Exception {
		log(namespace, Level.WARN, content);
	}

	void error(String namespace, String content) throws Exception {
		log(namespace, Level.ERROR, content);
	}

	void fatal(String namespace, String content) throws Exception {
		log(namespace, Level.FATAL, content);
	}
}
