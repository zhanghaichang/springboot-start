package com.dwring.springboot.controller;

import com.dwring.springboot.model.BaseResponse;
import com.dwring.springboot.model.UserInfo;
import com.dwring.springboot.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserInfoController
 * @Description:
 * @author haichangzhang
 * @date 2017年7月11日 上午11:31:47
 * 
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	private IUserInfoService userInfoServiceImpl;

	@GetMapping(value = "/{id}")
	public BaseResponse<?> view(@PathVariable Long id) {
		UserInfo userInfo = userInfoServiceImpl.getById(id);
		return new BaseResponse<>(userInfo);
	}

	@GetMapping(value = "/")
	public BaseResponse<?> getAll() {
		return new BaseResponse<>(userInfoServiceImpl.getAll());
	}

	@DeleteMapping(value = "/{id}")
	public ModelMap delete(@PathVariable Long id) {
		ModelMap result = new ModelMap();
		userInfoServiceImpl.removeById(id);
		result.put("msg", "删除成功!");
		return result;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelMap save(@RequestBody UserInfo userInfo) {
		ModelMap result = new ModelMap();
		userInfoServiceImpl.save(userInfo);
		result.put("userInfo", userInfo);
		result.put("msg", "新增成功!");
		return result;
	}
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ModelMap update(@RequestBody UserInfo userInfo) {
		ModelMap result = new ModelMap();
		userInfoServiceImpl.updateById(userInfo);
		result.put("userInfo", userInfo);
		result.put("msg", "修改成功");
		return result;
	}
}
