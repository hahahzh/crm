package com.crm.utils;

/**
 * 字段校验通用工具类
 * User: karl
 * Date: 2016-08-24
 * 上午11:24
 */
public class ValidateUtils {

    /**
     * 是否是手机号
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile){
        return mobile != null && mobile.matches("^[1][34578][0-9]{9}$");
    }

    /**
     * 是否是邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        return email != null && email.matches("^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
    }
}
