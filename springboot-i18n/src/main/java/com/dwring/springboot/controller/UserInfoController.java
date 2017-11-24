package com.dwring.springboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dwring.springboot.constant.EnumUtils;
import com.dwring.springboot.model.BaseResponse;
import com.dwring.springboot.model.UserInfo;
import com.dwring.springboot.service.LocaleMessageSourceService;
import com.dwring.springboot.service.UserInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@Api(value = "/user", tags = "user", description = "用户管理")
@RequestMapping(value = "/user", produces = { APPLICATION_JSON_UTF8_VALUE })
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private LocaleMessageSourceService localeMessageService;

    @ApiOperation(value = "用户查询", code = 201)
	@RequestMapping(value = "/view/{id}",method=RequestMethod.GET)
	public BaseResponse<UserInfo> view(@PathVariable String id) {
		UserInfo userInfo = userInfoService.getById(id);
		return new BaseResponse<UserInfo>(userInfo);
	}

    @ApiOperation(value = "创建用户", code = 201)
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UserInfo")
	public BaseResponse<Object> save(@RequestBody @Validated UserInfo userInfo) {
		userInfoService.save(userInfo);
		return new BaseResponse<Object>(localeMessageService.getMessage("hello"),EnumUtils.ResponseCode.SUCCESS.getCode());
	}

	/**
	 * 数据分页 pagehelper
	 * 
	 * @return
	 */
    @ApiOperation(value = "用户列表", code = 201)
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public BaseResponse<List<UserInfo>> list() {
		PageHelper.startPage(1, 2);
		List<UserInfo> userInfos = userInfoService.listUserInfos();
		System.out.println("Total: " + ((Page<UserInfo>) userInfos).getTotal());
		return new BaseResponse<List<UserInfo>>(userInfos);
	}
}
