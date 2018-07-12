package com.dwring.springboot.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Title: MockitoBasedTest.java
 * @Package com.dwring.springboot.test
 * @Description: TODO
 * @author haichangzhang
 * @date 2018年7月11日 下午6:08:47
 * @version V1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class MockitoBasedTest {

	@Before
	public void setUp() throws Exception {
		// 初始化测试用例类中由Mockito的注解标注的所有模拟对象
		MockitoAnnotations.initMocks(this);
	}
}
