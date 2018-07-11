package com.dwring.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @Title: RestTemplateTest.java
 * @Package com.dwring.springboot.test
 * @Description: TODO
 * @author haichangzhang
 * @date 2018年6月26日 上午10:32:22
 * @version V1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RestTemplateTest {
	
	
	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void testHttps() {
		String url = "https://free-api.heweather.com/v5/forecast?city=CN101080101&key=5c043b56de9f4371b0c7f8bee8f5b75e";
		String resp = restTemplate.getForObject(url, String.class);
		System.out.println(resp);
	}
}
