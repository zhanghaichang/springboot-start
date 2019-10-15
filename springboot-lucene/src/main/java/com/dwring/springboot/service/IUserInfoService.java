package com.dwring.springboot.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dwring.springboot.model.UserInfo;

/**
 *  服务类
 * @author zhanghaichang
 * @since 2017-07-11
 */
public interface IUserInfoService extends IService<UserInfo> {

	
	List<UserInfo> getAll();
	
}
