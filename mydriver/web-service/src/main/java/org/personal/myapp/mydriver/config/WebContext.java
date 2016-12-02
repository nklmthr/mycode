/**
 * 
 */
package org.personal.myapp.mydriver.config;

import org.apache.log4j.Logger;

public class WebContext {

	private static final Logger logger = Logger.getLogger(WebContext.class);
	private static ThreadLocal<WebContext> webContextThreadLocal = new ThreadLocal<WebContext>();

	private String intuit_tid;
	
	
	public static WebContext getInstance() {
		WebContext webContext = webContextThreadLocal.get();
		if (webContext == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Creating new context.");
			}
			webContext = new WebContext();
			webContextThreadLocal.set(webContext);
		}else{
			logger.debug("Webcontext not null "+webContext.toString());
		}
		return webContext;
	}

	public static void removeInstance() {
		webContextThreadLocal.remove();
	}

	public String getIntuit_tid() {
		return intuit_tid;
	}

	public void setIntuit_tid(String intuit_tid) {
		this.intuit_tid = intuit_tid;
	}
	
}
