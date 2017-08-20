package com.ema.controllers;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crm.Application;
import com.crm.dao.ExtendAppInfoDao;
import com.crm.dao.ExtendUserBasicInfoDao;
import com.crm.models.ExtendAppInfo;
import com.crm.models.ExtendUserBasicInfo;

@Configuration  
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan({"com.ema"})  
@SpringBootApplication
@SpringApplicationConfiguration(classes = Application.class)
public class DaoTest {
	
	@Autowired
	ExtendAppInfoDao extendAppInfoDao;
	@Autowired
	ExtendUserBasicInfoDao extendUserBasicInfoDao;
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testFindByAppid() {
		ExtendAppInfo entity = extendAppInfoDao.findByAppid(20015L);
		System.out.println(entity.getHome_page_url());
	}
	/*@Test
	public void testFindByUid(){
		ExtendUserBasicInfo eubi = extendUserBasicInfoDao.findOne(10001L, 20015L, "360", "0", "1");
		System.out.println(eubi.getApp_id());
	}
	
	@Test
	public void testFindByInvitecode(){
		int i = extendUserBasicInfoDao.findByInvitecode("ASDWA1V");
		System.out.println(i);
		
	}*/
}
