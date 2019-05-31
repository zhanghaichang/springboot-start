package com.dwring.springboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dwring.springboot.model.BaseResponse;
import com.dwring.springboot.model.User;
import com.dwring.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: UserController
 * @Description:(使用 swagger 展示API )
 * @author haichangzhang
 * @date 2017年7月25日 上午11:09:23
 * 
 */
@RestController
@RequestMapping("/user")
@Api("userController相关api")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "读取用户列表", notes = "")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public BaseResponse<?> getUserList() {
		List<User> list = userService.getUserList();
		if (list.size() > 0) {
			return new BaseResponse<>(list);
		} else {
			return new BaseResponse<>("没有数据");
		}
	}

	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UserInfo")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public BaseResponse<User> creatUser(@RequestBody User user) {
		userService.save(user);
		return new BaseResponse<>();
	}

	@ApiOperation(value = "获取用户信息", notes = "根据用户ID获取对应用户信息")
	@ApiImplicitParams(@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path"))
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public BaseResponse<User> loadUser(@PathVariable Integer id) {
		return new BaseResponse<User>(userService.getById(id));
	}

	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer"),
			@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, paramType = "path", dataType = "UserInfo") })
	@ApiOperation(value = "修改用户信息", notes = "根据用户ID修改对应用户信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public BaseResponse<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
		// 处理"/users/{id}"的PUT请求，用来更新User信息
		user.setId(id);
		userService.updateById(user);
		return new BaseResponse<>();

	}

	@ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Integer")
	@ApiOperation(value = "删除用户信息", notes = "根据用户ID删除对应用户信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public BaseResponse<Boolean> deleteUser(@PathVariable Integer id) {
		userService.removeById(id);
		return new BaseResponse<Boolean>();
	}

}
