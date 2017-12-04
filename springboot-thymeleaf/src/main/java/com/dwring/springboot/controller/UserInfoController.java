package com.dwring.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dwring.springboot.model.UserInfo;
import com.dwring.springboot.service.UserInfoService;

@Controller
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Integer id,Model model) {
		UserInfo userInfo = userInfoService.getById(id);
		model.addAttribute("user", userInfo);
		return "user";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<UserInfo> list = userInfoService.listUserInfos();
		model.addAttribute("userlist", list);
		return "userlist";
	}

}
