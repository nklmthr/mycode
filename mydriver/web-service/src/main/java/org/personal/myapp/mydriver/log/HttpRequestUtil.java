package org.personal.myapp.mydriver.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;


@Component
public class HttpRequestUtil {

	private final Log log = LogFactory.getLog(getClass());

	public String getUrl(HttpServletRequest httpServletRequest) {
		if (httpServletRequest == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("method=").append(httpServletRequest.getMethod()).append(" url=")
				.append(httpServletRequest.getRequestURI());
		String query = httpServletRequest.getQueryString();
		if (query != null) {
			sb.append('?').append(query);
		}
		return sb.toString();
	}

	public String payload(HttpServletRequest httpServletRequest) throws IOException {
		if (httpServletRequest == null) {
			return null;
		}
		BufferedReader bufferedReader = httpServletRequest.getReader();
		StringWriter sw = new StringWriter();
		char[] buffer = new char[1024 * 4];
		int n = 0;
		while (-1 != (n = bufferedReader.read(buffer))) {
			sw.write(buffer, 0, n);
		}
		return sw.toString();
	}

	public Map<String, String> getHeaders(HttpServletRequest httpServletRequest) {
		if (httpServletRequest == null) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = httpServletRequest.getHeader(key);
			map.put(key, value);
		}
		return map;
	}

	private static final String HEADER_NAME_TID = "tid";

	private static final String HEADER_APP_ID = "intuit_appid";

	public String getTransactionID(HttpServletRequest req) {
		if (req == null) {
			return null;
		}
		String transactionId = req.getHeader(HEADER_NAME_TID);
		return transactionId;
	}

	public String getAppID(HttpServletRequest req) {
		if (req == null) {
			return null;
		}
		String appId = req.getHeader(HEADER_APP_ID);
		return appId;
	}

	public synchronized String generateTransactionID(MutableHttpServletRequest req) {
		if (req == null) {
			return null;
		}
		String transactionId = UUID.randomUUID().toString();
		req.putHeader(HEADER_NAME_TID, transactionId);
		log.debug("Header tid doesn't exists, so setting one");
		return transactionId;
	}

}