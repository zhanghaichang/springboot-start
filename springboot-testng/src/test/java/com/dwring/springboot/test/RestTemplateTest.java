package com.dwring.springboot.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
/**
 * @Title: RestTemplateTest.java
 * @Package com.dwring.springboot.test
 * @Description: resttemplate
 * @author haichangzhang
 * @date 2018年6月26日 上午10:32:22
 * @version V1.0
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestTemplateTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void testHttps() {
		String url = "https://free-api.heweather.com/v5/forecast?city=shanghai&key=5c043b56de9f4371b0c7f8bee8f5b75e";
		ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		System.out.println(entity.getBody());
	}
}
