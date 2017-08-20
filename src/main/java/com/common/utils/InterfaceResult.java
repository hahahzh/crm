package com.common.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 接口返回结果模型
 * @param <T> 结果的类型
 * User: HZH
 * Date: 2016/7/13
 * Time: 10:59
 */
public class InterfaceResult<T> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
    返回状态
     */
    private String status;

    /*
    提示信息
     */
    private String message;

    /*
    系统配置参数
     */
    private Map<String, Object> config = new HashMap<String, Object>();

    /*
    结果集
     */
    private T data;

    /**
    * constructor
    * @param message
    * @param data
    * @param status
    */
    public InterfaceResult(String message, T data, String status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    /**
     * constructor
     */
    public InterfaceResult() {

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InterfaceResultModel{");
        sb.append("message=").append(message);
        sb.append(", config=").append(config);
        sb.append(", data=").append(data);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Object> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }
}
