package com.dwring.springboot.test;

import java.util.List;
import javax.annotation.Resource;
import org.junit.Assert;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testng.annotations.Test;
import com.dwring.springboot.model.UserInfo;
import com.dwring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * AbstractTestNGSpringContextTests 没有事务
 * AbstractTransactionalTestNGSpringContextTests 默认启用事务和自动回滚。
 * 如果不想回滚，可手动添加注解 @Rollback(false)
 * 
 */
// @ActiveProfiles(profiles = "dev")
@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
public class UserServiceTest extends AbstractTestNGSpringContextTests {

	@Resource(name = "userService")
	private UserService userService;

	@Autowired
	private MockMvc mvc;

	@Test
	public void testQueryAll() {
		List<UserInfo> dtoList = userService.queryAll();
		System.out.println("-----------------dtoList:" + dtoList);
		Assert.assertFalse(dtoList.isEmpty());
	}

	@Test
	public void testWeatherController() throws Exception {
		MvcResult mvcResult = this.mvc.perform(get("/weather/shanghai")).andDo(print()).andExpect(status().isOk())
				.andReturn();
		System.out.println("--------"+mvcResult.getResponse().getContentAsString());

	}

	/**
	 * @Title: testWeather
	 * @Description: 按照顺序测试，依赖testWeatherController测试方法
	 */
	@Test(priority = 1, alwaysRun = true, dependsOnMethods = { "testWeatherController" })
	public void testWeather() throws Exception {
		this.mvc.perform(get("/weather/shanghai")).andDo(print()).andExpect(status().isOk());

	}
}
