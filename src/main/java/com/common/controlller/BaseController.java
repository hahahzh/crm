package com.common.controlller;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.InterfaceResult;
import com.common.utils.FastJsonUtils;
import com.common.utils.LogUtils;


public abstract class BaseController {
    protected static HttpHeaders headers = new HttpHeaders();

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class.getName());
    static {
        headers.add("Content-Type", "application/json; charset=utf-8");
        headers.add("Access-Control-Allow-Origin", "*");
    }

    /**
     * 返回状态标志位、提示信息
     * @param status
     * @param message
     * @return
     */
    protected ResponseEntity<String> returnMsg(String status, String message) {
        InterfaceResult<Object> result = new InterfaceResult<Object>();
        result.setStatus(status);
        result.setMessage(message);
        result.setData(new HashMap<>());
        MDC.clear();
        return new ResponseEntity<String>(FastJsonUtils.convertObject2JSONString(result), headers, HttpStatus.OK);
    }

    /**
     * 返回状态标志位、提示信息、数据
     * @param status
     * @param message
     * @param t
     * @param <T>
     * @return
     */
    protected <T> ResponseEntity<String> returnData(String status, String message, T t) {
        InterfaceResult<Object> result = new InterfaceResult<Object>();
        result.setStatus(status);
        result.setMessage(message);
        if(null == t)
            result.setData(new HashMap<>());
        else
            result.setData(t);
        MDC.clear();
        return new ResponseEntity<String>(FastJsonUtils.convertObject2JSONString(result), headers, HttpStatus.OK);
    }

    /**
     * 返回字符串
     * @param message
     * @return
     */
    protected ResponseEntity<String> returnText(String message) {
        MDC.clear();
        return new ResponseEntity<String>(message, headers, HttpStatus.OK);
    }

    protected String getJsonBody(HttpServletRequest request) throws IOException {
        String jsonBody = null;
        jsonBody = IOUtils.toString(request.getInputStream());
        if(jsonBody.isEmpty())LogUtils.EmaException("Json格式错误");//throw new RuntimeException("Json格式错误");
        return jsonBody;
    }

    protected JSONObject getParams(String body){
        com.alibaba.fastjson.JSONObject json = JSON.parseObject(body);
        if(json == null || json.isEmpty())LogUtils.EmaException("Json为空");//throw new RuntimeException("Json为空");
        return json;
    }
    
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code){
       return this.getMessage(code,new Object[]{});
    }

    public String getMessage(String code,String defaultMessage){
       return this.getMessage(code, null,defaultMessage);
    }

    public String getMessage(String code,String defaultMessage,Locale locale){
       return this.getMessage(code, null,defaultMessage,locale);
    }

    public String getMessage(String code,Locale locale){
    	return this.getMessage(code,null,"",locale);
    }

    public String getMessage(String code,Object[] args){
       return this.getMessage(code, args,"");
    }

    public String getMessage(String code,Object[] args,Locale locale){
       return this.getMessage(code, args,"",locale);
    }

    public String getMessage(String code,Object[] args,String defaultMessage){
       Locale locale =LocaleContextHolder.getLocale();
       return this.getMessage(code, args, defaultMessage, locale);
    }

    public String getMessage(String code,Object[]args,String defaultMessage,Locale locale){

       return messageSource.getMessage(code, args, defaultMessage, locale);
    }

    /**
     * 得到页面传递的参数封装成map
     */
    protected Map<String, String> getParameters(HttpServletRequest request) {
        Map<String, String> p = new HashMap<String, String>();
        if (request == null) {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }
        Map<String, String[]> req = request.getParameterMap();
        if ((req != null) && (!req.isEmpty())) {
            Collection<String> keys = req.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext();) {
                String key = (String) i.next();
                Object value = req.get(key);
                Object v = null;
                if ((value.getClass().isArray())
                        && (((Object[]) value).length > 0)) {
                    v = ((Object[]) value)[0];
                } else {
                    v = value;
                }
                if ((v != null) && ((v instanceof String))) {
                    String s = ((String) v).trim();
                    if (s.length() > 0) {
                        p.put(key, s);
                    }
                }
            }
            // 读取cookie
            p.putAll(ReadCookieMap(request));
            return p;
        }
        return p;
    }

    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    private static Map<String, String> ReadCookieMap(HttpServletRequest request) {
        Map<String, String> cookieMap = new HashMap<String, String>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieMap;
    }


    protected String failEmaException(String e) {
        String[] msgArr = e.split(",");
        LOG.warn(msgArr[0]);
        return msgArr[0];
    }

    protected static void Exception() {
        String method = "";
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        StackTraceElement[] stenew;
        if(ste.length>12){
            stenew = Arrays.copyOfRange(ste, 2, 12);
        }else{
            stenew = ste;
        }
        for (StackTraceElement sm : stenew) {
            method += sm.getFileName()+":"+sm.getMethodName()+":line"+sm.getLineNumber()+",";
        }
        MDC.put("errorLocation", method);
    }

}
