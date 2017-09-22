package com.qf.springboot.controller;

import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.qf.springboot.exception.BizException;
import com.qf.springboot.model.BaseResponse;
import com.qf.springboot.model.UserInfo;
import com.qf.springboot.service.UserInfoService;
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
@RequestMapping("/users")
@Api("userController相关api")
public class UserController {

	@Autowired
	private UserInfoService userInfoService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@ApiOperation(value = "读取用户列表", notes = "")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public BaseResponse<Collection<UserInfo>> getUserList() {
		// 处理"/users/"的GET请求，用来获取用户列表
		// 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
		List<UserInfo> list = userInfoService.getList();
		return new BaseResponse<Collection<UserInfo>>(list);
	}

	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@ApiImplicitParam(name = "userInfo", value = "用户详细实体user", required = true, dataType = "UserInfo")
	@RequestMapping(value = "/", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public BaseResponse<?> creatUser(@RequestBody @Validated UserInfo userinfo) {
		// 处理"/users/"的POST请求，用来创建User
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		userInfoService.save(userinfo);
		return new BaseResponse<String>("success");
	}

	@ApiOperation(value = "获取用户信息", notes = "根据用户ID获取对应用户信息")
	@ApiImplicitParams(@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path"))
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public BaseResponse<UserInfo> loadUser(@PathVariable long id) {
		// 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
		// url中的id可通过@PathVariable绑定到函数的参数中
		return new BaseResponse<UserInfo>(userInfoService.load(id));
	}

	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer"),
			@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, paramType = "path", dataType = "UserInfo") })
	@ApiOperation(value = "修改用户信息", notes = "根据用户ID修改对应用户信息")
	@RequestMapping(value = "/{id}", produces = "application/json;charset=UTF-8", method = RequestMethod.PUT)
	public BaseResponse<UserInfo> updateUser(@PathVariable long id, @RequestBody @Validated UserInfo user) {
		// 处理"/users/{id}"的PUT请求，用来更新User信息
		UserInfo userinfo = userInfoService.load(id);
		userinfo.setRealname(user.getRealname());
		userInfoService.update(userinfo);
		return new BaseResponse<UserInfo>(userinfo);

	}

	@ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Integer")
	@ApiOperation(value = "删除用户信息", notes = "根据用户ID删除对应用户信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public BaseResponse<Boolean> deleteUser(@PathVariable long id) {
		// 处理"/users/{id}"的DELETE请求，用来删除User
		userInfoService.delete(id);
		return new BaseResponse<Boolean>();
	}

	@ApiOperation(value = "自定义异常", notes = "业务自定义异常")
	@RequestMapping(value = "/exception", method = RequestMethod.GET)
	public BaseResponse<String> exception() {
		throw new BizException("用户自定义异常。");
	}

	@RequestMapping(value = "/table", method = RequestMethod.GET)
	public BaseResponse<String> getTable() {
		RestTemplate restTemplate = new RestTemplate();
		String[] tables = { "account", "acrc_channel", "actor", "actor_login_log", "actor_role",
				"anchor_funder_channel", "ar", "ar_app", "ar_balance", "ar_log", "ar_pd", "ar_trade", "ar_trade_detail",
				"company", "discount_transaction", "invite_log", "invite_relation", "line_of_credit", "permission",
				"role", "sms_request_log", "t_eth_account", "t_eth_transaction", "transaction", "wf_company_config",
				"wf_system_config" };
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < tables.length; i++) {
			String url = "http://dbinfo.sl.com/datainfo/showtable/?env=PROD&db=supply_be&table=";
			String json=restTemplate.getForObject(url+tables[i], String.class);
			sBuilder.append(json);
			logger.info(json);
		}
		return new BaseResponse<String>(sBuilder.toString());
	}
}
