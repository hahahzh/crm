package com.crm.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Logger;

/**
 * 
* 绫诲悕绉帮細WebAppContextListener.java
* 绫绘弿杩帮細 
* 浣滆?呭崟浣嶏細 
* 鑱旂郴鏂瑰紡锛?
* @version 1.0
 */
public class WebAppContextListener implements ServletContextListener {
    protected Logger logger = Logger.getLogger(WebAppContextListener.class.getName());
    
    private static WebApplicationContext springContext;

	public void contextDestroyed(ServletContextEvent event) {
	}

	public void contextInitialized(ServletContextEvent event) {
		springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	}
	
	public static ApplicationContext getApplicationContext() {
        return springContext;
    }


}
