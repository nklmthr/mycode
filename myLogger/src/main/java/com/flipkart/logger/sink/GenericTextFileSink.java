package com.flipkart.logger.sink;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import com.flipkart.logger.level.Level;

public class GenericTextFileSink extends Sink {

	private String sinkFileLocation;

	void setFileLocation(String fileLocation) {
		sinkFileLocation = fileLocation;
	}

	public void log(String namespace, Level level, String content) throws Exception {
		PrintWriter out = null;
		try {
			FileWriter fw = new FileWriter(sinkFileLocation, true);
			BufferedWriter bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);
			out.println(simpleDateFormat.format(new Date()) + " " + level.name() + " " + namespace + ": " + content);

		} catch (IOException e) {
			throw new Exception("Error in writing logs to file");
		} finally {
			out.close();
		}

	}

}
