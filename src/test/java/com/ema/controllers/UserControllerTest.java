package com.ema.controllers;

import static org.junit.Assert.assertEquals;

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

@Configuration  
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan({"com.ema"})  
@SpringBootApplication
@SpringApplicationConfiguration(classes = Application.class)
public class UserControllerTest {

//	@Autowired
//	UserController userController;
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

//	@Test
//	public void testProtogenesisSQL() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetAllUser() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetRedisForString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetRedisForStruct() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGeti18n() {
		
//		assertEquals("你好 汉语",userController.geti18n().toString());
	}

//	@Test
//	public void testGetUser() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCreate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDelete() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetByEmail() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUpdateUser() {
//		fail("Not yet implemented");
//	}

}
