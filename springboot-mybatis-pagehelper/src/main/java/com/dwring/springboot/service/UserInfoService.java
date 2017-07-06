package com.dwring.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwring.springboot.mapper.UserInfoMapper;
import com.dwring.springboot.model.UserInfo;


@Service
public class UserInfoService {
	
    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo getById(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }
}
