package com.crm.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * User: karl
 * Date: 16-3-1
 * Time: 4:58
 */
public class HttpUtils {
    private static final String POST = "POST";
    private static final String GET = "GET";

    /**
     * Post
     * @param url
     * @return
     */
    public static String post(String url) {
        return request(url, POST);
    }

    /**
     * Post
     * @param url
     * @param params
     * @return
     */
    public static String post(String url,Map<String, Object> params) {
        return post(url + initParams(params));
    }

    /**
     * Post
     * @param url
     * @return
     */
    public static String post(String url, String jsonStr) {

        return request(url, POST);
    }

    /**
     * Post
     * @param url
     * @param jsonObj
     * @return
     */
    public static String post(String url,JSONObject jsonObj) {
        return post(url + initParams(jsonObj));
    }

    /**
     * Get
     * @param url
     * @return
     */
    public static String get(String url) {
        return request(url, GET);
    }

    /**
     * Get
     * @param url
     * @param params
     * @return
     */
    public static String get(String url,Map<String, Object> params) {
        return get(url + initParams(params));
    }

    /**
     *
     * @param params
     * @return
     */
    private static String initParams(Map<String, Object> params) {
        StringBuffer paramBuffer = new StringBuffer("?");
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            paramBuffer.append(key).append("=").append(value).append("&");
        }
        paramBuffer = paramBuffer.deleteCharAt(paramBuffer.length() -1);
        return paramBuffer.toString();
    }

    /**
     *
     * @param jsonObj
     * @return
     */
    private static String initParams(JSONObject jsonObj) {
        StringBuffer paramBuffer = new StringBuffer("?");
        try {
            for (String key : jsonObj.keySet()) {
                String value = jsonObj.getString(key);
                paramBuffer.append(key).append("=").append(URLEncoder.encode(value, "UTF-8")).append("&");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        paramBuffer = paramBuffer.deleteCharAt(paramBuffer.length() - 1);
        return paramBuffer.toString();
    }

    /**
     * @param url
     * @param method
     * @return
     */
    private static String request(String url, String method) {
        StringBuffer stringBuffer = new StringBuffer("");
        try {
            URL postUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod(method);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; text/html; charset=UTF-8");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

//////////////////////////////////////////////////////////////////////////////////

    @SuppressWarnings("unused")
    private static String sendPost(String url, Map<String, Object> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
            // ????POST???????????????????
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST????
            conn.setRequestMethod("POST");
            // ????????????????
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            // ???URLConnection?????????????
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // ???????????
            if (params != null) {
                StringBuilder param = new StringBuilder();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    if(param.length()>0){
                        param.append("&");
                    }
                    param.append(entry.getKey());
                    param.append("=");
                    param.append(entry.getValue());
                }
                System.out.println("param:"+param.toString());
                out.write(param.toString());
            }
            // flush??????????
            out.flush();
            // ????BufferedReader???????????URL?????
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
}
