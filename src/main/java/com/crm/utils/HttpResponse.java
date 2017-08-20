/*
 * 鏂� 浠� 鍚�: HttpResponse.java
 * 鐗� 鏉�: Huawei Technologies Co., Ltd. Copyright YYYY-YYYY, All rights reserved
 * 鎻� 杩�: <鎻忚堪>
 * 淇� 鏀� 浜�: w00171845
 * 淇敼鏃堕棿: 2012-8-7
 * 璺熻釜鍗曞彿: <璺熻釜鍗曞彿>
 * 淇敼鍗曞彿: <淇敼鍗曞彿>
 * 淇敼鍐呭: <淇敼鍐呭>
 */
package com.crm.utils;

/**
 * Http 鍝嶅簲瀵硅薄 Response
 * 
 * @author w00171845
 * @version [鐗堟湰鍙�, 2012-8-7]
 * @see [鐩稿叧绫�/鏂规硶]
 * @since [浜у搧/妯″潡鐗堟湰]
 */
public class HttpResponse
{
    public final static int OK = 200;
    
    protected int defaultPort;
    
    protected String file;
    
    protected String host;
    
    protected String path;
    
    protected int port;
    
    protected String protocol;
    
    protected String query;
    
    protected String ref;
    
    protected String userInfo;
    
    protected String contentEncoding;
    
    protected String content;
    
    protected String contentType;
    
    protected int code;
    
    protected String message;
    
    protected String method;
    
    protected int connectTimeout;
    
    protected int readTimeout;
    
    public String getContent()
    {
        return content;
    }
    
    public String getContentType()
    {
        return contentType;
    }
    
    public int getCode()
    {
        return code;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public String getContentEncoding()
    {
        return contentEncoding;
    }
    
    public String getMethod()
    {
        return method;
    }
    
    public int getConnectTimeout()
    {
        return connectTimeout;
    }
    
    public int getReadTimeout()
    {
        return readTimeout;
    }
    
    public int getDefaultPort()
    {
        return defaultPort;
    }
    
    public String getFile()
    {
        return file;
    }
    
    public String getHost()
    {
        return host;
    }
    
    public String getPath()
    {
        return path;
    }
    
    public int getPort()
    {
        return port;
    }
    
    public String getProtocol()
    {
        return protocol;
    }
    
    public String getQuery()
    {
        return query;
    }
    
    public String getRef()
    {
        return ref;
    }
    
    public String getUserInfo()
    {
        return userInfo;
    }
}
