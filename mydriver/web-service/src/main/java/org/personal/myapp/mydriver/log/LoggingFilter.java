package org.personal.myapp.mydriver.log;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class LoggingFilter implements Filter {

	//private final Log log = LogFactory.getLog(getClass());
	private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

	@Autowired
	private HttpRequestUtil httpRequestUtil;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
		} else {
			chain.doFilter(request, response);
		}
	}

	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		long processingTime = -1; // Default Value assigned as -1
		try {
			long start = System.currentTimeMillis();
			doFilterAddMDC(request, response, chain);
			processingTime = System.currentTimeMillis() - start;
		} finally {
			if (logger.isInfoEnabled()) {
				String url = httpRequestUtil.getUrl(request);
				logger.info(url + "  processingTime=" + processingTime + "  status=" + response.getStatus());
			}
			MDC.clear();
		}
	}

	private void doFilterAddMDC(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String transactionId = null;
		MutableHttpServletRequest mutableHttpServletRequest = null;
		transactionId = httpRequestUtil.getTransactionID(request);

		if (transactionId == null) {
			mutableHttpServletRequest = new MutableHttpServletRequest(request);
			transactionId = httpRequestUtil.generateTransactionID(mutableHttpServletRequest);
		}
		MDC.put("tid", transactionId);

		String appID = httpRequestUtil.getAppID(request);
		if (appID != null) {
			MDC.put("appid", appID);
		}

		if (mutableHttpServletRequest != null) {
			chain.doFilter(mutableHttpServletRequest, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {
	}
}