package com.crm.utils;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理类
 */
public class CustomExceptionHandler implements HandlerExceptionResolver {
    Logger log = Logger.getLogger(CustomExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse response, Object handlerMethod, Exception exception) {
//        exception.printStackTrace();
        log.error(exception.getMessage(),exception);
//        if (exception instanceof UserException) {
//            return new ModelAndView("user/userException");
//
//        }
        return new ModelAndView("exception/500");
    }
}
