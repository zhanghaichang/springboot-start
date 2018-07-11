package com.dwring.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dwring.springboot.manager.HttpsManager;

/**
 * @Title: WeatherService.java
 * @Package com.dwring.springboot.service
 * @Description: 天气预报服务类
 * @author haichangzhang
 * @date 2018年7月11日 下午3:47:24
 * @version V1.0
 */
@Service
public class WeatherService {

	@Autowired
	private HttpsManager httpsManager;

	private static final String WEATHER_URL = "https://free-api.heweather.com/v5/forecast?";

	/**
	 * @Title: getWeatherByCity
	 * @Description: 获取城市天气预报信息
	 */
	public String getWeatherByCity(String city, String key) {
		String requestUrl = WEATHER_URL + String.format("city=%s&key=%s", city, key);
		return httpsManager.doGet(requestUrl);
	}

}
