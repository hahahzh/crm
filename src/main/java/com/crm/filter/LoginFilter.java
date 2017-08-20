package com.crm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import com.common.config.LocaleMessageSourceConfig;
import com.common.model.TokenModel;
import com.crm.utils.ExtendRedis;

public class LoginFilter implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(LoginFilter.class.getName());
	
	protected LocaleMessageSourceConfig localeMessageSourceConfig;
	private ExtendRedis extendRedis;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
    	ServletContext  servletContext = config.getServletContext();
    	WebApplicationContext context = (WebApplicationContext)servletContext .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
    	localeMessageSourceConfig = (LocaleMessageSourceConfig)context.getBean("localeMessage");
    	extendRedis = (ExtendRedis)context.getBean("extendRedis");
	}
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;  
		
		String token = httpRequest.getHeader("Authorization");
		LOG.info("LoginFilter Authorization="+token);
		response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/json; charset=utf-8");
        if(token == null){
        	localeMessageSourceConfig.getMessage("verif.error.token.null");
        }
		
        TokenModel tm = extendRedis.checkToken(token);
        
    	if(tm == null){
    		throw new RuntimeException(localeMessageSourceConfig.getMessage("verif.error.token"));
    	}
        
        LOG.info("Login filter from redis: token"+tm.toString());
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	


}
