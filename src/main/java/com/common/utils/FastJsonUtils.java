package com.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.*;

/**
 *  JsonUtil转化处理
 */
public final class FastJsonUtils {
    private FastJsonUtils() {

    }

    /**
     * 将对象转换为json字符串.
     *
     * @param obj 被转换的对象
     * @param <T> 类型参数
     * @return json字符串
     */
    public static <T> String convertObject2JSONString(T obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullListAsEmpty);//.replaceAll("null","\"\"");
    }

    /**
     * 将对象转换为json字符串.
     *
     * @param obj 被转换的对象
     * @param <T> 类型参数
     * @return json字符串
     */
    public static <T> String convertObject2JSONString2(T obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullListAsEmpty);
    }

    /**
     * 通过指定序列化参数，将对象转换为json字符串
     * @param obj 被转换的对象
     * @param features 序列化参数
     * @param <T> 类型参数
     * @return json字符串
     */
    public static <T> String convert2FormatJSONString(T obj, SerializerFeature... features) {
        return JSON.toJSONString(obj, features);
    }

    /**
     * 将json串转换为类型为className的对象.
     * 处理如下json：{'field1':1,'field2':'a'}
     *
     * @param <T> 类型参数
     * @param jsonString json字符串
     * @param className 对象的Class类型对象
     * @return 对象
     */
    public static <T> T convertJSONString2Object(String jsonString, Class<T> className) {
        return JSON.parseObject(jsonString, className);
    }

    /**
     * 将json串转换为类型为className的对象.
     * 处理如下json：{'field1':1,'field2':'a'}
     *
     * @param <T> 类型参数
     * @param jsonString json字符串
     * @param type TypeReference<T>
     * @return 对象
     */
    public static <T> T convertJSONString2Object(String jsonString, TypeReference<T> type) {
        return JSON.parseObject(jsonString, type);
    }

    /**
     * 将json串转换为类型为className的对象集合.
     * 处理如下json：[{'field1':1,'field2':'a'},{'field1':2,'field2':'b'}]
     *
     * @param <T> 类型参数
     * @param jsonString json字符串
     * @param className Class类型对象
     * @return 转换成的list
     */
    public static <T> List<T> convertJSONString2Collection(String jsonString, Class<T> className) {
        return JSON.parseArray(jsonString, className);
    }

    /**
     * JSONObject转Map<String,String>.
     *
     * @param jsonObject jsonObject
     * @return map
     */
    public static Map<String, String> convertJSONObject2Map(JSONObject jsonObject) {
        if (jsonObject != null) {
            Map<String, String> result = new HashMap<String, String>();
            Iterator<String> iterator = jsonObject.keySet().iterator();
            String key;
            String value;
            while (iterator.hasNext()) {
                key = iterator.next();
                value = jsonObject.getString(key);
                result.put(key, value);
            }
            return result;
        }
        return null;
    }

    /**
     * jsonArray转List<Map<String, String>>.
     *
     * @param jsonArray json数组
     * @return 转换后的list
     */
    public static List<Map<String, String>> convertJSONArray2ListMap(JSONArray jsonArray) {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        for (int i = 0, len = jsonArray.size(); i < len; i++) {
            result.add(convertJSONObject2Map(jsonArray.getJSONObject(i)));
        }
        return result;
    }

    /**
     * Map<String, String>转字符串.
     *
     * @param map Map<String, String>对象
     * @return json字符串
     */
    public static String convertMapToJSONString(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (String key : map.keySet()) {
            stringBuilder.append("\"");
            stringBuilder.append(key);
            stringBuilder.append("\"");
            stringBuilder.append(":");
            stringBuilder.append("\"");
            stringBuilder.append(map.get(key));
            stringBuilder.append("\"");
            stringBuilder.append(",");
        }
        String result = stringBuilder.toString();
        if (result.endsWith(",")) {
            result = result.substring(0, result.length() - 1);
        }
        return result + "}";
    }

    /**
     * Map<String, String>转字符串.
     *
     * @param map Map<String, Object>对象
     * @return json字符串
     */
    public static String convertObjectMapToJSONString(Map<String, Object> map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (String key : map.keySet()) {
            stringBuilder.append("\"");
            stringBuilder.append(key);
            stringBuilder.append("\"");
            stringBuilder.append(":");
            stringBuilder.append("\"");
            stringBuilder.append(map.get(key));
            stringBuilder.append("\"");
            stringBuilder.append(",");
        }
        String result = stringBuilder.toString();
        if (result.endsWith(",")) {
            result = result.substring(0, result.length() - 1);
        }
        return result + "}";
    }
}
