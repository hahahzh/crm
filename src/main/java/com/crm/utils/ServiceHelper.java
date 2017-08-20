package com.crm.utils;



/**
 * @author Administrator
 * 获取Spring容器中的service bean
 */
public final class ServiceHelper {
	
	public static Object getService(String serviceName){
		return WebAppContextListener.getApplicationContext().getBean(serviceName);
	}
	

	//public static RedisService getRedisService(){
		//return (RedisService) getService("redisService");
	//}
	
	//public static AuthService getAuthService(){
		//return (AuthService) getService("authService");
	//}




}
