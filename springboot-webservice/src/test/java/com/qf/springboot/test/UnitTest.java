package com.qf.springboot.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dwring.springboot.controller.UserController;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @ClassName UnitTest
 * @Description 单元测试
 * @author zhanghaichang
 * @date: 2017年9月13日 上午10:59:46
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
public class UnitTest {

	private MockMvc mvc;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}

	@Test
	public void testUserController() throws Exception {
		RequestBuilder request = null;
		// 1、get查一下user列表，应该为空
		request = get("/users/");
		mvc.perform(request).andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"msg\":\"success\",\"code\":200,\"data\":[]}")));
		// 2、post提交一个user
		// TODO 对象转json 自己实现
		String requestBody = "{\"email\":\"zhanghaichang@163.com\",\"enabled\":0,\"id\":1,\"password\":\"sdfsd\",\"qq\":\"1233434345\",\"realname\":\"zzzzz\",\"tel\":\"13585343438\",\"username\":\"zhanghaichang\",\"usertype\":\"1\"}";
		request = post("/users/").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestBody);
		mvc.perform(request).andExpect(status().isOk())
				.andExpect(jsonPath("$.data", is("success"))).andDo(print())
				.andReturn().getResponse().getContentAsString();
		// 3、get获取user列表，应该有刚才插入的数据
		request = get("/users/");
		mvc.perform(request).andExpect(status().isOk())
				.andExpect(jsonPath("$.data",hasSize(1)))
				.andDo(print());
		 // 4、put修改id为1的user
		String update = "{\"email\":\"zhanghaichang@163.com\",\"enabled\":0,\"id\":1,\"password\":\"sdfsd\",\"qq\":\"1233434345\",\"realname\":\"张海昌\",\"tel\":\"13585343438\",\"username\":\"zhanghaichang\",\"usertype\":\"1\"}";
		 request=put("/users/1").contentType(MediaType.APPLICATION_JSON_UTF8)
				 .content(update);
		 mvc.perform(request).andExpect(status().isOk())
		 		.andDo(print());
		 // 5、get一个id为1的user
		 request=get("/users/1");
		 mvc.perform(request).andExpect(status().isOk())
		 		.andExpect(jsonPath("$.data.realname", is("张海昌")))
		 		.andDo(print());
		 // 6、del删除id为1的user
		 request=delete("/users/1");
		 mvc.perform(request).andExpect(status().isOk())
		 		.andExpect(jsonPath("$.data",nullValue()))
		 		.andDo(print());
		 // 7、get查一下user列表，应该为空
		 request=get("/users/");
		 mvc.perform(request).andExpect(status().isOk())
		 	.andExpect(jsonPath("$.data",hasSize(0)))
		 	.andDo(print());

	}

}
