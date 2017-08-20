package com.common.security.filllter;
import java.io.IOException;  

import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;  

public abstract class HTTPBasicAuthorizeAttribute implements Filter{
	
    @Override  
    public void destroy() {  
          
    }  
  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {  
          
    }  
  
      
    public void init(FilterConfig arg0) throws ServletException {  
          
    }

	public void checkLogin(HttpServletRequest httpRequest) {
	}  
      
    
}
