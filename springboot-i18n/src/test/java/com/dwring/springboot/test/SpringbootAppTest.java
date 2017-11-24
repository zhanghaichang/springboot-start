package com.dwring.springboot.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dwring.springboot.model.UserInfo;
import com.dwring.springboot.service.UserInfoService;

/**
 * @ClassName SpringbootAppTest
 * @Description 单元测试
 * @author zhanghaichang
 * @date: 2017年11月8日 下午4:48:25
 *
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest
public class SpringbootAppTest {

	@Autowired
	private UserInfoService userinfoService;

	@Test
	public void testAdd() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(100);
		userInfo.setEnabled(1);
		userInfo.setQq("77022369");
		userInfo.setRealname("小黑");
		userInfo.setUsername("zhanghaichang");
		userInfo.setUsertype("1");
		userInfo.setPassword("zhanghaichang");
		userinfoService.save(userInfo);
	}

	@Test
	public void testLoad() {
		UserInfo userInfo = userinfoService.getById("100");
		Assert.assertEquals(userInfo.getUsername(), "zhanghaichang");
	}

	@Test
	public void testUpdate() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(100);
		userInfo.setUsername("zhanghaichang");
		userInfo.setRealname("小二黑");
		userInfo.setEmail("zhanghaichang@163.com");
		userInfo.setTel("13585888888");
		userInfo.setPassword("123456789");
		userInfo = userinfoService.updateById(userInfo);
		Assert.assertEquals(userInfo.getRealname(), "小二黑");
	}

	@Test
	public void testDelete() {
		int row = userinfoService.delById("100");
		Assert.assertEquals(row, 1);
	}

}
