package com.crm.service;

import com.alibaba.fastjson.JSONObject;

public interface ExtendInterface {

	public JSONObject generateCode(String... params);

	public JSONObject getInviteStatus(String... params);

	public JSONObject getUrlByInviteCode(String... params);

	public JSONObject checkLimit(String... params);

	public JSONObject sendReword(String... params);

	public JSONObject recordGetReword(String... params);

}
