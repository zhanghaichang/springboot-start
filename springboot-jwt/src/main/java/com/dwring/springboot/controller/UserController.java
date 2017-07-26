package com.dwring.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.dwring.springboot.neum.UserInfoEnum;

/**
 * Created by zhanghaichang on 2017/4/9.
 */
@RestController
@RequestMapping("/oauth")
public class UserController {

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public JSONObject user(int id) {
		JSONObject resultMsg = new JSONObject();
		switch (id) {
		case 1:
			resultMsg.put("username", UserInfoEnum.TEST.getUsername());
			break;
		case 2:
			resultMsg.put("username", UserInfoEnum.ADMIN.getUsername());
			break;
		case 3:
			resultMsg.put("username", UserInfoEnum.ZHANGSAN.getUsername());
			break;
		}
		return resultMsg;
	}

}
