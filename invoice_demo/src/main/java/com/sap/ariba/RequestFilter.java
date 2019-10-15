package com.sap.ariba;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.ThreadContext;
import org.jboss.logging.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@WebFilter(urlPatterns="/")
@Component
@Order(1)
public class RequestFilter implements Filter {

	Logger logger = Logger.getLogger(RequestFilter.class);
	private static final String TRANSACTION_ID = "transaction_id";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("init(FilterConfig filterConfig)");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String id = httpServletRequest.getHeader(TRANSACTION_ID);
 
        if (StringUtils.isBlank(id)) {
            id = UUID.randomUUID().toString();
        }
        ThreadContext.put("transactionId", id);
        ThreadContext.put("IPAdd", request.getRemoteHost());
        chain.doFilter(request, response);
        ThreadContext.clearStack();
	}

	@Override
	public void destroy() {
		logger.info("void destroy() ");
	}

}
