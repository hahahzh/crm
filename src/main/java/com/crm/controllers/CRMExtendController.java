package com.crm.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.codec.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.common.config.LocaleMessageSourceConfig;
import com.common.controlller.BaseController;
import com.crm.service.ExtendInterface;
import com.crm.utils.CommonUtils;
import com.crm.utils.ExtendRedis;
import com.crm.utils.Utility;

import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/extend")
public class CRMExtendController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CRMExtendController.class);

    @Autowired
    private LocaleMessageSourceConfig localeMessage;

    @Autowired
    private ExtendInterface extendInterface;

    @RequestMapping(value = "/ema_extend_p", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public ResponseEntity<String> emaPostController(HttpServletResponse response, HttpServletRequest request) {

        JSONObject result = null;
        String message = null;
        String status = "0";
        try {
            result = doDirect(request);
        } catch (Exception e) {
            message = localeMessage.getMessage(e.getMessage());
            status = "1";
            return returnData(status, message, result);
        }
        return returnData(status, message, result);
    }

    @RequestMapping(value = "/ema_extend_g", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity<String> emaGetController(HttpServletRequest request, @RequestParam(value = "body", required = true) String body) {
        JSONObject result = null;
        String message = null;
        String status = "0";
        try {
            String ip = Utility.getRemoteHost(request);
            result = doDirect(body, ip);
        } catch (Exception e) {
            message = localeMessage.getMessage(e.getMessage());
            status = "1";
            return returnData(status, message, result);
        }
        return returnData(status, message, result);
    }


    @RequestMapping(value = "/verif/ema_extend_g", method = RequestMethod.GET)
    @CrossOrigin
     public ResponseEntity<String> emaGetVerifController(HttpServletRequest request, @RequestParam(value = "body", required = true) String body) {
        JSONObject result = null;
        String message = null;
        String status = "0";
        try {
            String ip = Utility.getRemoteHost(request);
            result = doVerifDirect(body, ip);
        } catch (Exception e) {
            message = localeMessage.getMessage(e.getMessage());
            status = "1";
            return returnData(status, message, result);
        }
        return returnData(status, message, result);
    }

    @RequestMapping(value = "/verif/ema_extend_p", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<String> emaPostVerifController(HttpServletRequest request, @RequestParam(value = "body", required = true) String body) {
        JSONObject result = null;
        String message = null;
        String status = "0";
        try {
            String ip = Utility.getRemoteHost(request);
            result = doVerifDirect(body, ip);
        } catch (Exception e) {
            message = localeMessage.getMessage(e.getMessage());
            status = "1";
            return returnData(status, message, result);
        }
        return returnData(status, message, result);
    }

    @RequestMapping(value = "/ema_extend_v", method = RequestMethod.GET)
    @CrossOrigin
    public ModelAndView emaViewController(HttpServletRequest request, @RequestParam(value = "body", required = true) String body) {
        JSONObject result = null;
        String message = null;
        String status = "0";
        ModelAndView mv = new ModelAndView();
        try {
            String ip = Utility.getRemoteHost(request);
            result = doDirect(body, ip);
            mv.setViewName("redirect:" + result.get("home_page_url"));
        } catch (Exception e) {
            message = localeMessage.getMessage(e.getMessage());
            status = "1";
            return mv;
        }
        return mv;
    }

    private JSONObject doVerifDirect(HttpServletRequest request) throws IOException {
        String ip = Utility.getRemoteHost(request);
        String body = getJsonBody(request);
        return doVerifDirect(body, ip);
    }


    private JSONObject doVerifDirect(String body, String ip) throws IOException {

        body = new String(Base64.decode(body));
        com.alibaba.fastjson.JSONObject params = getParams(body);
        System.out.println("___________________param:"+params);
        Integer i_id = params.getInteger("i_id");
        if (i_id == null) throw new RuntimeException("params.error.iid");
        JSONObject results = null;
        switch (i_id) {
            case 2000:
                String uidStatus = params.getString("uid");
                String appidStatus = params.getString("appid");
                String allianceidStatus = params.getString("allianceid");
                String channeltagStatus = params.getString("channeltag");
                String serverStatus = params.getString("server");
                results = extendInterface.getInviteStatus(uidStatus, appidStatus, allianceidStatus, channeltagStatus, serverStatus);
                break;
            case 2001:
                String uid = params.getString("uid");
                String appid = params.getString("appid");
                String allianceid = params.getString("allianceid");
                String channeltag = params.getString("channeltag");
                String server = params.getString("server");
                if(CommonUtils.isEmpty(server))break;
                results = extendInterface.generateCode(uid, appid, allianceid, channeltag, server, ip);
                break;
            default:

                break;
        }
        return results;
    }

    private JSONObject doDirect(HttpServletRequest request) throws IOException {
        String ip = Utility.getRemoteHost(request);
        String body = getJsonBody(request);
        return doVerifDirect(body, ip);
    }

    private JSONObject doDirect(String body, String ip) throws IOException {

        body = new String(Base64.decode(body));
        com.alibaba.fastjson.JSONObject params = getParams(body);
        Integer i_id = params.getInteger("i_id");
        if (i_id == null) throw new RuntimeException("params.error.iid");
        JSONObject results = null;
        switch (i_id) {
            case 2002:
                String inviteCode = params.getString("inviteCode");
                results = extendInterface.getUrlByInviteCode(inviteCode);
                String appId = results.get("app_id") + "";
                String allianceId = results.get("alliance_id") + "";
                String channelTag = results.get("channel_tag") + "";
                String server = results.get("server") + "";
                if (ip.equals(results.get("ip"))) {
                    break;
                }
                JSONObject limit = extendInterface.checkLimit(inviteCode, ip, appId, allianceId, channelTag, server);
                if ((boolean) limit.get("is_limited")) {
                    break;
                }
                try{
                    String uid = results.get("uid") + "";
                    String gameRewardUrl = results.get("game_reward_url") + "";
                    String eriId = limit.get("id") + "";
                    String itemIdString = limit.get("invite_item_id") + "";
                    String itemCountString = limit.get("invite_item_count") + "";
                    String itemName = limit.get("invite_item_name") + "";
                    JSONObject getReword = extendInterface.recordGetReword(eriId, inviteCode, uid);
                    String orderId = getReword.get("order_id") + "";
                    JSONObject sendResult = extendInterface.sendReword(allianceId,uid,server,itemIdString,itemCountString,itemName,orderId,gameRewardUrl);
                    logger.info("appId:" + appId +
                            ";allianceId:" + allianceId +
                            ";channelTag:"+channelTag+
                            ";server:"+server+
                            ";result:"+sendResult.get("flag"));
                }catch (Exception e){
                    logger.error("appId:" + appId +
                            ";allianceId:" + allianceId +
                            ";channelTag:" + channelTag +
                            ";server:" + server +
                            ";result:" + false);
                }
                break;
            default:

                break;
        }
        return results;
    }

}
