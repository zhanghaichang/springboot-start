package com.qf.application.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qf.springboot.Application;
import com.qf.springboot.domain.AccountInfo;
import com.qf.springboot.domain.City;
import com.qf.springboot.service.AccountInfoService;
import com.qf.springboot.service.CityService;

/**
 * @Title: AppTest.java
 * @Package com.qf.application.test
 * @Description: TODO
 * @author haichangzhang
 * @date 2017年6月22日 上午11:41:37
 * @version V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AppTest {

	@Autowired
	private AccountInfoService accountInfoService;

	@Autowired
	private CityService cityService;

	@Test
	public void save() {
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setId("1");
		accountInfo.setAccountName("China Bank");
		accountInfo.setNickName("zhangsan");
		accountInfoService.save(accountInfo);
	}
	@Test
    public void createCityTest() {
		City city=new City();
		city.setId(4l);
		city.setName("shanghai");
		city.setDescription("帝都");
		city.setScore(100);
        cityService.saveCity(city);
    }

}
