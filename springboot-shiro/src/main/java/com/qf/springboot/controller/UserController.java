
package com.qf.springboot.controller;

import com.qf.springboot.model.User;
import com.qf.springboot.service.UserService;
import java.util.List;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: UserInfoController
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:25
 * 
 */
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userInfoService;

	/**
	 * 用户查询List.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list")
	@RequiresPermissions("userInfo:view") // 权限管理;
	public @ResponseBody List<User> getAll() {
		List<User> userInfoList = userInfoService.selectAll();
		return userInfoList;
	}

	/**
	 * 用户查询.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/info/{uid}")
	@RequiresPermissions("userInfo:view") // 权限管理;
	public String UserInfo(@PathVariable("uid") Integer uid, Map<String, Object> map) {
		User user = userInfoService.loadById(uid);
		map.put("user", user);
		return "userinfo";
	}

	/**
	 * 用户添加;
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@RequiresPermissions("userInfo:add") // 权限管理;
	public String userInfoAdd() {
		return "userinfoadd";
	}

	/**
	 * 用户删除;
	 * 
	 * @return
	 */
	@RequestMapping("/del")
	@RequiresPermissions("userInfo:del") // 权限管理;
	public String userDel() {
		return "userinfodel";
	}

}
