package com.qf.application.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qf.application.Application;
import com.qf.application.domain.UserInfo;
import com.qf.application.service.ComputeService;
import com.qf.application.service.UserInfoService;



/**
 * @Title: ApplicationTest.java
 * @Package
 * @Description: TODO
 * @author haichangzhang
 * @date 2017年6月21日 下午2:18:21
 * @version V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class ApplicationTest {

	@Autowired
	private ComputeService computeService;
	
	@Autowired
	private UserInfoService userInfoService;

	@Test
	public void addTest() {
		System.out.println("================================: "+computeService.add(1, 1));
		Assert.assertEquals(new Integer(2), computeService.add(1, 1));
	}
	
	
	@Test
	public void loadTest() {
		UserInfo user=userInfoService.loadById(1);
		System.out.println("================================: "+user);
		Assert.assertNotNull(user);
	}

}
