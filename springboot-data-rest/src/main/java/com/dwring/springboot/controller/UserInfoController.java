
package com.dwring.springboot.controller;

import com.dwring.springboot.model.UserInfo;
import com.dwring.springboot.service.UserInfoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserInfoController
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:25
 * 
 */
@RestController
@RequestMapping("/users")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping(value = "/list")
	public @ResponseBody List<UserInfo> getAll() {
		return userInfoService.selectAll();
	}

	@RequestMapping(value = "/view/{id}")
	public UserInfo view(@PathVariable Long id) {
		return userInfoService.loadById(id);
	}

	@RequestMapping(value = "/delete/{id}")
	public ModelMap delete(@PathVariable Long id) {
		ModelMap result = new ModelMap();
		UserInfo userInfo = new UserInfo();
		userInfo.setId(id);
		userInfoService.deleteById(userInfo);
		result.put("msg", "删除成功!");
		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelMap save(UserInfo userInfo) {
		ModelMap result = new ModelMap();
		String msg = userInfo.getId() == null ? "新增成功!" : "更新成功!";
		userInfoService.save(userInfo);
		result.put("userInfo", userInfo);
		result.put("msg", msg);
		return result;
	}

	@RequestMapping(value = "/add",produces="application/json", method = RequestMethod.POST)
	public String add(@RequestBody UserInfo userInfo) {
		String msg ="成功!";
		userInfoService.save(userInfo);
		return msg;
	}
}
