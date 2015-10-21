package com.twosides.social.restservices.util;

import org.apache.log4j.Logger;

public class WebContext {

	private static final Logger logger = Logger.getLogger(WebContext.class);
	private static ThreadLocal<WebContext> webContextThreadLocal = new ThreadLocal<WebContext>();

	public static WebContext getInstance() {
		WebContext webContext = webContextThreadLocal.get();
		if (webContext == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Creating new context.");
			}
			webContext = new WebContext();
			webContextThreadLocal.set(webContext);
		}
		return webContext;
	}

	public static void removeInstance() {
		webContextThreadLocal.remove();
	}

}
