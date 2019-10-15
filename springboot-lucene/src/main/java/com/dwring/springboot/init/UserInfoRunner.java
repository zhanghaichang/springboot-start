package com.dwring.springboot.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.dwring.springboot.service.ILuceneService;

/**
 * 项目启动后,立即执行
 * 
 * @author zhanghaichang
 *
 */
@Component
@Order(value = 1)
public class UserInfoRunner implements ApplicationRunner {

	@Autowired
	private ILuceneService service;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// 启动后将同步,并创建index
		service.synUserInfoCreatIndex();
	}

}
