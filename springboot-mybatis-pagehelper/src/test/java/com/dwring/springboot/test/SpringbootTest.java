package com.dwring.springboot.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dwring.springboot.model.UserInfo;
import com.dwring.springboot.service.UserInfoService;

/**
 * @ClassName SpringbootTest
 * @Description TODO
 * @author zhanghaichang
 * @date: 2017年11月9日 上午11:23:01
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTest {

	@Autowired
	private UserInfoService userInfoService;

	@Before
	public void before() {
//		UserInfo user = new UserInfo();
//		user.setRealname("zhanghaichang");
//		user.setPassword("123456789");
//		user.setUsername("AAA");
//		userInfoService.save(user);
	}

	@Test
	public void testLoadUser() {
		UserInfo user1 = userInfoService.getById(101);
		System.out.println("第一次查询：" + user1.getUsername());

		UserInfo user2 = userInfoService.getById(101);
		System.out.println("第二次查询：" + user2.getUsername());

	}

}
