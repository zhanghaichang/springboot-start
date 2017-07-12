package com.qf.springboot.service;

import com.qf.springboot.model.UserInfo;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 *  服务类
 * @author zhanghaichang
 * @since 2017-07-11
 */
public interface IUserInfoService extends IService<UserInfo> {

	
	List<UserInfo> getAll();
	
}
