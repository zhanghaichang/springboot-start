package com.qf.springboot.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qf.springboot.Application;
import com.qf.springboot.model.UserInfo;
import com.qf.springboot.primary.service.UserInfoService;

/**  
* @ClassName: JpaCRUDTest  
* @Description:多数据源 
* @author haichangzhang  
* @date 2017年6月21日 下午3:32:50  
*    
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JpaCRUDTest {

	@Autowired
	private UserInfoService userInfoService;

	@Test
	public void saveTest() {
		UserInfo userInfo = new UserInfo();
		userInfo.setEmail("zhangsan@qq.com");
		userInfo.setPassword("12343");
		userInfo.setRealname("王重阳");
		userInfo.setQq("99999999");
		userInfo.setTel("110");
		userInfoService.save(userInfo);
		Assert.assertEquals("王重阳", userInfoService.loadById(1).getRealname());
		System.out.println(userInfoService.loadById(1).getRealname());
	}

	@Test
	public void loadTest() {
		UserInfo userInfo = userInfoService.loadById(1);
		System.out.println(userInfo);
		Assert.assertEquals("test1", userInfoService.loadById(userInfo.getId()).getUsername());
	}

	@Test
	public void updateTest() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(1);
		userInfo.setRealname("张山峰");
		userInfoService.update(userInfo);
		userInfo = userInfoService.loadById(userInfo.getId());
		System.out.println(userInfo);
		Assert.assertEquals("张山峰", userInfo.getRealname());
	}

	@Test
	public void deleteTest() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(1);
		userInfoService.deleteById(userInfo);
		userInfo =userInfoService.loadById(userInfo.getId());
		System.out.println(userInfo);
		Assert.assertNull(userInfo);

	}

}
