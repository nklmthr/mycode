/**
 * 
 */
package org.personal.myapp.mydriver.config;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.RequestContextFilter;


@Component
public class RequestResponseFilter extends RequestContextFilter{
	private static final Logger logger = Logger.getLogger(RequestResponseFilter.class);
	
	@Value("${cookie.authid}")
	String authidCookie;
	
	@Value("${cookie.ticket}")
	String ticketCookie;
	
	
	protected void doFilterInternal(HttpServletRequest httpRequest,HttpServletResponse httpResponse,FilterChain filterChain)
				throws ServletException,IOException{
		try{
			logger.info("########## Before Request Processing ##########");
			String intuit_tid = httpRequest.getHeader("intuit_tid");
			if (null != intuit_tid && !"".equals(intuit_tid.trim())) {
				WebContext.getInstance().setIntuit_tid(intuit_tid);
			} else {
				WebContext.getInstance().setIntuit_tid(UUID.randomUUID().toString());
			}
	        
			MDC.put("intuit_tid", WebContext.getInstance().getIntuit_tid());
			MDC.put("app_name", "fdptools-service");

			MDC.put("req_uri", httpRequest.getRequestURI());
			MDC.put("req_method", httpRequest.getMethod());
			MDC.put("req_ip_add", "");
			
			
			
			filterChain.doFilter(httpRequest, httpResponse);
		}finally{
			logger.info("########## After Request Processing ##########");
			logger.info("");
			logger.info("");
			
			MDC.remove("app_name");
			MDC.remove("intuit_tid");
			MDC.remove("req_uri");
			MDC.remove("req_method");
			MDC.remove("req_ip_add");
			MDC.remove("intuit_uid");
			WebContext.removeInstance();
			
			//httpResponse.setHeader("Access-Control-Allow-Origin", "*");
			httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
			httpResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
			/*Enumeration<String> reqHead = httpRequest.getHeaderNames();
			if (null != reqHead) {
				httpResponse.setHeader("Access-Control-Allow-Headers", reqHead.toString());
			}*/
		}
	}
}
