package com.crm.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.common.config.LocaleMessageSourceConfig;
import com.common.controlller.BaseController;
import com.common.model.TokenModel;
import com.common.utils.InterfaceConstant;
import com.crm.dao.ExtendRewardInfoDao;
import com.crm.dao.ExtendUserBasicInfoDao;
import com.crm.service.ExtendInterface;
import com.crm.utils.ExtendRedis;

@Controller
public class MainController extends BaseController {

  @Autowired
  private ExtendInterface extendInterface;
  @Autowired
  private ExtendRewardInfoDao extendRewardInfoDao;
  @Autowired
  private ExtendUserBasicInfoDao extendUserBasicInfoDao;
  @RequestMapping("/")
  @ResponseBody
  public String index() {
    return "Hello Ema";
  }



  @RequestMapping("/s")
  @ModelAttribute
  public ModelAndView bindTg(HttpServletRequest request,String allianceId,String uid) {
    ModelAndView mv = new ModelAndView("redirect:"+"http://www.kingofmoba.com");
    String status="";
    String message="";
    JSONObject results = null;
    try {
  } catch (DataAccessException e) {
      status = InterfaceConstant.CODE_FALIURE;
      message = e.getCause().getMessage();
      e.printStackTrace();
    } catch (Exception e1) {
      status = InterfaceConstant.CODE_FALIURE;
      message = e1.getMessage();
      e1.printStackTrace();
    }
    return mv;
  }
  @Autowired
  private ExtendRedis extendRedis;
  @Autowired
  private LocaleMessageSourceConfig localeMessage;
  @RequestMapping(value="/testDB",method = RequestMethod.POST)
  public String testDB(HttpServletRequest request) {
	  String json;
	  TokenModel result = null;
	try {
		
    	TokenModel tm = new TokenModel();
    	tm.setToken("dsadsadasdasdasd");
    	extendRedis.set("777777token20015", tm, 10000);
        result = extendRedis.get("777777token20015",TokenModel.class);
        System.out.println(result.getToken());
//		json = getJsonBody(request);
//		String body = new String(Base64.decode(json));
//		JSONObject params = getParams(body);
//		ExtendUserBasicInfo eb = new ExtendUserBasicInfo();
//		  eb.setAlliance_id(params.getString("alliance_id"));
//		  eb.setApp_id(params.getLong("app_id"));
//		  eb.setChannel_tag(params.getString("channel_tag"));
//		  String m = StringUtils.random9();
//		  eb.setInvite_code(params.getString("invitecode")+m);
//		  eb.setInvite_level(params.getInteger("invite_level"));
//		  eb.setIp(params.getString("ip"));
//		  eb.setServer(params.getString("server"));
//		  eb.setStatus(params.getInteger("status"));
//		  eb.setUid(params.getLong("uid"));
//		  extendUserBasicInfoDao.save(eb);
//		  ExtendUserBasicInfo eb2 = new ExtendUserBasicInfo();
//		  
//		  eb2 = extendUserBasicInfoDao.findByCode("AAAAAA"+m);
//		  extendUserBasicInfoDao.delete(eb2.getId());
		  
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result.getToken();
	  
  }
  
  @RequestMapping(value="/testCom",method = RequestMethod.GET)
  @ResponseBody
  public String testCommon() {
	  String json;
	  TokenModel result = null;
	  String msg = null;
	try {
		
		result = extendRedis.get("777777token20015",TokenModel.class);
		msg = localeMessage.getMessage("service.error.appid.null");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result.getToken()+"--"+msg;
	  
  }
}
