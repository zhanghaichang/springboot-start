package com.dwring.springboot.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dwring.springboot.mapper.UserInfoMapper;
import com.dwring.springboot.model.UserInfo;
import com.dwring.springboot.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
/**
 * @Title: UserInfoServiceImpl.java
 * @Package com.qf.springboot.service.impl
 * @Description: TODO
 * @author haichangzhang
 * @date 2017年7月11日 下午6:23:35
 * @version V1.0
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	public List<UserInfo> getAll() {
		return userInfoMapper.getAll();
	}
}
