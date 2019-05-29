package com.dwring.springboot.controller;

import com.dwring.springboot.model.BaseResponse;
import com.dwring.springboot.model.UserInfo;
import com.dwring.springboot.primary.service.UserInfoService;
import com.dwring.springboot.validator.ValidatorUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserInfoController
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:25
 * 
 */
@Api("userController相关api")
@RequestMapping("/user")
@RestController
public class UserInfoPrimaryController {

	@Autowired
	private UserInfoService userInfoService;

	@ApiOperation(value = "读取用户列表", notes = "")
	@GetMapping(value = "/")
	public BaseResponse<?> getAll() {
		List<UserInfo> userInfoList = userInfoService.selectAll();
		return new BaseResponse<>(userInfoList);
	}

	@ApiOperation(value = "获取用户信息", notes = "根据用户ID获取对应用户信息")
	@GetMapping(value = "/{id}")
	public BaseResponse<?> view(@PathVariable @Validated @Min(value = 0, message = "id必须大于0") Integer id) {
		ValidatorUtils.validateEntity(id);
		UserInfo userInfo = userInfoService.loadById(id);
		return new BaseResponse<>(userInfo);
	}

	@ApiOperation(value = "删除用户信息", notes = "根据用户ID删除对应用户信息")
	@DeleteMapping(value = "/{id}")
	public BaseResponse<?> delete(@PathVariable Integer id) {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(id);
		userInfoService.deleteById(userInfo);
		return new BaseResponse<>();
	}

	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@PostMapping(value = "/")
	public BaseResponse<?> save(@RequestBody @Validated UserInfo userInfo) {
		userInfoService.save(userInfo);
		return new BaseResponse<>();
	}

	@ApiOperation(value = "修改用户信息", notes = "根据用户ID修改对应用户信息")
	@PutMapping(value = "/{id}")
	public BaseResponse<?> update(@PathVariable Integer id, @RequestBody UserInfo userInfo) {
		userInfoService.save(userInfo);
		return new BaseResponse<>();
	}
}
