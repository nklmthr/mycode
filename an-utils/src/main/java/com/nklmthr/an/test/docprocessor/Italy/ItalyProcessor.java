package com.nklmthr.an.test.docprocessor.Italy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;

public class ItalyProcessor {
	private static final String CHARSET = "UTF-8";

	private static final String CRLF = "\r\n";

	public static void main(String[] args) throws ClientProtocolException, IOException {
		ItalyProcessor p = new ItalyProcessor();
		p.post();
	}

	private void post() throws IOException {
		// Connect to the web server endpoint
		URL serverUrl = new URL("https://svcdev26.ariba.com/scripts/WebObjects.dll/ANDocumentProcessor.woa");
		HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();

		String boundaryString = "multipart-boundary";
		String fileUrl = "/Users/i344377/Desktop/Upload.xml";
		File logFileToUpload = new File(fileUrl);

		// Indicate that we want to write to the HTTP request body
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");
		urlConnection.addRequestProperty("", "multipart/related; boundary=" + boundaryString);
		urlConnection.addRequestProperty("DocumentName", "FPa");
		

		OutputStream outputStreamToRequestBody = urlConnection.getOutputStream();
		
		StringBuilder httpRequestBodyWriter = new StringBuilder();
		BufferedWriter httpRequestWriter=		new BufferedWriter(new OutputStreamWriter(outputStreamToRequestBody));

//		// Include value from the myFileDescription text area in the post data
//		httpRequestBodyWriter.append("\n\n--" + boundaryString + "\n");
//		httpRequestBodyWriter.append("Content-Disposition: form-data; name=\"myFileDescription\"");
//		httpRequestBodyWriter.append("\n\n");
//		httpRequestBodyWriter.append("Log file for 20150208");

		// Include the section to describe the file
		httpRequestBodyWriter.append("\n--" + boundaryString + "\n");
		httpRequestBodyWriter.append(//"Content-Disposition: form-data;" + "name="
				//+ logFileToUpload.getName() + "\"" + 
				"Content-Type: text/xml\nContent-Transfer-Encoding: base64\nX-Sdi-Id:43534543\n\n");
		
		
		// Write the actual file contents
		FileInputStream inputStreamToLogFile = new FileInputStream(logFileToUpload);

		byte[] bys = IOUtils.toByteArray(inputStreamToLogFile);
		System.out.println("***"+new String(bys)+"***");
		byte[] str  = Base64.getEncoder().encode(bys);
		String s = new String(str);
		httpRequestBodyWriter.append(s);
		

		// Mark the end of the multipart http request
		httpRequestBodyWriter.append("\n--" + boundaryString + "--\n");
		//httpRequestBodyWriter.append(new String(IOUtils.toByteArray(inputStreamToLogFile)));
		
		
		
		System.out.println(httpRequestBodyWriter);
		httpRequestWriter.write(httpRequestBodyWriter.toString());
		httpRequestWriter.flush();
		outputStreamToRequestBody.flush();
		// Close the streams
		outputStreamToRequestBody.close();
		
		httpRequestWriter.close();
		// Read response from web server, which will trigger the multipart HTTP request to be sent.
		BufferedReader httpResponseReader =
		    new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String lineRead;
		while((lineRead = httpResponseReader.readLine()) != null) {
		    System.out.println(lineRead);
		}
	}
}
