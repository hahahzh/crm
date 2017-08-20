package com.crm.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.crm.models.ExtendGetReward;
import com.crm.models.ExtendRewardInfo;
import com.crm.models.ExtendUserBasicInfo;
import com.crm.utils.CommonUtils;

public abstract class BaseServiceImpl {

    protected JSONObject toJson(Object o) {
        JSONObject json = null;
        if (o instanceof ExtendUserBasicInfo) {
            json = new JSONObject();
            ExtendUserBasicInfo eubi = (ExtendUserBasicInfo) o;
            if (CommonUtils.isNotEmpty(eubi.getInvite_code())) json.put("invite_code", eubi.getInvite_code());
            if (CommonUtils.isNotEmpty(eubi.getAlliance_id())) json.put("alliance_id", eubi.getAlliance_id());
            if (CommonUtils.isNotEmpty(eubi.getApp_id())) json.put("app_id", eubi.getApp_id());
            if (CommonUtils.isNotEmpty(eubi.getChannel_tag())) json.put("channel_tag", eubi.getChannel_tag());
            if (CommonUtils.isNotEmpty(eubi.getId())) json.put("id", eubi.getId());
            if (CommonUtils.isNotEmpty(eubi.getUid())) json.put("uid", eubi.getUid());
            if (CommonUtils.isNotEmpty(eubi.getServer())) json.put("server", eubi.getServer());
            if (CommonUtils.isNotEmpty(eubi.getInvite_level())) json.put("invite_level", eubi.getInvite_level());
            if (CommonUtils.isNotEmpty(eubi.getStatus())) json.put("status", eubi.getStatus());
            if (CommonUtils.isNotEmpty(eubi.getIp())) json.put("ip", eubi.getIp());
            if (CommonUtils.isNotEmpty(eubi.getCreate_ts())) json.put("create_ts", eubi.getCreate_ts());
            if (CommonUtils.isNotEmpty(eubi.getUpdate_ts())) json.put("update_ts", eubi.getUpdate_ts());
        }else if(o instanceof ExtendRewardInfo){
            json = new JSONObject();
            ExtendRewardInfo eri = (ExtendRewardInfo)o;
            if (CommonUtils.isNotEmpty(eri.getId())) json.put("id", eri.getId());
            if (CommonUtils.isNotEmpty(eri.getApp_id())) json.put("app_id", eri.getApp_id());
            if (CommonUtils.isNotEmpty(eri.getAlliance_id())) json.put("alliance_id", eri.getAlliance_id());
            
            if (CommonUtils.isNotEmpty(eri.getChannel_tag())) json.put("channel_tag", eri.getChannel_tag());
            if (CommonUtils.isNotEmpty(eri.getInvite_level())) json.put("invite_level", eri.getInvite_level());
            if (CommonUtils.isNotEmpty(eri.getDay_ip_max())) json.put("day_ip_max", eri.getDay_ip_max());
            if (CommonUtils.isNotEmpty(eri.getDay_max())) json.put("day_max", eri.getDay_max());
            if (CommonUtils.isNotEmpty(eri.getWeek_max())) json.put("week_max", eri.getWeek_max());
            if (CommonUtils.isNotEmpty(eri.getInvite_item_count())) json.put("invite_item_count", eri.getInvite_item_count());
            if (CommonUtils.isNotEmpty(eri.getInvite_item_id())) json.put("invite_item_id", eri.getInvite_item_id());
            if (CommonUtils.isNotEmpty(eri.getInvite_item_name())) json.put("invite_item_name", eri.getInvite_item_name());
            if (CommonUtils.isNotEmpty(eri.getGet_count())) json.put("get_count", eri.getGet_count());
            if (CommonUtils.isNotEmpty(eri.getCreate_ts())) json.put("create_ts", eri.getCreate_ts());
            if (CommonUtils.isNotEmpty(eri.getUpdate_ts())) json.put("update_ts", eri.getUpdate_ts());
        }else if(o instanceof ExtendGetReward){
            json = new JSONObject();
            ExtendGetReward rgr = (ExtendGetReward)o;
            if (CommonUtils.isNotEmpty(rgr.getId())) json.put("id", rgr.getId());
            if (CommonUtils.isNotEmpty(rgr.getUid())) json.put("uid", rgr.getUid());
            if (CommonUtils.isNotEmpty(rgr.getInvite_code())) json.put("invite_code", rgr.getInvite_code());
            if (CommonUtils.isNotEmpty(rgr.getEri_id())) json.put("eri_id", rgr.getEri_id());
            if (CommonUtils.isNotEmpty(rgr.getOrder_id())) json.put("order_id", rgr.getOrder_id());
            if (CommonUtils.isNotEmpty(rgr.getUpdate_ts())) json.put("update_ts", rgr.getUpdate_ts());
        }

        return json;
    }
}
