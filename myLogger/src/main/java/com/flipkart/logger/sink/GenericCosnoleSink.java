package com.flipkart.logger.sink;

import java.util.Date;

import com.flipkart.logger.level.Level;

public class GenericCosnoleSink extends Sink {
	public void log(String namespace, Level level, String content) {
		System.out.println(simpleDateFormat.format(new Date()) + " " + level.name() + " " + namespace + ": " + content);
	}

}
