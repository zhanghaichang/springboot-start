package com.dwring.springboot.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Title: HttpsManager.java
 * @Package com.dwring.springboot.manager
 * @Description: TODO
 * @author haichangzhang
 * @date 2018年7月11日 下午3:45:10
 * @version V1.0
 */
@Service
public class HttpsManager {

	@Autowired
	private RestTemplate restTemplate;

	/** 
	* @Title: doPost 
	* @Description: 发送POST请求
	*/
	public String doPost(String url, String request) {
		return restTemplate.postForObject(url, request, String.class);
	}

	

	/** 
	* @Title: doGet 
	* @Description: 发送GET请求
	*/
	public String doGet(String url) {
		return restTemplate.getForObject(url, String.class);
	}
}
