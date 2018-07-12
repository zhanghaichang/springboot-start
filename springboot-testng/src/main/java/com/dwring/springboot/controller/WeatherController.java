package com.dwring.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dwring.springboot.constant.EnumUtils;
import com.dwring.springboot.model.BaseResponse;
import com.dwring.springboot.service.WeatherService;
import lombok.extern.slf4j.Slf4j;

/**
 * @Title: WeatherController.java
 * @Package com.dwring.springboot.controller
 * @Description: 天气预报
 * @author haichangzhang
 * @date 2018年7月11日 下午3:38:47
 * @version V1.0
 */
@Slf4j
@RequestMapping("/weather")
@RestController
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@GetMapping("/shanghai")
	public BaseResponse<String> getWeather() {
		try {
			log.info("接受请求");
			String city = "shanghai";
			String key = "5c043b56de9f4371b0c7f8bee8f5b75e";
			String reponseBody = weatherService.getWeatherByCity(city, key);
			log.info("接受结束：{}", reponseBody);
			return new BaseResponse<>(reponseBody);
		} catch (Exception e) {
			log.error("系统发生异常:", e);
			return new BaseResponse<>(EnumUtils.ResponseCode.FAIL.getCode(), EnumUtils.ResponseCode.FAIL.getMessage(),
					e.getMessage());
		}

	}

}
