package com.dwring.springboot.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dwring.springboot.model.User;

/**
 * @Classname UserService
 * @Description 用户服务类
 */
public interface UserService extends IService<User>{

	/**
	 * 保存用户信息
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	boolean save(User entity);

	/**
	 * 查询全部用户信息
	 * 
	 * @return
	 */
	List<User> getUserList();
}
