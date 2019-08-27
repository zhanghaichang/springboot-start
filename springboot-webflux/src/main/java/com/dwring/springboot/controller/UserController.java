package com.dwring.springboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dwring.springboot.exception.BizException;
import com.dwring.springboot.handler.UserHandler;
import com.dwring.springboot.model.BaseResponse;
import com.dwring.springboot.model.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @ClassName: UserController
 * @Description:(使用 swagger 展示API )
 * @author haichangzhang
 * @date 2017年7月25日 上午11:09:23
 * 
 */
@Slf4j
@RestController
@RequestMapping("/users")
@Api("userController相关api")
public class UserController {

	@Autowired
	private UserHandler userHandler;

	@ApiOperation(value = "读取用户列表", notes = "")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Mono<BaseResponse<List<UserInfo>>> getUserList() {
		log.info("----getUserList request start-----");
		BaseResponse<List<UserInfo>> response = new BaseResponse<List<UserInfo>>(userHandler.findAll());
		log.info("----getUserList request end-----");
		return Mono.justOrEmpty(response);
	}

	@ApiOperation(value = "读取用户列表", notes = "")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public BaseResponse<List<UserInfo>> list() {
		log.info("----list request start-----");
		List<UserInfo> list = userHandler.findAll();
		log.info("----list request end-----");
		return new BaseResponse<List<UserInfo>>(list);
	}

	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UserInfo")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Mono<BaseResponse<?>> creatUser(@RequestBody UserInfo user) {
		log.info("----creatUser request start-----");
		return Mono.create(userMonoSink->userMonoSink.success(new BaseResponse<Long>(userHandler.save(user))));
	}

	@ApiOperation(value = "获取用户信息", notes = "根据用户ID获取对应用户信息")
	@ApiImplicitParams(@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path"))
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Mono<BaseResponse<?>> loadUser(@PathVariable Long id) {
		log.info("----deleteUser request start-----");
		BaseResponse<UserInfo> response = new BaseResponse<UserInfo>(userHandler.findUserById(id));
		log.info("----deleteUser request end-----");
		return Mono.justOrEmpty(response);
	}

	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer"),
			@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, paramType = "path", dataType = "UserInfo") })
	@ApiOperation(value = "修改用户信息", notes = "根据用户ID修改对应用户信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Mono<BaseResponse<?>> updateUser(@PathVariable Long id, @RequestBody UserInfo user) {
		log.info("----updateUser request start-----");
		UserInfo userinfo = userHandler.findUserById(id);
		userinfo.setEmail(user.getEmail());
		userinfo.setPassword(user.getPassword());
		userinfo.setQq(user.getQq());
		userinfo.setRealname(user.getRealname());
		userinfo.setTel(user.getTel());
		userinfo.setUsername(user.getUsername());
		userinfo.setUsertype(user.getUsertype());
		userinfo.setEnabled(user.getEnabled());
		BaseResponse<Long> response = new BaseResponse<Long>(userHandler.update(userinfo));
		log.info("----updateUser request end-----");
		return Mono.justOrEmpty(response);

	}

	@ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Integer")
	@ApiOperation(value = "删除用户信息", notes = "根据用户ID删除对应用户信息")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Mono<BaseResponse<?>> deleteUser(@PathVariable Long id) {
		log.info("----deleteUser request start-----");
		BaseResponse<Long> response = new BaseResponse<Long>(userHandler.delete(id));
		log.info("----deleteUser request end-----");
		return Mono.justOrEmpty(response);
	}

	@ApiOperation(value = "自定义异常", notes = "业务自定义异常")
	@RequestMapping(value = "/exception", method = RequestMethod.GET)
	public BaseResponse<String> exception() {
		throw new BizException("用户自定义异常。");
	}
}
