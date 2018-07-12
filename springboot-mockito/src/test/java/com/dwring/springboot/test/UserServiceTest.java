package com.dwring.springboot.test;

import static org.mockito.Mockito.when;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import com.dwring.springboot.model.UserInfo;
import com.dwring.springboot.repository.UserRepository;
import com.dwring.springboot.service.UserService;

/**
 * @Title: UserServiceTest.java
 * @Package com.dwring.springboot.test
 * @Description: 用户验证
 * @author haichangzhang
 * @date 2018年7月11日 下午6:30:12
 * @version V1.0
 */
public class UserServiceTest extends MockitoBasedTest {

	@InjectMocks
	@Resource(name = "userService")
	private UserService userService;

	/**
	 * @Spy(真实调用) @Mock(mock对象)
	 */
	@Spy
	@Resource
	// @Mock
	private UserRepository userDao;

	@Test
	public void testQueryAll() {
		when(userDao.selectAll()).thenReturn(Collections.<UserInfo>emptyList());
		List<UserInfo> dtoList = userService.queryAll();
		System.out.println("-----------------=====dtoList:" + dtoList);
		Assert.assertTrue(dtoList.isEmpty());
	}
}
