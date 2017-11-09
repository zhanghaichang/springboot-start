package com.dwring.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dwring.springboot.model.UserInfo;
import com.dwring.springboot.service.UserInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/user", produces = { APPLICATION_JSON_UTF8_VALUE })
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping(value = "/view/{id}")
	public UserInfo view(@PathVariable Integer id) {
		UserInfo userInfo = userInfoService.getById(id);
		return userInfo;
	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
	public String Delete(@PathVariable Integer id) {
		userInfoService.delete(id);
		return "successfully";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String save(@RequestBody UserInfo userInfo) {
		userInfoService.save(userInfo);
		return "successfully";
	}

	/**
	 * 数据分页 pagehelper
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list")
	public List<UserInfo> list() {
		PageHelper.startPage(1, 2);
		List<UserInfo> userInfos = userInfoService.listUserInfos();
		System.out.println("Total: " + ((Page<UserInfo>) userInfos).getTotal());
		return userInfos;
	}
}
