package com.crm.service.impl;


import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.crm.dao.*;
import com.crm.models.*;
import com.crm.service.ExtendInterface;
import com.crm.utils.*;

import java.util.Date;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeMap;

@Service
public class ExtendInterfaceImpl extends BaseServiceImpl implements ExtendInterface {

    private static final Logger logger = LoggerFactory.getLogger(ExtendInterfaceImpl.class);

    @Autowired
    private ExtendAppInfoDao extendAppInfoDao;
    @Autowired
    private ExtendUserBasicInfoDao extendUserBasicInfoDao;
    @Autowired
    private ExtendUserRecordDao extendUserRecordDao;
    @Autowired
    private ExtendRewardInfoDao extendRewardInfoDao;
    @Autowired
    private ExtendGetRewardDao extendGetRewardDao;

    @Override
    public JSONObject generateCode(String... params) {
        Long tUid = Long.parseLong(params[0]);
        Long tAppid = Long.parseLong(params[1]);
        Integer count = extendAppInfoDao.findCount(tAppid);
        if (count < 1) throw new RuntimeException("service.error.appid.null");
        ExtendUserBasicInfo eubi = extendUserBasicInfoDao.findOne(tUid, tAppid, params[2], params[3], params[4]);
        if (eubi == null) {
            eubi = new ExtendUserBasicInfo();
            eubi.setAlliance_id(params[2]);
            eubi.setApp_id(tAppid);
            eubi.setChannel_tag(params[3]);
            String code = "";
            Integer t = 1;
            int c = 0;
            while (t > 0 && c <= 100) {
                code = NumberUtils.codeRandom(6);
                t = extendUserBasicInfoDao.findByInvitecode(code);
                c++;
            }
            eubi.setInvite_code(code);
            eubi.setInvite_level(0);
            eubi.setServer(params[4]);
            eubi.setIp(params[5]);
            eubi.setStatus(0);
            eubi.setUid(tUid);
            extendUserBasicInfoDao.saveAndFlush(eubi);
        }

        return this.toJson(eubi);
    }


    @Override
    public JSONObject getInviteStatus(String... params) {
        JSONObject json = new JSONObject();
        Long tUid = Long.parseLong(params[0]);
        Long tAppid = Long.parseLong(params[1]);
        Integer count = extendAppInfoDao.findCount(tAppid);
        if (count < 1) throw new RuntimeException("service.error.appid.null");
        ExtendUserBasicInfo eubi = extendUserBasicInfoDao.findOne(tUid, tAppid, params[2], params[3], params[4]);
        ExtendRewardInfo extendRewardInfo = extendRewardInfoDao.getAppInfo(tAppid, params[2], params[3]);
        if (eubi != null && extendRewardInfo != null) {
            Date now = new Date();
            Integer dayCount = extendUserRecordDao.findCountByDay(eubi.getInvite_code(), DateUtils.getBeforeDateByDays(now, 0), now);
            Integer weekCount = extendUserRecordDao.findCountByDay(eubi.getInvite_code(), DateUtils.getMondayOfThisWeek(), now);
            json = this.toJson(eubi);
            String s = "{\"i_id\":2002,\"inviteCode\":\"" + eubi.getInvite_code() + "\"}";
            String body = Base64.encode(s.getBytes());
            json.put("url", body);
            json.put("week_count", weekCount);
            json.put("day_count", dayCount);
            json.put("week_max_count", extendRewardInfo.getWeek_max());
            json.put("day_max_count", extendRewardInfo.getDay_max());
        }
        return json;
    }


    @Override
    public JSONObject getUrlByInviteCode(String... params) {
        JSONObject json = new JSONObject();
        String inviteCode = params[0];
        ExtendUserBasicInfo eubi = extendUserBasicInfoDao.findByCode(inviteCode);
        Preconditions.checkArgument(CommonUtils.isNotEmpty(eubi)
                && CommonUtils.isNotEmpty(eubi.getApp_id()), "params.error.inviteCode");
        Long appId = eubi.getApp_id();
        ExtendAppInfo extendAppInfo = extendAppInfoDao.findByAppid(appId);
        Preconditions.checkArgument(CommonUtils.isNotEmpty(extendAppInfo)
                && new Date().getTime() < extendAppInfo.getExpired(), "service.error.appid.null");
        json = this.toJson(eubi);
        json.put("home_page_url", extendAppInfo.getHome_page_url());
        json.put("game_reward_url", extendAppInfo.getGame_reward_url());
        json.put("app_id", appId.toString());
        return json;
    }

    @Override
    public JSONObject checkLimit(String... params) {
        ExtendUserRecord eur = new ExtendUserRecord();
        eur.setStatus(0);
        JSONObject json = new JSONObject();
        String inviteCode = params[0];
        String ip = params[1];
        Long appId = Long.parseLong(params[2]);
        String allianceId = params[3];
        String channelTag = params[4];
        String server = params[5];
        Date now = new Date();

        ExtendRewardInfo eri = extendRewardInfoDao.getAppInfo(appId, allianceId, channelTag);
        Preconditions.checkArgument(CommonUtils.isNotEmpty(eri), "params.error.inviteCode");
        json = this.toJson(eri);
        json.put("is_limited", false);

        Integer dayIpCount = extendUserRecordDao.findCountByIp(inviteCode, DateUtils.getBeforeDateByDays(now, 0), DateUtils.getBeforeDateByDays(now, -1), ip);
        if (dayIpCount >= eri.getDay_ip_max()) {
            json.put("is_limited", true);
            return json;
        }

        Integer dayCount = extendUserRecordDao.findCountByDay(inviteCode, DateUtils.getBeforeDateByDays(now, 0), now);
        if (dayCount >= eri.getDay_max()) {
            json.put("is_limited", true);
            eur.setStatus(1);
        }
        Integer weekCount = extendUserRecordDao.findCountByDay(inviteCode, DateUtils.getMondayOfThisWeek(), now);
        if (weekCount >= eri.getWeek_max()) {
            json.put("is_limited", true);
            eur.setStatus(1);
        }

        eur.setInvite_code(inviteCode);
        eur.setUpdate_ts(new Date());
        eur.setIp(ip);
        extendUserRecordDao.saveAndFlush(eur);
        return json;
    }

    @Override
    public JSONObject sendReword(String... params) {
        String allianceId = params[0];
        String uid = params[1];
        String server = params[2];
        String itemIdString = params[3];
        String itemCountString = params[4];
        String itemName = params[5];
        String orderId = params[6];
        String gameRewardUrl = params[7];
        boolean b = reword(allianceId, uid, server, itemIdString, itemCountString, itemName,orderId,gameRewardUrl);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag", b);
        return jsonObject;
    }

    /**
     * 调游戏接口发放奖励
     *
     * @param allianceId
     * @param uid
     * @return
     */
    public boolean reword(String allianceId, String uid, String server, String itemIdString, String itemCountString, String itemName,String orderId,String gameRewardUrl) {
        try {
            System.out.println("channelId:" + allianceId + ";uid:" + uid);

            String accountId = getuid(allianceId, uid);//查询游戏账号
            if ("0".equals(accountId)) return false;//游戏账号为0 不存在

            System.out.println("accountId:" + accountId);

            TreeMap<String, String> itemsList = setReward(itemIdString, itemCountString);
            itemsList.put("accountname", "EMNET#" + allianceId + "_" + uid);
            itemsList.put("mailtype", "singlesenditem");//固定值
            itemsList.put("mailtitle", "安利奖励");//邮件标题
            itemsList.put("mailcontent", "Hi,次元膜法师，此次招募萌新任务你做得很好！小萌特此奖励" + itemName + "，继续努力吧！愿次元之力与您同在~");//邮件描述
            itemsList.put("accountid", accountId); //"151468");//用户的平台id，后面会开放直接查询接口
            itemsList.put("areaid", server);//大区ID
            itemsList.put("orderno", orderId);//运营方订单编号 先随便写一个
            itemsList.put("timestamp", String.valueOf(System.currentTimeMillis()));//时间戳
            //3、拼接加密源字符串
            SortedSet<String> keys = (SortedSet<String>) itemsList.keySet();
            StringBuilder postData = new StringBuilder();
            for (String key : keys) {
                postData.append(key + "=" + itemsList.get(key) + "&");
            }
            int length = postData.length();
            postData.deleteCharAt(length - 1);
            //4、设置签名
            String post = postData.toString();
            String sign = Utility.MD5(post + "sdLnNrs8jHe0BbRHbrolRzde9zHJquPM", "UTF-8");
            // 5、设置签名字段：
            post += "&sign=" + sign;
            // 6、创建Web http请求（post）方法
            System.out.println("post:" + post);
            String sendResult = Utility.sendPost(gameRewardUrl, post);
            System.out.println("游戏奖励返回:" + sendResult);
            // 7、处理返回结果：
            if (!StringUtils.isBlank(sendResult) && "0".equals(sendResult.trim().substring(0, 1))) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 调游戏接口查询游戏accountID
     *
     * @param ChannelID
     * @param AllianceUid
     * @return
     */
    private String getuid(String ChannelID, String AllianceUid) {
        String res = "";
        try {
            res = Utility.sendGet("http://120.92.63.244/getuid.html?channelID=" + ChannelID + "&allianceUid=" + AllianceUid);
        } catch (Exception e) {
            res = "0";
        }
        return res;
    }

    private TreeMap<String, String> setReward(String itemIdString, String itemCountString) {
        TreeMap<String, String> itemsList = new TreeMap<>();
        String[] itemIds = itemIdString.split(",");
        String[] itemCounts = itemCountString.split(",");
        for (int j = 0; j < 6; j++) {
            if (j < itemIds.length) {
                itemsList.put("itemid" + (j + 1), itemIds[j]);
                itemsList.put("itemcount" + (j + 1), itemCounts[j]);
            } else {
                itemsList.put("itemid" + (j + 1), "0");
                itemsList.put("itemcount" + (j + 1), "0");
            }
        }
        return itemsList;
    }

    @Override
    public JSONObject recordGetReword(String... params) {
        ExtendGetReward egr = new ExtendGetReward();
        try {
            Long eriId = Long.parseLong(params[0]);
            String inviteCode = params[1];
            Long uid = Long.parseLong(params[2]);
            egr.setEri_id(eriId);
            egr.setInvite_code(inviteCode);
            egr.setUid(uid);
            Date now = new Date();
            egr.setUpdate_ts(now);
            String orderId = Utility.MD5(eriId + inviteCode + uid+now.getTime(), "UTF-8");
            egr.setOrder_id(orderId);
            extendGetRewardDao.saveAndFlush(egr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.toJson(egr);
    }


}
