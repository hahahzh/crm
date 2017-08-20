package com.crm.utils;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 响应消息工具类
 * User: karl
 * Date: 15-06-18:下午5:21
 */
public class RespUtils {
    static Logger logger = Logger.getLogger(RespUtils.class);

    /**
     * 返回json格式的字符串
     *
     * @param resp
     * @param msg
     * @param success
     */
    public static String jsonMsg(HttpServletResponse resp, String msg, boolean success) {
        try {
            resp.setContentType("text/json");
            JSONObject obj = new JSONObject();
            obj.put("success", success);
            obj.put("message", msg);
            PrintWriter writer = resp.getWriter();
            writer.write(obj.toString());
            writer.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }



    /**
     * 返回json格式的bootstrapvalidator校验字符串
     *
     * @param resp
     * @param success
     */
    public static String validMsg(HttpServletResponse resp, boolean success) {
        try {
            resp.setContentType("text/json");
            JSONObject obj = new JSONObject();
            obj.put("valid", success);
            PrintWriter writer = resp.getWriter();
            writer.write(obj.toString());
            writer.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 返回json格式的字符串 至 php手机端，success 为0或1
     *
     * @param resp
     * @param msg
     * @param success
     */
    public static String jsonMsgToPhp(HttpServletResponse resp, String msg, String success) {
        try {
            resp.setContentType("text/json");
            JSONObject obj = new JSONObject();
            obj.put("success", success);
            obj.put("message", msg);
            PrintWriter writer = resp.getWriter();
            writer.write(obj.toString());
            writer.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 返回text格式的字符串
     * @param resp
     * @param msg
     */
    public static String textMsg(HttpServletResponse resp, String msg) {
        try {
            resp.setContentType("text/json");
            PrintWriter writer = resp.getWriter();
            writer.write(msg);
            writer.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * json
     *
     * @param resp
     * @param msg
     * @param success
     */
    public static String jsonMsgForFileDownload(HttpServletResponse resp, String msg, boolean success) {
        try {
            resp.setContentType("text/json");
            resp.setHeader("Set-Cookie", "fileDownload=" + String.valueOf(success) + "; path=/");
            PrintWriter writer = resp.getWriter();
            writer.write(msg);
            writer.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
